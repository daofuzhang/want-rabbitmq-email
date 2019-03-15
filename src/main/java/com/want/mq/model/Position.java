package com.want.mq.model;

import java.io.Serializable;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;


public class Position implements Serializable {

	private static final long serialVersionUID = 6992381944357039936L;
	private String id;
	private String name;
	private String orgId;
	private String masterId;
	private boolean isDepartmentDirector;
	private boolean isDivisionDirector;
	private boolean isTopDirector;
	private String areaCode;
	private String supervisor;
	private String supervisorPosId;
	private String ou;
	private Organization organization;
	private Employee employee;
	
	private String supportEmpId;//支援岗的上级empid
	private String supportPosId ;//支援岗的上级Posid
	
	/*
	 * Propertey add by wangyicheng
	 */
	private String pos_property_id = "";//add ="" by nw004
	private String pos_property_name;
	
	private String typeId;
	private String typeName;
	private String director_pos_id;
	
	//add by zhangl on 20160817 
	private String directorType;//几级主管

	
	public Position() {
		
	}
	
	public Position(Attributes attributes) throws NamingException {
		Attribute attr = attributes.get("cn");
		if (attr != null)
			setId((String) attr.get());

		attr = attributes.get("description");
		if (attr != null)
			setName((String) attr.get());

		attr = attributes.get("businessCategory");
		if (attr != null)
			setMasterId((String) attr.get());

		attr = attributes.get("deptDirector");
		if (attr != null && attr.get().equals("Y")) {
			String value = (String) attr.get();
			if (value.equals("Y"))
				setDepartmentDirector(true);
		}

		attr = attributes.get("divisionDirector");
		if (attr != null && attr.get().equals("Y")) {
			String value = (String) attr.get();
			if (value.equals("Y"))
				setDivisionDirector(true);
		}

		attr = attributes.get("topDirector");
		if (attr != null && attr.get().equals("Y")) {
			String value = (String) attr.get();
			if (value.equals("Y"))
				setTopDirector(true);
		}

		attr = attributes.get("supervisor");
		if (attr != null)
			setSupervisor((String) attr.get());

		attr = attributes.get("supervisorPosId");
		if (attr != null)
			setSupervisorPosId((String) attr.get());

		attr = attributes.get("empAreaCode");
		if (attr != null)
			setAreaCode((String) attr.get());

		attr = attributes.get("supportEmpId");
		if (attr != null)
			setSupportEmpId((String) attr.get());

		attr = attributes.get("supportPosId");
		if (attr != null)
			setSupportPosId((String) attr.get());

		/* add by wang yi cheng */
		attr = attributes.get("posPropertyId");
		if (attr != null)
			setPos_property_id((String) attr.get());

		attr = attributes.get("posPropertyName");
		if (attr != null)
			setPos_property_name((String) attr.get());
		
		attr = attributes.get("posTypeId");
		if (attr != null)
			this.setTypeId((String) attr.get());
		
		attr = attributes.get("posTypeName");
		if (attr != null)
			this.setTypeName((String) attr.get());
		
		//add by zhangl on 20160817 
		attr = attributes.get("directorType01");
		
		if (attr != null)
			this.setDirectorType((String) attr.get());
		//end
	}
	
	
	
	public String getDirectorType() {
		return directorType;
	}

	public void setDirectorType(String directorType) {
		this.directorType = directorType;
	}

	public String getSupportEmpId() {
		return supportEmpId;
	}

	public void setSupportEmpId(String supportEmpId) {
		this.supportEmpId = supportEmpId;
	}

	public String getSupportPosId() {
		return supportPosId;
	}

	public void setSupportPosId(String supportPosId) {
		this.supportPosId = supportPosId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public boolean isDepartmentDirector() {
		return isDepartmentDirector;
	}

	public void setDepartmentDirector(boolean isDepartmentDirector) {
		this.isDepartmentDirector = isDepartmentDirector;
	}

	public boolean isDivisionDirector() {
		return isDivisionDirector;
	}

	public void setDivisionDirector(boolean isDivisionDirector) {
		this.isDivisionDirector = isDivisionDirector;
	}

	public boolean isTopDirector() {
		return isTopDirector;
	}

	public void setTopDirector(boolean isTopDirector) {
		this.isTopDirector = isTopDirector;
	}

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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSupervisorPosId() {
		return supervisorPosId;
	}

	public void setSupervisorPosId(String supervisorPosId) {
		this.supervisorPosId = supervisorPosId;
	}

	
	
	public String getPos_property_id() {
		return pos_property_id;
	}

	public void setPos_property_id(String posPropertyId) {
		pos_property_id = posPropertyId;
	}

	public String getPos_property_name() {
		return pos_property_name;
	}

	public void setPos_property_name(String posPropertyName) {
		pos_property_name = posPropertyName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getDirector_pos_id() {
		return director_pos_id;
	}

	public void setDirector_pos_id(String directorPosId) {
		director_pos_id = directorPosId;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position pos = (Position) obj;
			return getId().equals(pos.getId());
		}
		return super.equals(obj);
	}

	public String toString() {
		
		StringBuffer temp = new StringBuffer();
		temp.append("\t岗位代码:").append(getId());
		temp.append("\t岗位名:").append(getName());
		temp.append("\t岗位顺序:").append(getMasterId()).append("\n");

		temp.append("\t部门主管:").append(isDepartmentDirector());
		temp.append("\t单位主管:").append(isDivisionDirector);
		temp.append("\t单位最高主管:").append(isTopDirector).append("\n");

		temp.append("\t人事范围代码:").append(getAreaCode());
		temp.append("\t上级主管工号:").append(getSupervisor());
		temp.append("\t上级主管岗位代码:").append(getSupervisorPosId()).append("\n\n");

		if (getEmployee() != null)
			temp.append("员工资讯:\n" + getEmployee() + "\n");

		return "[ "+temp.toString() +" ]";
	}
}
