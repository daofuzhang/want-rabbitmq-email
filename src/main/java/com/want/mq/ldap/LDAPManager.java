package com.want.mq.ldap;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.want.mq.config.CxfConfig;
import com.want.mq.config.LdapConfig;
import com.want.mq.model.Employee;
import com.want.mq.model.Organization;
import com.want.mq.model.Position;
import com.want.mq.model.PositionComparator;


class LDAPManager {

	private static Logger log = LoggerFactory.getLogger(LDAPManager.class);
	 
	private final int LDAP_SIZELIMIT_EXCEEDED = 1000;
	private final int ONE_RESULT = 1;
	private final int MANY_RESULT = 2;
	private final String MEMBER = "member";
	private final String[] MEMBER_SEARCH_CONTROL = { MEMBER };
	private final String ldapGroupMemberAttribute = "member";
	private final String areaObjectClass = "container";
	private final String groupObjectClass = "group";
	private final String placeOfBusinessDN = "CN=营业所,CN=AP";
	private final String ORG_SEARCHDN = "CN=Organizations, CN=Groups, ";
	private final String POS_SEARCHDN = "CN=Positions, CN=Groups, ";

	private String groupOU = null;

	private String orgOU = null;

	private String posOU = null;

	private String userOU = null;

	private String ldapBaseDN = null;

	// add by wang yi cheng
	private String ldapHrDN = null;

	private Hashtable<String, Object> context = null;
	
	protected String providerUrl = null;

	// private LdapContext ldapContext = null;

	/**
	 * update by wang yi cheng
	 * 
	 * 通过NWA切换 Test or Prod
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws NamingException
	 * @throws SQLException
	 */
	LDAPManager(String type) throws IOException, NumberFormatException,
			NamingException, SQLException {
		
		LdapConfig ldap= SpringUtils.getBean(LdapConfig.class);
		if(null==ldap) {
			log.error("ladp 获取配置信息失败");
		}

		ldapBaseDN = ldap.getLdapBaseDN();
		String ldapHostname = ldap.getLdapHostname();
		String ldapPort =ldap.getLdapPort();
		String ldapUsername = ldap.getLdapUsername()+ "," + ldapBaseDN;
		String ldapPassword = ldap.getLdapPassword();

		providerUrl = "ldap://" + ldapHostname + ":" + ldapPort;
		/**
		 * iwwp.properties
		 */
		ldapHrDN = "CN=HR,CN=Groups" + "," + ldapBaseDN;

		groupOU = "CN=Groups" + "," + ldapBaseDN;

		orgOU = ORG_SEARCHDN + ldapBaseDN;

		posOU = POS_SEARCHDN + ldapBaseDN;

		userOU = "CN=Users" + "," + ldapBaseDN;

		context = new Hashtable<String, Object>();
		context.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		context.put(Context.PROVIDER_URL, providerUrl);
//		context.put("java.naming.ldap.attributes.binary", "objectSid");
//		context.put(Context.SECURITY_AUTHENTICATION, "simple");
		context.put("java.naming.ldap.attributes.binary", "objectSid");
		context.put(Context.SECURITY_AUTHENTICATION, "simple");
		context.put(Context.SECURITY_PRINCIPAL, ldapUsername);
		context.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		
		String pooling = ldap.getPooling();
		context.put("com.sun.jndi.ldap.connect.pool", pooling);
		String poolMaxsize = ldap.getPoolMaxsize();
		context.put("com.sun.jndi.ldap.connect.pool.maxsize", poolMaxsize);
//		context.put("com.sun.jndi.ldap.connect.pool.timeout", "30000");
//		context.put("com.sun.jndi.ldap.connect.pool.debug", "all");
	}

	public InitialLdapContext getLdapContext() throws NamingException {
		InitialLdapContext ctx = new InitialLdapContext(context, null);
//		trace.debugT("LDAP client: " + ctx.get);
		return ctx;
	}

