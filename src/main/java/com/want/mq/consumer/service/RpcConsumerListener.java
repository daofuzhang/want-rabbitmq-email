package com.want.mq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReceiveAndReplyMessageCallback;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.want.mq.util.Constant;

@Component
@RabbitListener(queues = Constant.RPC_QUEUE_P)
public class RpcConsumerListener {
	
	private Logger logger=LoggerFactory.getLogger(RpcConsumerListener.class);
	@Autowired
    private AmqpTemplate rabbitTemplate;

	@RabbitHandler
	public String process(String obj) {
		/*logger.debug("RpcConsumerListener receive msg ="+obj);
		rabbitTemplate.receiveAndReply(Constant.RPC_QUEUE_C, new ReceiveAndReplyMessageCallback(){
			@Override
			public Message handle(Message payload) {
				String mesg= "rec msg :"+  obj;
				Message msg=new Message(mesg.getBytes(), payload.getMessageProperties());
				return msg;
			}
		}, Constant.RPC_EXCHANGE, Constant.RPC_ROUTER_KEY);*/
		logger.debug("rpc rec msg ="+obj);
		return "rpc rec msg ="+obj;
	}

}
