package com.want.mq.biz;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.want.mq.mongo.service.IMongoService;

/****************************   
* http://i.want-want.com
*
* @Description: 
* @version: v1.0.0
* @author: 00291315
* @date: 2019年1月28日 下午2:01:19 
* Modification History: 
* 1.  00291315  2019年1月28日  初始创建
*******************************/
@Service
public class MongoHessianServiceImpl implements MongoHessianService {
	
	@Autowired
	private  IMongoService mongoService;

	/**
	 * 传入entity ,外部调用可能有异常
	 */
	public <T> void insertByName(T obj,String docName) {
		mongoService.insertByName(obj,docName);
	}
	/**
	 * 传入key value 类型的数据
	 */
	public void insert(Map<String,Object> content,String docName) {
		mongoService.insertMap(content, docName);
	}
	/**
	 * 传入DTO模型的json字符串
	 */
	public void insertJson(String json,String docName) {
		mongoService.insertJson(json, docName);
	}
	/**
	 * 写入特别复杂数据类型的
	 * 例如List<DTO> 的json字符串 ，DTO中有多层次嵌套.   
	 */
	public void insertJsonCollection(String json,String docName) {
		mongoService.insertJsonCollection(json, docName);
	}

}
