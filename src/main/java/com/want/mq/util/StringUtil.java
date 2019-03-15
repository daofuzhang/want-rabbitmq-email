package com.want.mq.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.want.mq.ldap.AdminIdentitySessionBean;
import com.want.mq.model.Employee;

public class StringUtil {
	
	private static Logger logger=LoggerFactory.getLogger(StringUtil.class);
	private static AdminIdentitySessionBean adminIdentity=null;
	static {
		if(null==adminIdentity) {
			adminIdentity=new AdminIdentitySessionBean();
			logger.info("ladp connection...");
		}
	}
	public static List<String> convertEmailRec(List<String> addressList) {
        if(null!=addressList && addressList.size()>0) {
        	Set<String> result=new HashSet<String>();
        	//通过传入的工号查询ladp，查询结果获取邮箱。
        	List<String> validList=new ArrayList<String>();
        	for(String address:addressList) {
        		String empId = null;
        		if(address.contains("@")) {
        			empId = address.substring(0, address.indexOf("@"));
        		}else {
        			empId = address;
        		}
				if (isNumeric(empId)) {
					try {
					    Employee emp = adminIdentity.getEmployeeById(empId);
					    validList.add(emp.getEmail());
					    logger.info(empId+"ladp emial address ="+emp.getEmail());
					    
					}catch(Exception e) {
						logger.error("ladp error get employee",e);
					}
				}else {
					validList.add(address);
				}
        	}
        	//组装邮箱
        	for(String address:validList) {
        		if(address.contains("@")) {
        			result.add(address);
        		}else {
        			result.add(address+"@want-want.com");
        		}
        	}
        	return new ArrayList(result);
        }
        return null;
	}
	
	public static String[] convertEmail(List<String> addressList) {
        if(null!=addressList && addressList.size()>0) {
        	List<String> result=new ArrayList<String>();
        	//通过传入的工号查询ladp，查询结果获取邮箱。
        	List<String> validList=new ArrayList<String>();
        	for(String address:addressList) {
        		String empId = null;
        		if(address.contains("@")) {
        			empId = address.substring(0, address.indexOf("@"));
        		}else {
        			empId = address;
        		}
				if (isNumeric(empId)) {
					try {
					    Employee emp = adminIdentity.getEmployeeById(empId);
					    validList.add(emp.getEmail());
					    logger.info(empId+"ladp emial address ="+emp.getEmail());
					    
					}catch(Exception e) {
						logger.error("ladp error get employee",e);
					}
				}else {
					validList.add(address);
				}
        	}
        	//组装邮箱
        	for(String address:validList) {
        		if(address.contains("@")) {
        			result.add(address);
        		}else {
        			result.add(address+"@want-want.com");
        		}
        	}
        	String[] to = new String[result.size()];
        	return result.toArray(to);
        }
        return null;
	}
	
	
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean isNull(String obj) {
		if(null == obj || obj.trim().length()==0) {
			return true;
		}
		return false;
	}	
}
