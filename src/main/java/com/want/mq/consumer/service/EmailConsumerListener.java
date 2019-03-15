package com.want.mq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.want.mq.email.service.IEmailService;
import com.want.mq.model.PublicEmail;
import com.want.mq.util.Constant;
import com.want.mq.util.JsonUtil;

/**
 * @author 00291315
 * 邮件监听类
 */
@Component
@RabbitListener(queues = Constant.EMAIL_TOPIC_QUEUE )
public class EmailConsumerListener {
	
	private Logger logger=LoggerFactory.getLogger(EmailConsumerListener.class);

	@Autowired
	private IEmailService iMailService;

	@RabbitHandler
	public void process(String obj) {
		if (StringUtils.isEmpty(obj)) {
			return;
		}
		try {
			PublicEmail email = JsonUtil.parseToClass(obj, PublicEmail.class);
			iMailService.sendHtmlMail(email);
		} catch (Exception e) {
			logger.error("EmailConsumerListener sendsimpleemail error desc",e);
		}
	}
}
