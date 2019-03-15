package com.want.mq.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.want.mq.model.PublicEmail;
import com.want.mq.producer.service.IRabbitMqService;

import io.swagger.annotations.ApiOperation;

/**
 * 发送邮件
 * 
 * @author 00291315
 *
 */
@Controller
@RequestMapping("/email")
public class SendMailController {

	private Logger logger = LoggerFactory.getLogger(SendMailController.class);

	// 邮件发送者
	@Value("${want.mail.sender}")
	private String sender = null;
	// mq服务
	@Autowired
	private IRabbitMqService iRabbitMqService;

	// 登录页面
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ApiOperation(value = "发送邮件", notes = "发送页面加载")
	public String load() {
		return "sendmail";
	}

	/**
	 * 发送邮件，实体类PublicEmail作为入参发送邮件,返回跳转页面
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/sendbypublicemail", method = RequestMethod.POST)
	@ApiOperation(value = "发送邮件", notes = "实体类PublicEmail作为入参发送邮件,返回跳转页面")
	public String send(@RequestParam(value = "email" ,required=true) PublicEmail email) {
		try {
			email.setSender(sender);
			email.setCreateDate(new Date());
			iRabbitMqService.send(email);
			return "success";
		} catch (Exception e) {
			logger.error("发送邮件异常", e);
		}
		return "error";
	}
	/**
	 * 发送邮件，实体类PublicEmail作为入参发送邮件,返回失败或成功map信息
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/sendpublicemail")
	@ResponseBody
	@ApiOperation(value = "发送邮件", notes = "实体类PublicEmail作为入参发送邮件,返回失败或成功map信息")
	public Map<String,String> sendEmail(@RequestParam(value = "email" ,required=true) PublicEmail email) {
		Map<String,String> result=new HashMap<String,String>();
		try {
			email.setSender(sender);
			email.setCreateDate(new Date());
			iRabbitMqService.send(email);
			result.put("code", "S");
			result.put("msg", "发送成功");
			return result;
		} catch (Exception e) {
			logger.error("发送邮件异常", e);
		}
		result.put("code", "E");
		result.put("msg", "发送失败");
		return result;
	}
    /**
     * 发送邮件，邮件信息作为入参发送邮件,返回跳转页面
     * @param mailTitle
     * @param mailContent
     * @param recipients
     * @param cc
     * @return
     */
	@RequestMapping(value = "/sendbyattribute", method = RequestMethod.POST)
	@ApiOperation(value = "发送邮件", notes = "邮件信息作为入参发送邮件,返回跳转页面")
	public String sendByParam(@RequestParam(value = "mailTitle" ,required=true) String mailTitle, @RequestParam(value = "mailContent",required=true) String mailContent, @RequestParam(value = "recipients",required=true) List<String> recipients, @RequestParam(value = "cc",required=false) List<String> cc) {
		try {
			PublicEmail email = new PublicEmail();
			email.setSender(sender);
			email.setCc(cc);
			email.setCreateDate(new Date());
			email.setMailContent(mailContent);
			email.setMailTitle(mailTitle);
			email.setRecipients(recipients);
			iRabbitMqService.send(email);
			return "success";
		} catch (Exception e) {
			logger.error("发送邮件异常", e);
		}
		return "error";
	}
	 /**
     * 发送邮件，邮件信息作为入参发送邮件,返回失败或成功map信息
     * @param mailTitle
     * @param mailContent
     * @param recipients
     * @param cc
     * @return
     */
	@RequestMapping(value = "/sendattribute", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "发送邮件", notes = "邮件信息作为入参发送邮件,返回失败或成功map信息")
	public Map<String,String> sendByParam2(@RequestParam(value = "mailTitle" ,required=true) String mailTitle, @RequestParam(value = "mailContent",required=true) String mailContent, @RequestParam(value = "recipients",required=true) List<String> recipients, @RequestParam(value = "cc",required=false) List<String> cc) {
		Map<String,String> result=new HashMap<String,String>();
		try {
			PublicEmail email = new PublicEmail();
			email.setSender(sender);
			email.setCc(cc);
			email.setCreateDate(new Date());
			email.setMailContent(mailContent);
			email.setMailTitle(mailTitle);
			email.setRecipients(recipients);
			iRabbitMqService.send(email);
			result.put("code", "S");
			result.put("msg", "发送成功");
			return result;
		} catch (Exception e) {
			logger.error("发送邮件异常", e);
		}
		result.put("code", "E");
		result.put("msg", "发送失败");
		return result;
	}

}
