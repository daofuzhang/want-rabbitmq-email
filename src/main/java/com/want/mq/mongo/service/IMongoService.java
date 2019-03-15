package com.want.mq.mongo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.want.mq.model.Paging;
import com.want.mq.model.PublicEmail;

/**
 * mongodb manage
 * @author 00291315
 * 2018-12-12 11:18:49
 */
public interface IMongoService {
	
	
	public void insertEmail(PublicEmail email);
	public List<PublicEmail> findByDate(Date begin,Date end);
	public List<PublicEmail> findByTitle(String title);
	public List<PublicEmail> findByContent(String content);
	public List<PublicEmail> findByRecipient(String recipient);
	public List<PublicEmail> findByCc(String cc);
	public List<PublicEmail> search(Date begin,Date end,String title,String content,String recipient,String cc);
	public Paging<PublicEmail> searchPaging(Date begin,Date end,String title,String content,String recipient,String cc,int currentPage ,int pageSize) ;
	
	public <T> void insert(T obj);
	public <T> void insertByName(T obj,String docName);
	public <T> void insertCollection(List<T> obj);
	public void insertJson(String json,String docName) ;
	public void insertJsonCollection(String json,String docName) ;
	public void insertMap(Map<String,Object> content,String docName);
}
