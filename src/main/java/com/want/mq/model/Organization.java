package com.want.mq.model;

import java.io.Serializable;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class Organization implements Serializable{
	private static final long serialVersionUID = 7583238409942345751L;
	private String id;
	private String name;
	private String areaId;
	private String ou;
	private String parentDept;	
	
	private List<String> member;
	
	public Organization() {
	}
	
	public Organization(Attributes attributes)
			throws NamingException {

		Attribute attr = attributes.get("cn");
		if (attr != null)
			setId((String) attr.get());

		attr = attributes.get("description");
		if (attr != null)
			setName((String) attr.get());

		attr = attributes.get("adminDescription");
		if (attr != null)
			setOrgLevel((String) attr.get());
	}

	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel.trim();
	}
	private String orgLevel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getParentDept() {
		return parentDept;
	}
	public void setParentDept(String parentDept) {
		this.parentDept = parentDept;
	}		
	
	public List<String> getMember() {
		return member;
	}
	
	public void setMember(List<String> member) {
		this.member = member;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Organization) {
			Organization org = (Organization) obj;
			return getId().equals(org.getId());
		}
		return super.equals(obj);
	}

	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append("\t部门代码:").append(getId());
		temp.append("\t部门名:").append(getName());
		temp.append("\t部门层级:").append(getOrgLevel());	
		return temp.toString();
	}		
}
