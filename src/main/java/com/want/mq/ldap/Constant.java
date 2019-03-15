package com.want.mq.ldap;

public class Constant {
	
	//行政或业务
	public static final String ADMIN_TYPE = "ADMIN";
	public static final String SALES_TYPE = "SALES";
	

	//人事范围
	public static final String SCOPE_ID_HQ = "WWZB";//总部
	
	//陆籍员工的EmpClassCode
	public static final String EMP_CLASS_CODE_LUJI_A = "A";
	public static final String EMP_CLASS_CODE_LUJI_H = "H";
	public static final String EMP_CLASS_CODE_LUJI_J = "J";
	
	//级别
	public static final int LEVEL_EMPLOYEE = 1;//员工
	public static final int LEVEL_DIRECTOR = 50;//其他低阶主管
	public static final int LEVEL_DEPARTMENT_DIRECTOR = 100;//部门主管
	public static final int LEVEL_DIVISION_DIRECTOR = 200;//单位主管
	public static final int LEVEL_DIVISION_TOP_DIRECTOR = 300;//单位最高主管
	public static final int LEVEL_BG_TOP_DIRECTOR = 325;//事业群最高主管
	public static final int LEVEL_VICE_PRESIDENT = 350;//副总裁
	public static final int LEVEL_COO = 400;//COO
	public static final int LEVEL_PRESIDENT = 500;//总裁
	
	//职等
	public static final int JOB_GRADE_WEI2 = 6;//委二
	public static final int JOB_GRADE_WEI3 = 7;//委三
	
	//部门主管的OrgLevel
	public static final int DEPT_SUPERVISOR_ORG_LEVEL_HQ = 7;
	public static final int DEPT_SUPERVISOR_ORG_LEVEL_LOCAL = 8;
	
	//事业群OrgLevel
	public static final int ORG_LEVEL_BG = 3;
	
	public static final int FLAG_NOT_NEED_APPROVAL = 0;//按级别签核时的无需签核标志
	public static final int FLAG_NOT_NEED_APPROVAL_JOB_GRADE = 100;//按职等签核时的无需签核标志
	
	//循环签核结束标志
	public static final String FLAG_APPROVAL_END = "END";
	
	//URL参数
	public static final String URL_PARAMETER_PROCESS_ID = "ProcessId";
	public static final String URL_PARAMETER_DRAFT_ID = "DraftId";
	public static final String URL_PARAMETER_SHOW_HISTORY = "ShowHistory";
	
	//URL中用于判断的参数的值
	public static final String URL_VALUE_YES = "Y";
	public static final String URL_VALUE_NO = "N";
	
	//审批动作
	public static final String ACTION_APPROVE = "Y";//核准
	public static final String ACTION_REJECT = "N";//驳回
	
	//关卡为系统时
	public static final String SYSTEM_APPROVER_SAP_ID = "SAP";
	public static final String SYSTEM_APPROVER_SAP_NAME = "SAP系统";
	
	//Session Bean
	public static final String JNDI_NAME_USER_MANAGER = "ejb:/appName=com.want/common~util~ear, jarName=com.want~common~util~ejb.jar, beanName=UserManagerSessionBean, interfaceName=com.want.common.util.ejb.UserManagerSessionBeanLocal";
	public static final String JNDI_NAME_COMMON = "ejb:/appName=com.want/common~util~ear, jarName=com.want~common~util~ejb.jar, beanName=CommonSessionBean, interfaceName=com.want.common.util.ejb.CommonSessionBeanLocal";
	public static final String JNDI_NAME_SERIALS = "ejb:/appName=com.want/common~util~ear, jarName=com.want~common~util~ejb.jar, beanName=SerialsSessionBean, interfaceName=com.want.common.util.ejb.SerialsSessionBeanLocal";
	public static final String JNDI_NAME_HRSCOPE = "ejb:/appName=com.want/stand~ml~ear, jarName=com.want~stand~ml~ejb.jar, beanName=HrscopeFacade, interfaceName=com.want.ml.crud.HrscopeFacadeLocal";
	public static final String JNDI_NAME_SEND_MAIL = "ejb:/appName=com.want/common~util~ear, jarName=com.want~common~util~mail~ejb.jar, beanName=SendMailBean, interfaceName=com.want.common.util.mail.ejb.SendMailBeanLocal";
	
	//分隔符
	public static final String SEPARATOR_MAIL_ADDRESSES = ",";//多个邮件地址时的分隔符
	public static final String SEPARATOR_ATTACHMENT_FILE_ID = "@";//附件文件ID的分隔符
	
	//task描述里的属性名
	public static final String TASK_DESC_PROP_NAME_SUB_PROCESS = "Sub-Process";
	
	//表FORM_INFO中的SAVEHISTORYINFO字段，该表单的历史是否存储业务数据（FORM_MASTER_INFO中FORMINFO的数据）
	public static final String SAVE_HISTORY_INFO_YES = "T";//存储
	
	//表FORM_HISTORY_INFO中的APPROVESTATE字段，签核结果
	public static final String APPROVE_STATE_START="S";
	public static final String APPROVE_STATE_IN_PROCESSING="N";
	public static final String APPROVE_STATE_AGREE="A";
	public static final String APPROVE_STATE_REJECT="R";
	public static final String APPROVE_STATE_DELETE="D";
	public static final String APPROVE_STATE_CANCEL="C";
	public static final String APPROVE_STATE_WEIPAI="W";
	
	//表FORM_MASTER_INFO中的AGENCYSTATE字段，代理状态
	public static final String AGENCY_STATE_YES = "T";//是代理
	//表FORM_MASTER_INFO的PROCESSSTATE字段
	public static final String PROCESS_STATE_PROCESSING = "N";//签核中
	public static final String PROCESS_STATE_REJECT = "R";//驳回
	public static final String PROCESS_STATE_END = "E";//已结案
	public static final String PROCESS_STATE_CANCEL = "C";//流程取消
	
	//表FORM_TMP_MASTER_INFO中的FORMRESULT字段
	public static final String FORM_RESULT_TMP = "T";//暂存中
	public static final String FORM_RESULT_PROCESSING = "N";//签核中
	public static final String FORM_RESULT_AGREE = "A";//核准
	public static final String FORM_RESULT_REJECT = "R";//驳回
	public static final String FORM_RESULT_CANCEL = "C";//表单取消
	
	//表HRSCOPE中的PROPERTY字段
	public static final String HRSCOPE_PROPERTY_HQ = "1";//总部
	public static final String HRSCOPE_PROPERTY_GENERAL_PLANT = "3";//总厂
	
	public static final String USER_OBJECT_CLASS = "(|(objectClass=wantWantOrgPerson)(objectClass=wantWantUserProxyFull))";
	public static final String POSITION_OBJECT_CLASS = "(objectClass=groupOfWantWantPositions)";
	public static final String GROUP_OBJECT_CLASS = "(objectClass=group)";
	public static final String EMPLOYEE_OBJECT_CLASS = "(wantWantUserProxyFull)";
}
