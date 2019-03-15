package com.want.mq.biz;

import com.want.mq.model.PublicEmail;

public interface IHttpInvokeEmailService {


	public void sendHtmlMail(PublicEmail email);

}
