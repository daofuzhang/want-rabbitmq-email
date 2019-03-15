package com.want.mq.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 00291315
 * 邮件实体类
 */
public class PublicEmailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mailTitle;
	private String mailContent;
	private String sender = null;
	private String recipient = null;
	private String cc = null;
	private Date begin;
	private Date end;
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
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "PublicEmailDTO [mailTitle=" + mailTitle + ", mailContent=" + mailContent + ", sender=" + sender
				+ ", recipient=" + recipient + ", cc=" + cc + ", begin=" + begin + ", end=" + end + ", createDate="
				+ createDate + "]";
	}

}
