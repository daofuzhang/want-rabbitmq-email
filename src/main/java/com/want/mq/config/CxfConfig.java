package com.want.mq.config;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.want.mq.biz.ICxfEmailService;

/**
 * webservice配置
 * @author 00291315
 *
 */
@Configuration
public class CxfConfig {

	@Autowired
	private ICxfEmailService cxfEmailService;


	@Autowired
	private Bus bus;

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, cxfEmailService);
		endpoint.publish("/email/send");
		return endpoint;
	}
}
