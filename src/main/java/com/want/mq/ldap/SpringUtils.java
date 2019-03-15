package com.want.mq.ldap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/****************************
 * http://i.want-want.com
 *
 * @Description:
 * @version: v1.0.0
 * @author: 00291315
 * @date: 2019年1月18日 上午10:48:55 Modification History: 1. 00291315 2019年1月18日
 *        初始创建
 *******************************/
@Component
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtils.applicationContext == null) {
			SpringUtils.applicationContext = applicationContext;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	public static Object getBean(String name) throws BeansException {
		return getApplicationContext().getBean(name);
	}

}
