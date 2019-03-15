package com.want.mq.producer.service;

import com.want.mq.model.PublicEmail;

public interface IRabbitMqService {
	public void send(PublicEmail email);
	public void rpcSendAndRec(String obj);
}