	private String getDN(String base, String cn) throws NamingException,
			IOException {
		String ret = null;
		List<SearchResult> values = query(base, "(cn=" + cn + ")",
				Constant.GROUP_OBJECT_CLASS, SearchControls.SUBTREE_SCOPE, ONE_RESULT);
		if (values != null && values.size() > 0) {
			SearchResult result = values.get(0);
			ret = result.getName() + "," + base;
		}
		return ret;
	}

	public boolean existsInGroupName(String searchedDN, String groupName)
			throws NamingException {
		String groupDN = "CN=" + groupName + ",CN=AP," + groupOU;
		return existsInGroup(searchedDN, groupDN);
	}

	public boolean existsInGroup(String searchedDN, String groupDN)
			throws NamingException {
		Attributes attributes = getAttributes(groupDN, MEMBER_SEARCH_CONTROL);
		if (attributes != null) {
			Attribute memberAtts = attributes.get(MEMBER);
			if (memberAtts != null) {
				for (NamingEnumeration<?> vals = memberAtts.getAll(); vals
						.hasMoreElements();) {
					String uniqueMemberDN = (String) vals.nextElement();
					if (searchedDN.equalsIgnoreCase(uniqueMemberDN))
						return true;
				}
			}
		}
		return false;
	}

	public boolean userInGroup(String username, String groupName)
			throws NamingException {
		return existsInGroup(username, getGroupDN(groupName));
	}

	public List<String> getMembers(String groupName) throws NamingException {
		Attributes attributes = getAttributes(getGroupDN(groupName),
				MEMBER_SEARCH_CONTROL);
		List<String> members = new ArrayList<String>();
		if (attributes != null) {
			Attribute memberAtts = attributes.get(MEMBER);
			if (memberAtts != null) {
				for (NamingEnumeration<?> vals = memberAtts.getAll(); vals
						.hasMoreElements(); members
						.add(getUserUID((String) vals.nextElement())))
					;
			}
		}
		return members;
	}

	public String getSIDAsString(byte[] SID) {
		StringBuilder strSID = new StringBuilder("S-");
		strSID.append(SID[0]).append('-');
		StringBuilder tmpBuff = new StringBuilder();
		for (int t = 2; t <= 7; t++) {
			String hexString = Integer.toHexString(SID[t] & 0xFF);
			tmpBuff.append(hexString);
		}
		strSID.append(Long.parseLong(tmpBuff.toString(), 16));
		int count = SID[1];
		for (int i = 0; i < count; i++) {
			int currSubAuthOffset = i * 4;
			tmpBuff.setLength(0);
			tmpBuff.append(String.format("%02X%02X%02X%02X",
					(SID[11 + currSubAuthOffset] & 0xFF),
					(SID[10 + currSubAuthOffset] & 0xFF),
					(SID[9 + currSubAuthOffset] & 0xFF),
					(SID[8 + currSubAuthOffset] & 0xFF)));

			strSID.append('-').append(Long.parseLong(tmpBuff.toString(), 16));
		}
		return strSID.toString();
	}

	

	private String getCN(String dn) {
		String result = "";
		if (dn != null) {
			String cn = dn.trim().toUpperCase();
			if (cn.startsWith("CN"))
				result = cn.substring(0, cn.indexOf(","));
		}
		return result;
	}

	public String getOrgLdapStringUnderOrgId(String dn) throws NamingException,
			IOException {
		if (dn.indexOf("Organizations") == -1)
			return "";

		StringBuffer ldapString = new StringBuffer("(").append(getCN(dn))
				.append(")");
		Attributes attributes = getAttributes(dn, MEMBER_SEARCH_CONTROL);
		if (attributes != null) {
			Attribute memberAtts = attributes.get(MEMBER);
			if (memberAtts != null) {
				for (NamingEnumeration<?> vals = memberAtts.getAll(); vals
						.hasMoreElements();) {
					String subDn = (String) vals.nextElement();
					if (subDn.indexOf("Organizations") != -1)
						ldapString.append(getOrgLdapStringUnderOrgId(subDn));
				}
			}
		}

		return ldapString.toString();
	}

