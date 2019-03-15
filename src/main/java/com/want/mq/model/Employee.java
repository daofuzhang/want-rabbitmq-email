package com.want.mq.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class Employee extends User implements Serializable {
	private static final long serialVersionUID = 6355425232914289151L;
	public static final String[] jobGradeDesc = { "特一等", "特二等", "特三等", "特四等",
			"委一等", "委二等", "委三等", "聘四等", "聘五等", "聘六等", "雇七等" };
	private String mobile; // 手机号
	private String email; // 电子邮件
	private String locale = "zh_CN"; // 语言
	private String telephoneNumber; // 分机号
	private String jobGrade; // 职等
	private String jobName; // 职务
	private String birthday; // 生日
	private String supervisor; // 主管工号
	private String supervisorOfSales; // 业务类主管工号
	private String onboardDate; // 入职日期
	private String position; // 岗位资讯
	private String organization = null; // 单位资讯
	private List<Position> positionList; // 岗位列表
	private List<String> memberOfList; // 成员列表
	private String empClassName; // 在职状态
	private String empSubClassName; // 在职状态子分类
	private String empAreaName; // 人事范围
	private String empSubAreaName; // 人事子范围
	private String empClassCode; // 在职状态
	private String empSubClassCode; // 在职状态子分类
	// private String empAreaCode; //人事范围
	private String empSubAreaCode; // 人事子范围
	private String objectSid; // 存在AD内的会有此值
	private byte[] objectSidBytes; // 存在AD内的会有此值
	private boolean groupInlcuded; // 是否需要将memberOf的属性加在Employee物件中
	private String distinguishedName;
	private boolean status;
	private boolean isEmptyPos;
	private String masterId;


	public Employee() {
	}

	public Employee(Attributes attributes) throws NamingException {
		Attribute attr = attributes.get("distinguishedName");
		if (attr != null)
			setDistinguishedName((String) attr.get());

		attr = attributes.get("sn");
		if (attr != null)
			setName((String) attr.get());

		attr = attributes.get("cn");
		if (attr != null)
			setId((String) attr.get());

		attr = attributes.get("distinguishedName");
		if (attr != null)
			setOu((String) attr.get());

		attr = attributes.get("empJobGrade");
		if (attr != null)
			setJobGrade((String) attr.get());

		attr = attributes.get("title");
		if (attr != null)
			setJobName((String) attr.get());

		attr = attributes.get("telephoneNumber");
		if (attr != null)
			setTelephoneNumber((String) attr.get());
		else
			setTelephoneNumber("");

		attr = attributes.get("mobile");
		if (attr != null)
			setMobile((String) attr.get());
		else
			setMobile("");

		attr = attributes.get("supervisor");
		if (attr != null)
			setSupervisor((String) attr.get());

		attr = attributes.get("onboardDate");
		if (attr != null)
			setOnboardDate((String) attr.get());

		attr = attributes.get("empClassName");
		if (attr != null)
			setEmpClassName((String) attr.get());

		attr = attributes.get("empSubClassName");
		if (attr != null)
			setEmpSubClassName((String) attr.get());

		attr = attributes.get("empAreaName");
		if (attr != null)
			setEmpAreaName((String) attr.get());

		attr = attributes.get("empSubAreaName");
		if (attr != null)
			setEmpSubAreaName((String) attr.get());

		attr = attributes.get("empClassCode");
		if (attr != null)
			setEmpClassCode((String) attr.get());

		attr = attributes.get("empSubClassCode");
		if (attr != null)
			setEmpSubClassCode((String) attr.get());

		attr = attributes.get("distinguishedName");
		if (attr != null)
			setOu((String) attr.get());

		attr = attributes.get("empSubAreaCode");
		if (attr != null)
			setEmpSubAreaCode((String) attr.get());

		attr = attributes.get("empGender");
		if (attr != null)
			setGender((String) attr.get());

		setGroupInlcuded(true);

		attr = attributes.get("memberOf");
		if (attr != null) {
			List<String> memberOfList = new ArrayList<String>();
			for (NamingEnumeration<?> vals = attr.getAll(); vals
					.hasMoreElements();) {
				String group = (String) vals.nextElement();
				memberOfList.add(group);
			}
			setMemberOfList(memberOfList);
		}

		attr = attributes.get("mail");
		if (attr != null)
			setEmail((String) attr.get());
		else
			setEmail("");
	}

	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getJobGradeDesc() {
		String jobGradeDesc = goJobGradeDesc(jobGrade);
		return jobGradeDesc;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	private static String goJobGradeDesc(String jobGradeId) {
		String result = jobGradeId;
		try {
			int iJobGradeId = Integer.parseInt(jobGradeId.trim());
			result = jobGradeDesc[iJobGradeId - 1];
		} catch (Exception e) {
		}
		return result;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSupervisorOfSales() {
		return supervisorOfSales;
	}

	public void setSupervisorOfSales(String supervisorOfSales) {
		this.supervisorOfSales = supervisorOfSales;
	}

	public String getEmpClassName() {
		return empClassName;
	}

	public void setEmpClassName(String empClassName) {
		this.empClassName = empClassName;
	}

	public String getEmpSubClassName() {
		return empSubClassName;
	}

	public void setEmpSubClassName(String empSubClassName) {
		this.empSubClassName = empSubClassName;
	}

	public String getEmpAreaName() {
		return empAreaName;
	}

	public void setEmpAreaName(String empAreaName) {
		this.empAreaName = empAreaName;
	}

	public String getEmpSubAreaName() {
		return empSubAreaName;
	}

	public void setEmpSubAreaName(String empSubAreaName) {
		this.empSubAreaName = empSubAreaName;
	}

	public String getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(String onboardDate) {
		this.onboardDate = onboardDate;
	}

	public String getEmpClassCode() {
		return empClassCode;
	}

	public void setEmpClassCode(String empClassCode) {
		this.empClassCode = empClassCode;
	}

	public String getEmpSubClassCode() {
		return empSubClassCode;
	}

	public void setEmpSubClassCode(String empSubClassCode) {
		this.empSubClassCode = empSubClassCode;
	}

	// public String getEmpAreaCode() {
	// return empAreaCode;
	// }
	// public void setEmpAreaCode(String empAreaCode) {
	// this.empAreaCode = empAreaCode;
	// }
	public String getEmpSubAreaCode() {
		return empSubAreaCode;
	}

	public void setEmpSubAreaCode(String empSubAreaCode) {
		this.empSubAreaCode = empSubAreaCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getObjectSid() {
		return objectSid;
	}

	public void setObjectSid(String objectSid) {
		this.objectSid = objectSid;
	}

	public byte[] getObjectSidBytes() {
		return objectSidBytes;
	}

	public void setObjectSidBytes(byte[] objectSidBytes) {
		this.objectSidBytes = objectSidBytes;
	}

	public String toString() {
		StringBuffer positions = new StringBuffer();
		StringBuffer memberOfs = new StringBuffer();
		if (positionList != null && positionList.size() > 0) {
			for (int i = 0; i < positionList.size(); i++) {
				Position position = (Position) positionList.get(i);
				Organization organization = position.getOrganization();
				if (organization != null)
					positions.append(organization.toString() + "\n");
				if (position != null)
					positions.append(position.toString());
			}
		}

		if (memberOfList != null && memberOfList.size() > 0) {
			for (int i = 0; i < memberOfList.size(); i++) {
				String group = (String) memberOfList.get(i);
				memberOfs.append(group + "\n");
			}
		}

		StringBuffer result = new StringBuffer();
		result.append("工号:").append(getId()).append("\n");

		result.append("\t姓名:").append(getName());
		result.append("\t分机号:").append(getTelephoneNumber());
		result.append("\t手机号:").append(getMobile()).append("\n");

		result.append("\t职等:").append(getJobGrade());
		result.append("\t职等描述:").append(getJobGradeDesc());
		result.append("\t职务:").append(getJobName()).append("\n");

		result.append("\t入职日期:").append(getOnboardDate()).append("\n");

		result.append("\t在职状态码:").append(getEmpClassCode());
		result.append("\t在职状态子类别码:").append(getEmpSubClassCode()).append("\n");

		result.append("\t在职状态:").append(getEmpClassName());
		result.append("\t在职状态子类别:").append(getEmpSubClassName()).append("\n");

		if (getOrganization() != null)
			result.append("\n\t单位:").append(getOrganization()).append("\n");

		if (positionList != null && positionList.size() > 0)
			result.append("\n岗位与单位资讯:\n").append(positions);

		if (memberOfList != null && memberOfList.size() > 0)
			result.append("\n所属成员(Member Of):\n").append(memberOfs)
					.append("\n");

		return result.toString();
	}

	public void setMemberOfList(List<String> memberOfList) {
		this.memberOfList = memberOfList;
	}

	public List<String> getMemberOfList() {
		return memberOfList;
	}

	public void setGroupInlcuded(boolean groupInlcuded) {
		this.groupInlcuded = groupInlcuded;
	}

	public boolean isGroupInlcuded() {
		return groupInlcuded;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isEmptyPos() {
		return isEmptyPos;
	}

	public void setEmptyPos(boolean isEmptyPos) {
		this.isEmptyPos = isEmptyPos;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			Employee emp = (Employee) obj;
			return getId().equals(emp.getId());
		}
		return super.equals(obj);
	}
}
