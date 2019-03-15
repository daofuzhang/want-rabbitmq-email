package com.want.mq.email.service;

import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.want.mq.model.PublicEmail;
import com.want.mq.util.StringUtil;

/**
 * 邮件发送类 2018-12-12 09:57:01
 * 
 * @author 00291315
 *
 */
@Service
public class EmailService implements IEmailService {

	private Logger logger = LoggerFactory.getLogger(EmailService.class);
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${want.mail.sender}")
	private String sender;

	/**
	 * 发送普通邮件 00291315 2018-12-12 09:56:56
	 */
	@Override
	public void sendSimpleMail(PublicEmail email) {

		logger.debug("email desc " + email.toString());

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		// 邮件发送者
		simpleMailMessage.setFrom(StringUtil.isNull(email.getSender())?  sender : email.getSender());
		// 邮件抄送者
		String[] ccs = StringUtil.convertEmail(email.getCc());
		if (null != ccs && ccs.length > 0) {
			simpleMailMessage.setCc(ccs);
		}
		// 邮件接收者
		String[] recipients = StringUtil.convertEmail(email.getRecipients());
		if (null != recipients && recipients.length > 0) {
			simpleMailMessage.setTo(recipients);
		}
		// 邮件主题
		simpleMailMessage.setSubject(email.getMailTitle());
		// 邮件内容
		simpleMailMessage.setText(email.getMailContent());
		// 发送邮件
		try {
			mailSender.send(simpleMailMessage);
		} catch (Exception e) {
			logger.error("send mail error", e);
		}
	}

	/**
	 * 发送html邮件
	 */
	@Override
	public void sendHtmlMail(PublicEmail email) {
		try {
		System.out.println("email   ===>"+email.toString());
			
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			// 邮件发送者
			helper.setFrom(email.getSender()==null?sender:email.getSender());
			// 邮件抄送者
			String[] ccs = StringUtil.convertEmail(email.getCc());
			if (null != ccs && ccs.length > 0) {
				helper.setCc(ccs);
			}
			// 邮件接收者
			String[] recipients = StringUtil.convertEmail(email.getRecipients());
			if (null != recipients && recipients.length > 0) {
				helper.setTo(recipients);
			}
			// 邮件主题
			helper.setSubject(email.getMailTitle());
			// 邮件内容
			helper.setText(email.getMailContent(), true);
			// 发送邮件
			mailSender.send(message);
		} catch (MessagingException e) {
			logger.error("sendHtmlMail error desc", e);
		}
	}

	/**
	 * 发送带附件的邮件 00291315 2018-12-12 11:17:33
	 */
	@Override
	public void sendAttachmentMail(PublicEmail email, Map<String, byte[]> attachs) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			// 邮件发送者
			helper.setFrom(email.getSender()==null?sender:email.getSender());
			// 邮件抄送者
			String[] ccs = StringUtil.convertEmail(email.getCc());
			if (null != ccs && ccs.length > 0) {
				helper.setCc(ccs);
			}
			// 邮件接收者
			String[] recipients = StringUtil.convertEmail(email.getRecipients());
			if (null != recipients && recipients.length > 0) {
				helper.setTo(recipients);
			}
			// 邮件主题
			helper.setSubject(email.getMailTitle());
			// 邮件内容
			helper.setText(email.getMailContent());
			//
			for (String fileName : attachs.keySet()) {
				InputStreamSource inputStreamSource = new ByteArrayResource(attachs.get(fileName));
				helper.addAttachment(fileName, inputStreamSource);
			}
			// 发送邮件
			mailSender.send(message);
		} catch (MessagingException e) {
			logger.error("sendHtmlMail error desc", e);
		}
	}
}