	private String generateUsersFilterUnderOrg(String orgDN, int scope)
			throws NamingException {
		StringBuffer users = new StringBuffer();
		Attributes attributes = getAttributes(orgDN, MEMBER_SEARCH_CONTROL);
		if (attributes != null) {
			Attribute memberAtts = attributes.get(MEMBER);
			if (memberAtts != null)
				for (NamingEnumeration<?> vals = memberAtts.getAll(); vals
						.hasMoreElements();) {
					String found = (String) vals.nextElement();
					if (found.indexOf("Organizations") != -1) {
						if (scope == SearchControls.SUBTREE_SCOPE)
							users.append(generateUsersFilterUnderOrg(found,
									scope));
					} else if (found.indexOf("Positions") != -1) {
						Attributes attributes1 = getAttributes(found,
								MEMBER_SEARCH_CONTROL);
						if (attributes1 != null) {
							Attribute memberAtts1 = attributes1.get(MEMBER);
							if (memberAtts1 != null) {
								for (NamingEnumeration<?> vals1 = memberAtts1
										.getAll(); vals1.hasMoreElements();) {
									String person = (String) vals1
											.nextElement();
									users.append("(").append(
											person.substring(0, person
													.indexOf(","))).append(")");
								}
							}
						}
					}
				}
		}
		return users.toString();
	}

	

	public List<SearchResult> query(String subDN, String filter, String objectClass)
			throws NamingException, IOException {
		
		return query(subDN + "," + this.ldapBaseDN, filter, objectClass, SearchControls.SUBTREE_SCOPE, MANY_RESULT, this.LDAP_SIZELIMIT_EXCEEDED);
	}

	private List<SearchResult> query(String searchDN, String filter,
			String objectClass, int scope, int occurrance)
			throws NamingException, IOException {
		return query(searchDN, filter, objectClass, scope, occurrance, this.LDAP_SIZELIMIT_EXCEEDED);
	}

	private List<SearchResult> query(String searchDN, String filter,
			String objectClass, int scope, int occurrance, int size)
			throws NamingException, IOException {
		List<SearchResult> values = new ArrayList<SearchResult>();
		StringBuffer filterString = new StringBuffer();

		if (objectClass != null) {
			if (filter != null)
				filterString.append("(&").append(objectClass).append("(|")
						.append(filter).append("))");
		} else {
			filterString.append(filter);
		}

		byte[] cookie = null;
		InitialLdapContext ctx = getLdapContext();
		ctx.setRequestControls(new Control[] { new PagedResultsControl(
				size, Control.CRITICAL) });

		do {
			SearchControls cons = new SearchControls();

			cons.setSearchScope(scope);
			NamingEnumeration<?> results = ctx.search(searchDN, filterString
					.toString(), cons);

			while (results.hasMore()) {
				values.add((SearchResult) results.next());

				if (occurrance == ONE_RESULT)
					break;
			}

			if (occurrance == ONE_RESULT)
				break;

			Control[] controls = ctx.getResponseControls();
			if (controls != null)
				for (int i = 0; i < controls.length; i++)
					if (controls[i] instanceof PagedResultsResponseControl) {
						PagedResultsResponseControl prrc = (PagedResultsResponseControl) controls[i];
						cookie = prrc.getCookie();
					}

			ctx.setRequestControls(new Control[] { new PagedResultsControl(
					this.LDAP_SIZELIMIT_EXCEEDED, cookie, Control.CRITICAL) });
		} while (cookie != null);

		ctx.close();
		return values;
	}

	

	private Attributes getAttributes(String dn, String[] search)
			throws NamingException {
		InitialLdapContext ctx = getLdapContext();
		Attributes attributes = null;
		if (search == null)
			attributes = ctx.getAttributes(dn);
		else
			attributes = ctx.getAttributes(dn, search);
		ctx.close();
		return attributes;
	}

