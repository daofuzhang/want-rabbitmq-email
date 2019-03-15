package com.want.mq.util;

public class Constant {
	
	//队列名称
	public static final String EMAIL_TOPIC_QUEUE ="want.email.queue";
	public static final String MONGODB_TOPIC_QUEUE ="want.mongo.queue";
	public static final String RPC_QUEUE_P ="want.rpc.queue.p";
	public static final String RPC_QUEUE_C ="want.rpc.queue.c";
	
	//持久化
	public static final boolean DURABLE =true;
	//如果你想创建一个只有自己可见的队列，即不允许其它用户访问，RabbitMQ允许你将一个Queue声明成为排他性的（Exclusive Queue）
	public static final boolean EXCLUSIVE=false;
	//自动删除
	public static final boolean AUTODELETE=false;
	//交换机名称
	public static final String EMAIL_EXCHANGE="want.email.exchange";
	public static final String RPC_EXCHANGE_P="want.rpc.exchange.p";
	public static final String RPC_EXCHANGE_C="want.rpc.exchange.c";
	//路由routerkey
	public static final String EMAIL_ROUTER_KEY="want.email";
	public static final String RPC_ROUTER_KEY="want.rpc.rk";
	

}
