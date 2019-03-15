package com.want.mq.email.service;

import java.util.Map;
import com.want.mq.model.PublicEmail;

public interface IEmailService {

	public void sendSimpleMail(PublicEmail email);
	public void sendHtmlMail(PublicEmail email);
	public void sendAttachmentMail(PublicEmail email,Map<String,byte[]> attachs);
}
