package com.want.mq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Component;

import com.want.mq.biz.IHttpInvokeEmailService;

@Component
public class HttpInvokeConfig {
	

	@Autowired
	private IHttpInvokeEmailService httpInvokeEmailService;
	
	@Bean("userExporter")
	private HttpInvokerServiceExporter  userExporter() {
		HttpInvokerServiceExporter httpInvokerServiceExporter =new HttpInvokerServiceExporter();
		httpInvokerServiceExporter.setService(httpInvokeEmailService);
		httpInvokerServiceExporter.setServiceInterface(IHttpInvokeEmailService.class);
		return httpInvokerServiceExporter;
	} 

}