	public Employee getEmployeeByPos(String searchOrg) throws NamingException {
		Attributes attributes = getAttributes(searchOrg, MEMBER_SEARCH_CONTROL);
		if (attributes != null) {
			Attribute memberAtts = attributes.get(MEMBER);
			if (memberAtts != null) {
				for (NamingEnumeration<?> vals = memberAtts.getAll(); vals
						.hasMoreElements();) {
					String dn = (String) vals.nextElement();
					return new Employee(getAttributes(dn, null));
				}
			}
		}
		return null;
	}

	


	public Employee getEmployeeById(String id) throws NamingException,
			IOException, OrganizationNotFoundException {

		Employee employee = null;
		String baseDN = "CN=Users," + ldapBaseDN;
		List<SearchResult> values = query(baseDN, "(cn=" + id + ")",
				Constant.USER_OBJECT_CLASS, SearchControls.SUBTREE_SCOPE, ONE_RESULT);
		if (values != null && values.size() > 0) {
			SearchResult value = values.get(0);
			employee = new Employee(value.getAttributes());
			String dn = value.getName() + "," + baseDN;
			List<Position> positionList = getPositions(dn, true);
			employee.setPositionList(positionList);
		}
		return employee;
	}
	
	private List<Position> getPositions(String dn, boolean withOrder)
			throws NamingException, IOException, OrganizationNotFoundException {
		List<Position> groups = new ArrayList<Position>();
		List<SearchResult> values = query(posOU, "(member=" + dn + ")",
				Constant.GROUP_OBJECT_CLASS, SearchControls.SUBTREE_SCOPE, MANY_RESULT);
		for (SearchResult value : values) {
			Position position = new Position(value.getAttributes());
			String posDN = value.getName() + "," + posOU;
			position.setOu(posDN);
			Organization organization = getOrganizationBySearchedDN(posDN);
			position.setOrganization(organization);
			groups.add(position);
		}
		PositionComparator comparator = new PositionComparator();
		Collections.sort(groups, comparator);
		return groups;
	}
	private Organization getOrganizationBySearchedDN(String searchedDN)
			throws NamingException, OrganizationNotFoundException, IOException {
		Organization org = null;
		List<SearchResult> values = query(orgOU, "(member=" + searchedDN + ")",
				null, SearchControls.SUBTREE_SCOPE, MANY_RESULT);
		for (SearchResult value : values) {
			String dn = value.getName();
			if (dn != null && !dn.equals("") && !dn.startsWith("CN=外点分公司")
					&& !dn.startsWith("CN=工厂"))
				org = new Organization(value.getAttributes());
		}
		return org;
	}
	
	private String getUserUID(String userDN) {
		int start = userDN.indexOf("=");
		int end = userDN.indexOf(",");

		if (end == -1) {
			end = userDN.length();
		}

		return userDN.substring(start + 1, end);
	}

	private String getGroupDN(String name) {
		return new StringBuffer().append("cn=").append(name).append(",")
				.append(orgOU).toString();
	}

	public List<String> getEmployeeIdListUnderOrgId(String orgId)
			throws NamingException, IOException {
		List<String> employeeIdList = new ArrayList<String>();
		String organizationDN = getDN(orgOU, orgId);
		Map<String, String> employeeIdMap = new HashMap<String, String>();
		if (organizationDN != null) {
			employeeIdList = getEmployeeIdListUnderOrgDN(organizationDN);
		}
		return employeeIdList;
	}

