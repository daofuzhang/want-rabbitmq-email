package com.want.mq.biz;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.want.mq.model.PublicEmail;

@WebService
public interface ICxfEmailService {
	
	@WebMethod(operationName="sendHtmlMail")
	public void sendHtmlMail(@WebParam(name = "hemail") PublicEmail email);
}
