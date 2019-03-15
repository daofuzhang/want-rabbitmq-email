package com.want.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.want.mq.util.Constant;

/**
 * 初始化rabbitmq配置类
 * 
 * @author 00291315
 * 
 * 发送方将消息发送到交换机
 * 队列和交换机绑定
 * 消费者去队列中取数据
 * send -> exchange <- queue (bind) <- consumer
 * 
 */

@Configuration
public class RabbitConfig {
	/**
	 * 创建发送邮件队列
	 * 
	 * @return Queue
	 */
	@Bean("emailQueue")
	public Queue emailQueue() {
		return new Queue(Constant.EMAIL_TOPIC_QUEUE, Constant.DURABLE, Constant.EXCLUSIVE, Constant.AUTODELETE, null);
	}
	/**
	 * 创建mongo队列
	 * @return
	 */
	@Bean("mongoQueue")
	public Queue mongoQueue() {
		return new Queue(Constant.MONGODB_TOPIC_QUEUE, Constant.DURABLE, Constant.EXCLUSIVE, Constant.AUTODELETE, null);
	}
	/**
	 * rpc队列
	 * @return
	 */
	@Bean("rpcQueuep")
	public Queue rpcQueueP() {
		return new Queue(Constant.RPC_QUEUE_P, Constant.DURABLE, Constant.EXCLUSIVE, Constant.AUTODELETE, null);
	}
	@Bean("rpcQueuec")
	public Queue rpcQueueC() {
		return new Queue(Constant.RPC_QUEUE_C, Constant.DURABLE, Constant.EXCLUSIVE, Constant.AUTODELETE, null);
	}
	/**
	 * 创建交换机
	 * @return
	 */
	@Bean("emailExchange")
	public TopicExchange emailExchange() {
		return new TopicExchange(Constant.EMAIL_EXCHANGE, Constant.DURABLE, Constant.AUTODELETE, null);
	}
	@Bean("rpcExchangep")
	public TopicExchange rpcExchangep() {
		return new TopicExchange(Constant.RPC_EXCHANGE_P, Constant.DURABLE, Constant.AUTODELETE, null);
	}
	@Bean("rpcExchangec")
	public TopicExchange rpcExchangec() {
		return new TopicExchange(Constant.RPC_EXCHANGE_C, Constant.DURABLE, Constant.AUTODELETE, null);
	}
	/**
	 * 将队列绑定到交换机上去
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeMessageEmail(@Qualifier("emailQueue") Queue queueMessage,@Qualifier("emailExchange")TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(Constant.EMAIL_ROUTER_KEY);
	}
	/**
	 * 将队列绑定到交换机上去
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeMessageMongo(@Qualifier("mongoQueue") Queue queueMessage,@Qualifier("emailExchange")TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(Constant.EMAIL_ROUTER_KEY);
	}
	/**
	 * rpc交换机绑定,发送和接收方都绑定同一个交换机
	 */
	@Bean
	public Binding bindingExchangeMessageRpcP(@Qualifier("rpcQueuep") Queue queueMessage,@Qualifier("rpcExchangep")TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(Constant.RPC_ROUTER_KEY);
	}
	@Bean
	public Binding bindingExchangeMessageRpcC(@Qualifier("rpcQueuec") Queue queueMessage,@Qualifier("rpcExchangec")TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(Constant.RPC_ROUTER_KEY);
	}

}
