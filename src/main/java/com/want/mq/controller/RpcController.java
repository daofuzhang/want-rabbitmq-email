package com.want.mq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.mq.producer.service.IRabbitMqService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rpc")
public class RpcController {

	private Logger logger=LoggerFactory.getLogger(RpcController.class);
	
	@Autowired
	private IRabbitMqService rabbitMqService;
	
	@RequestMapping("/send")
	@ApiOperation(value = "RPC", notes = "rabbitmqRPC方式调用")
	public String rpcProvider() {
		rabbitMqService.rpcSendAndRec("zhangdaofu");
		return "success";
	}

}
