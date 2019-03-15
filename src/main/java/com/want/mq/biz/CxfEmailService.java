package com.want.mq.biz;


import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.want.mq.model.PublicEmail;
import com.want.mq.producer.service.IRabbitMqService;

/**
 * cxf方式发送邮件
 * @author 00291315
 *
 */
@Component
@WebService(serviceName="sendEmailService",  targetNamespace="http://com.want.email/",endpointInterface = "com.want.mq.biz.ICxfEmailService")
public class CxfEmailService  implements ICxfEmailService{
	
	private Logger logger = LoggerFactory.getLogger(CxfEmailService.class);

	@Autowired
	private IRabbitMqService iRabbitMqService;

	
	@Override
	public void sendHtmlMail(PublicEmail email) {
		iRabbitMqService.send(email);
	}

}
