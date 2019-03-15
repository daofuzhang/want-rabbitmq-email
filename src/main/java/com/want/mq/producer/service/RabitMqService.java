package com.want.mq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.want.mq.model.PublicEmail;
import com.want.mq.util.Constant;
import com.want.mq.util.JsonUtil;

/**
 * 
 * @author 00291315
 * rabbitmq 处理类
 */
@Service
public class RabitMqService implements IRabbitMqService {
	
	private Logger logger=LoggerFactory.getLogger(RabitMqService.class);
	
	@Autowired
    private AmqpTemplate rabbitTemplate;

	/**
	 * 发送消息到生产者 
	 * 交换机EMAIL_EXCHANGE
	 * 路由：EMAIL_ROUTER_KEY
	 */
	public void send(PublicEmail email) {
		rabbitTemplate.convertAndSend(Constant.EMAIL_EXCHANGE, Constant.EMAIL_ROUTER_KEY, JsonUtil.parseToJSON(email));
	}
	
	public void rpcSendAndRec(String obj) {
		Object msg= rabbitTemplate.convertSendAndReceive(Constant.RPC_EXCHANGE_P, Constant.RPC_ROUTER_KEY,obj);
		logger.debug("rpcSendAndRec receive msg ="+msg);
	}

}
