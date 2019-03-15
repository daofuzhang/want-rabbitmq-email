package com.want.mq.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.want.mq.model.PublicEmail;
import com.want.mq.producer.service.IRabbitMqService;

/**
 * 邮件发送类 2018-12-12 09:57:01
 * 
 * @author 00291315
 *
 */
@Service("hessianEmailService")
public class HessianEmailService implements IHessianEmailService {

	private Logger logger = LoggerFactory.getLogger(HessianEmailService.class);


	@Autowired
	private IRabbitMqService iRabbitMqService;

	/**
	 * 发送html邮件
	 */
	@Override
	public void sendHtmlMail(PublicEmail email) {
			iRabbitMqService.send(email);
	}

	
}
