package com.want.mq.email.service;

import java.util.Map;

public interface IHessianMongoService {


	public <T> void insertByName(T obj,String docName);
	public void insert(Map<String,Object> content,String docName);
	public void insertJson(String json,String docName);
	public void insertJsonCollection(String json,String docName);
}
