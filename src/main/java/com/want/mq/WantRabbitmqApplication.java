package com.want.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
****************************************
* 旺旺邮件发送中间件
* 00291315 
* 2018-12-14 09:50:40
****************************************
》》》mongodb功能
简介：邮件发送后会将发送的邮件写入mongodb，本项目提供mongodb查询发送邮件功能
地址:http://localhost:8080/mongo/init
》》》email发送功能
简介：本项目自带邮件发送测试页面
地址：http://localhost:8080/email/init
》》》webservice接口发送邮件
简介：本项目支持http方式和wenservice方式发送邮件
http地址： http://localhost:8080/email/sendbyattribute
webservice地址：http://localhost:8080/services/email/send?wsdl
》》》hession 调用接口

》》》swagger查询接口
简介：项目实现了swagger2
地址：http://localhost:8080/swagger-ui.html#/

》》》项目配置简介
项目配置只需修改mongodb的地址，email本身连接的是正式环境的地址，测试发送邮件请谨慎

》》》项目简介
本项目是用来给其他项目发送邮件的功能，采用消息中间件rabbitmq实现
基本实现原理  : provider -exchange - channel - consumer
           provider采用http和webservice供其它项目调用
           channel上绑定了两个消费端，用于发邮件和写入mongodb
           rabbitmq采用每个消费单开启10线程，每个线程一次消费一条消息的配置，可以修改。
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WantRabbitmqApplication {

	private static Logger logger=LoggerFactory.getLogger(WantRabbitmqApplication.class);
	public static void main(String[] args) {
		logger.error("springboot begin");
		SpringApplication.run(WantRabbitmqApplication.class, args);
	}

}
