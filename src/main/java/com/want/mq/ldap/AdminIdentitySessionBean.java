package com.want.mq.ldap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session Bean implementation class AdminIdentitySessionBean
 */

public class AdminIdentitySessionBean extends IdentityService {
	private static Logger log = LoggerFactory.getLogger(AdminIdentitySessionBean.class);
	public AdminIdentitySessionBean() {
		super(Constant.ADMIN_TYPE);
	}	
}
