package com.want.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/****************************   
* http://i.want-want.com
*
* @Description: 
* @version: v1.0.0
* @author: 00291315
* @date: 2019年1月18日 上午10:38:22 
* Modification History: 
* 1.  00291315  2019年1月18日  初始创建
*******************************/
@Configuration
public class LdapConfig {

	@Value("${admin.ldap.base_dn}")
	private  String ldapBaseDN;
	@Value("${admin.ldap.hostname}")
	private String ldapHostname;
	@Value("${admin.ldap.port}")
	private String ldapPort;
	@Value("${admin.ldap.username}")
	private String ldapUsername;
	@Value("${admin.ldap.password}")
	private String ldapPassword;
	@Value("${com.sun.jndi.ldap.connect.pool}")
	private String pooling ;
	@Value("${com.sun.jndi.ldap.connect.pool.maxsize}")
	private String poolMaxsize;
	public String getLdapBaseDN() {
		return ldapBaseDN;
	}
	public void setLdapBaseDN(String ldapBaseDN) {
		this.ldapBaseDN = ldapBaseDN;
	}
	public String getLdapHostname() {
		return ldapHostname;
	}
	public void setLdapHostname(String ldapHostname) {
		this.ldapHostname = ldapHostname;
	}
	public String getLdapPort() {
		return ldapPort;
	}
	public void setLdapPort(String ldapPort) {
		this.ldapPort = ldapPort;
	}
	public String getLdapUsername() {
		return ldapUsername;
	}
	public void setLdapUsername(String ldapUsername) {
		this.ldapUsername = ldapUsername;
	}
	public String getLdapPassword() {
		return ldapPassword;
	}
	public void setLdapPassword(String ldapPassword) {
		this.ldapPassword = ldapPassword;
	}
	public String getPooling() {
		return pooling;
	}
	public void setPooling(String pooling) {
		this.pooling = pooling;
	}
	public String getPoolMaxsize() {
		return poolMaxsize;
	}
	public void setPoolMaxsize(String poolMaxsize) {
		this.poolMaxsize = poolMaxsize;
	}
	@Override
	public String toString() {
		return "LdapConfig [ldapBaseDN=" + ldapBaseDN + ", ldapHostname=" + ldapHostname + ", ldapPort=" + ldapPort
				+ ", ldapUsername=" + ldapUsername + ", ldapPassword=" + ldapPassword + ", pooling=" + pooling
				+ ", poolMaxsize=" + poolMaxsize + "]";
	}

}
