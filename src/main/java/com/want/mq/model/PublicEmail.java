package com.want.mq.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 00291315
 * 邮件实体类
 */
public class PublicEmail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mailTitle;
	private String mailContent;
	private String sender = null;
	private List<String> recipients = null;
	private List<String> cc = null;
	private Date createDate;
	
	public String getMailTitle() {
		return mailTitle;
	}
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public List<String> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "PublicEmail [mailTitle=" + mailTitle + ", mailContent=" + mailContent + ", sender=" + sender
				+ ", recipients=" + recipients + ", cc=" + cc + ", createDate=" + createDate + "]";
	}
	
	
}
