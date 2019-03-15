package com.want.mq.biz;

import java.util.Map;

public interface MongoHessianService {


	public <T> void insertByName(T obj,String docName);
	public void insert(Map<String,Object> content,String docName);
	public void insertJson(String json,String docName);
	public void insertJsonCollection(String json,String docName);
}
