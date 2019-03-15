package com.want.mq.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.want.mq.model.Paging;
import com.want.mq.model.PublicEmail;
import com.want.mq.model.PublicEmailDTO;
import com.want.mq.mongo.service.IMongoService;
import com.want.mq.util.StringUtil;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/mongo")
public class SearchEmailController {

	private Logger logger = LoggerFactory.getLogger(SearchEmailController.class);

	@Autowired
	private IMongoService iMongoService;

	@RequestMapping(value = "/init")
	@ApiOperation(value = "查询邮件", notes = "查询已发送邮件")
	public String load() {
		return "searchmongo";
	}
	
	@RequestMapping(value = "/searchbytitle")
	@ResponseBody
	@ApiOperation(value = "查询邮件", notes = "通过标题查询已发送邮件")
	public List<PublicEmailDTO> searchBymailTitle(@RequestParam(value = "mailTitle", required = false) String mailTitle){
		List<PublicEmailDTO> emails = new ArrayList<PublicEmailDTO>();	
		List<PublicEmail> pubList=iMongoService.findByTitle(mailTitle);
		for(PublicEmail pub:pubList) {
			PublicEmailDTO dto=new PublicEmailDTO();
			BeanUtils.copyProperties(pub, dto);
			dto.setRecipient( null==pub.getRecipients()?"":pub.getRecipients().toString());
			dto.setCc(null==pub.getCc()?"":pub.getCc().toString());
			dto.setCreateDate(pub.getCreateDate());
			emails.add(dto);
		}
		return emails;
	}
	@RequestMapping(value = "/searchbycontent")
	@ResponseBody
	@ApiOperation(value = "查询邮件", notes = "通过内容查询已发送邮件")
	public List<PublicEmailDTO> searchBymailContent(@RequestParam(value = "mailContent", required = false) String mailContent){
		List<PublicEmailDTO> emails = new ArrayList<PublicEmailDTO>();	
		List<PublicEmail> pubList=iMongoService.findByContent(mailContent);
		for(PublicEmail pub:pubList) {
			PublicEmailDTO dto=new PublicEmailDTO();
			BeanUtils.copyProperties(pub, dto);
			dto.setRecipient( null==pub.getRecipients()?"":pub.getRecipients().toString());
			dto.setCc(null==pub.getCc()?"":pub.getCc().toString());
			dto.setCreateDate(pub.getCreateDate());
			emails.add(dto);
		}
		return emails;
	}
	@RequestMapping(value = "/searchbycreatetime")
	@ResponseBody
	@ApiOperation(value = "查询邮件", notes = "通过日期查询已发送邮件")
	public List<PublicEmailDTO> searchByCreateTime(@RequestParam(value = "begin", required = false) Date begin,
			@RequestParam(value = "end", required = false) Date end){
		List<PublicEmailDTO> emails = new ArrayList<PublicEmailDTO>();	
		List<PublicEmail> pubList=iMongoService.findByDate(begin, end);
		for(PublicEmail pub:pubList) {
			PublicEmailDTO dto=new PublicEmailDTO();
			BeanUtils.copyProperties(pub, dto);
			dto.setRecipient( null==pub.getRecipients()?"":pub.getRecipients().toString());
			dto.setCc(null==pub.getCc()?"":pub.getCc().toString());
			dto.setCreateDate(pub.getCreateDate());
			emails.add(dto);
		}
		return emails;
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	@ApiOperation(value = "查询邮件", notes = "查询已发送邮件")
	public List<PublicEmailDTO> search(@RequestParam(value = "mailTitle", required = false) String mailTitle,
			@RequestParam(value = "mailContent", required = false) String mailContent,
			@RequestParam(value = "recipient", required = false) String recipient,
			@RequestParam(value = "cc", required = false) String cc,
			@RequestParam(value = "begin", required = false) String begin,
			@RequestParam(value = "end", required = false) String end) {
		logger.debug("mailTitle=" + mailTitle);
		logger.debug("mailContent=" + mailContent);
		logger.debug("recipient=" + recipient);
		logger.debug("cc=" + cc);
		logger.debug("begin=" + begin);
		logger.debug("end=" + end);
		Date beginDate = null;
		Date endDate = null;
		List<PublicEmailDTO> emails = new ArrayList<PublicEmailDTO>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (!StringUtils.isEmpty(begin)) {
				beginDate = format.parse(begin);
			}
			if (!StringUtils.isEmpty(end)) {
				endDate = format.parse(end);
			}
		} catch (Exception e) {
           logger.error(e.toString());
		}
		List<PublicEmail> pubList=iMongoService.search(beginDate, endDate, mailTitle, mailContent, recipient, cc);
		for(PublicEmail pub:pubList) {
			PublicEmailDTO dto=new PublicEmailDTO();
			BeanUtils.copyProperties(pub, dto);
			dto.setRecipient( null==pub.getRecipients()?"":pub.getRecipients().toString());
			dto.setCc(null==pub.getCc()?"":pub.getCc().toString());
			dto.setCreateDate(pub.getCreateDate());
			emails.add(dto);
		}
		return emails;
	}
	@RequestMapping(value = "/searchpaging")
	@ResponseBody
	@ApiOperation(value = "查询邮件", notes = "查询已发送邮件")
	public Map searchPaging(@RequestParam(value = "mailTitle", required = false) String mailTitle,
			@RequestParam(value = "mailContent", required = false) String mailContent,
			@RequestParam(value = "recipient", required = false) String recipient,
			@RequestParam(value = "cc", required = false) String cc,
			@RequestParam(value = "begin", required = false) String begin,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value="page", required=false) String page, 
            @RequestParam(value="rows", required=false) String rows) {
		logger.debug("mailTitle=" + mailTitle);
		logger.debug("mailContent=" + mailContent);
		logger.debug("recipient=" + recipient);
		logger.debug("cc=" + cc);
		logger.debug("begin=" + begin);
		logger.debug("end=" + end);
		
		if(StringUtil.isNull(mailTitle) && StringUtil.isNull(mailContent) && StringUtil.isNull(cc) && StringUtil.isNull(begin)&& StringUtil.isNull(end)) {
			return null;
		}
		Date beginDate = null;
		Date endDate = null;
		List<PublicEmailDTO> emails = new ArrayList<PublicEmailDTO>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (!StringUtils.isEmpty(begin)) {
				beginDate = format.parse(begin);
			}
			if (!StringUtils.isEmpty(end)) {
				endDate = format.parse(end);
			}
		} catch (Exception e) {
           logger.error(e.toString());
		}
		int currentPage  =  StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page);
		int eachrow  =  StringUtils.isEmpty(rows) ? 10 : Integer.valueOf(rows);
		Paging<PublicEmail> sr =iMongoService.searchPaging(beginDate, endDate, mailTitle, mailContent, recipient, cc,currentPage,eachrow);
		List<PublicEmail> pubList=sr.getRows();
		for(PublicEmail pub:pubList) {
			PublicEmailDTO dto=new PublicEmailDTO();
			BeanUtils.copyProperties(pub, dto);
			dto.setRecipient( null==pub.getRecipients()?"":pub.getRecipients().toString());
			dto.setCc(null==pub.getCc()?"":pub.getCc().toString());
			dto.setCreateDate(pub.getCreateDate());
			emails.add(dto);
		}
		Map rMap = new HashMap(); 
		rMap.put("rows", emails);     //存放每页记录数
        rMap.put("total", sr.getTotalCount());   //存放总记录数 ，必须的
		return rMap;
	}

}
