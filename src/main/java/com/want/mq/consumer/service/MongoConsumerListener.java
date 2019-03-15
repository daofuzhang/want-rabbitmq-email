package com.want.mq.consumer.service;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.want.mq.model.PublicEmail;
import com.want.mq.mongo.service.IMongoService;
import com.want.mq.util.Constant;
import com.want.mq.util.JsonUtil;
import com.want.mq.util.StringUtil;

/**
 * @author 00291315
 * 将队列中的邮件信息保存到mongodb中
 *
 */
@Component
@RabbitListener(queues = Constant.MONGODB_TOPIC_QUEUE)
public class MongoConsumerListener {
	
	private Logger logger=Logger.getLogger(MongoConsumerListener.class);

	@Autowired
	private IMongoService iMongoService;
	@Value("${want.mail.sender}")
	private String sender;
	
	@RabbitHandler
	public void process(String obj) {
		if (StringUtils.isEmpty(obj)) {
			return;
		}
		try {
			PublicEmail email = JsonUtil.parseToClass(obj, PublicEmail.class);
			if(null==email.getSender()) {
				email.setSender(sender);
			}
			// 邮件抄送者
			List<String> ccs = StringUtil.convertEmailRec(email.getCc());
			if (null != ccs && ccs.size() > 0) {
				email.setCc(ccs);
			}
			// 邮件接收者
			List<String> recipients = StringUtil.convertEmailRec(email.getRecipients());
			if (null != recipients && recipients.size() > 0) {
				email.setRecipients(recipients);
			}
			email.setCreateDate(new Date());
			iMongoService.insertEmail(email);
		} catch (Exception e) {
			logger.error("MongoConsumerListener iMongoService desc",e);
		}

	}
}
