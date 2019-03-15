package com.want.mq.ldap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import com.want.mq.model.Employee;


abstract class IdentityService {

	private String _type = null;
	private LDAPManager ldap = null;

	public IdentityService(String type) {
		_type = type;
	}

	private LDAPManager getLDAPManager() throws Exception {
		if (ldap == null)
			ldap = new LDAPManager(_type);
		return ldap;
	}

  

	// 从工号得到 Employee物件
	public Employee getEmployeeById(String id) throws Exception {
		Employee employeeById = getLDAPManager().getEmployeeById(id);
		return employeeById;
	}
	
}