	public List<String> getEmployeeIdListUnderOrgDN(String dn)
			throws NamingException {
		List<String> employeeIdList = new ArrayList<String>();
		String[] searchAttributes = new String[1];
		searchAttributes[0] = ldapGroupMemberAttribute;

		Attributes attributes = null;
		DirContext ctx = getLdapContext();

		try {
			attributes = ctx.getAttributes(dn, searchAttributes);
			if (attributes != null) {
				Attribute memberAtts = attributes.get(ldapGroupMemberAttribute);
				if (memberAtts != null) {
					for (NamingEnumeration<?> vals = memberAtts.getAll(); vals
							.hasMoreElements();) {
						String findDN = (String) vals.nextElement();
						if (findDN.indexOf("Organizations") != -1) {
							employeeIdList
									.addAll(getEmployeeIdListUnderOrgDN(findDN));
						}
						if (findDN.indexOf("Positions") != -1) {
							Attributes attributes1 = ctx.getAttributes(findDN,
									searchAttributes);
							if (attributes1 != null) {
								Attribute memberAtts1 = attributes1
										.get(ldapGroupMemberAttribute);
								if (memberAtts1 != null) {
									Map<String, String> employeeIdMap = new HashMap<String, String>();
									for (NamingEnumeration<?> vals1 = memberAtts1
											.getAll(); vals1.hasMoreElements();) {
										String findDN1 = (String) vals1
												.nextElement();
										Employee employee = new Employee(
												getAttributes(findDN1, null));
										// System.out.println("employee="+employee);
										if (!employeeIdMap.containsKey(employee
												.getId())) {
											employeeIdList
													.add(employee.getId());
											employeeIdMap.put(employee.getId(),
													employee.getId());
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}

		ctx.close();

		return employeeIdList;
	}

	

	private String findAreaDNByAreaCode(String group) {
		String organizationDN = null;
		SearchControls cons = new SearchControls();
		cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String filter = new StringBuffer().append("(&").append(
				"(description=" + group + ")").append(
				"(objectClass=" + areaObjectClass + ")").append(")").toString();

		try {
			DirContext ctx = getLdapContext();
			NamingEnumeration<?> results = ctx.search(orgOU, filter, cons);
			while (results.hasMore()) {
				SearchResult result = (SearchResult) results.next();
				organizationDN = result.getName() + "," + orgOU;
			}
			ctx.close();
		} catch (NamingException e) {
		}
		return organizationDN;
	}

	public List<Organization> getOrganizationListBySearchedDN(String searchedDN) {
		List<Organization> organizationList = new ArrayList<Organization>();
		SearchControls cons = new SearchControls();
		cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String filter = new StringBuffer().append("(&").append(
				"(objectClass=" + groupObjectClass + ")").append(")")
				.toString();
		try {
			DirContext ctx = getLdapContext();
			NamingEnumeration<?> results = ctx.search(searchedDN, filter, cons);
			while (results.hasMore()) {
				SearchResult result = (SearchResult) results.next();
				String organizationDN = result.getName() + "," + searchedDN;
				Organization organization = new Organization(getAttributes(
						organizationDN, null));
				organizationList.add(organization);
			}
			ctx.close();
		} catch (NamingException e) {
		}
		return organizationList;
	}

	

	private List<Organization> getPlaceOfBusinessListBySearchedDN(
			String searchedDN) {
		List<Organization> organizationList = new ArrayList<Organization>();
		SearchControls cons = new SearchControls();
		cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String filter = new StringBuffer().append("(&").append(
				"(objectClass=" + groupObjectClass + ")").append(")")
				.toString();
		try {
			DirContext ctx = getLdapContext();
			NamingEnumeration<?> results = ctx.search(searchedDN, filter, cons);
			while (results.hasMore()) {
				SearchResult result = (SearchResult) results.next();
				String organizationDN = result.getName() + "," + searchedDN;
				String group = placeOfBusinessDN + "," + groupOU;
				if (existsInGroup(organizationDN, group)) {
					Organization organization = new Organization(getAttributes(
							organizationDN, null));
					organizationList.add(organization);
				}
			}
			ctx.close();
		} catch (NamingException e) {
		}
		return organizationList;
	}

	

	
}
