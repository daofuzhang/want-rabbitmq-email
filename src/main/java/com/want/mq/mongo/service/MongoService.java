package com.want.mq.mongo.service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.want.mq.model.Paging;
import com.want.mq.model.PublicEmail;
import com.want.mq.util.JsonUtil;

@Service
public class MongoService implements IMongoService {
	
	Logger logger =LoggerFactory.getLogger(MongoService.class);

	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public void insertEmail(PublicEmail email) {
		mongoTemplate.save(email);
	}

	@Override
	public List<PublicEmail> findByDate(Date begin, Date end) {
		Query query = new Query(Criteria.where("createDate").gte(begin).lt(end));
		List<PublicEmail> emails = mongoTemplate.find(query, PublicEmail.class);
		return emails;
	}

	@Override
	public List<PublicEmail> findByTitle(String title) {
		Query query = new Query(Criteria.where("mailTitle").regex(title));
		List<PublicEmail> emails = mongoTemplate.find(query, PublicEmail.class);
		return emails;
	}

	@Override
	public List<PublicEmail> findByContent(String content) {
		Query query = new Query(Criteria.where("mailContent").regex(content));
		List<PublicEmail> emails = mongoTemplate.find(query, PublicEmail.class);
		return emails;
	}

	@Override
	public List<PublicEmail> findByRecipient(String recipient) {
		Query query = new Query(Criteria.where("recipients").in(recipient));
		List<PublicEmail> emails = mongoTemplate.find(query, PublicEmail.class);
		return emails;
	}

	@Override
	public List<PublicEmail> findByCc(String cc) {
		Query query = new Query(Criteria.where("cc").in(cc));
		List<PublicEmail> emails = mongoTemplate.find(query, PublicEmail.class);
		return emails;
	}

	/**
	 * 查询1000条
	 */
	@Override
	public List<PublicEmail> search(Date begin,Date end,String title,String content,String recipient,String cc) {
		Query query = new Query();
		query.limit(1000);
		Criteria criteria =new  Criteria();
		if(!StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)  ) {
			criteria.andOperator(Criteria.where("createDate").gte(begin).lte(end));
		}
		
		if(!StringUtils.isEmpty(title)) {
			criteria.andOperator(Criteria.where("mailTitle").regex(title));
		}
		if(!StringUtils.isEmpty(content)) {
			criteria.andOperator(Criteria.where("mailContent").regex(content));
		}
		if(!StringUtils.isEmpty(recipient)) {
			criteria.andOperator(Criteria.where("recipients").in(recipient));
		}
		if(!StringUtils.isEmpty(cc)) {
			criteria.andOperator(Criteria.where("cc").in(cc));
		}
		query.addCriteria(criteria);
		List<PublicEmail> emails = mongoTemplate.find(query, PublicEmail.class);
		return emails;
	}
	/**
	 * 分页查询
	 */
	@Override
	public Paging<PublicEmail> searchPaging(Date begin,Date end,String title,String content,String recipient,String cc,int currentPage ,int pageSize) {
		Query query = new Query();
		Criteria criteria =new  Criteria();
		if(!StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)  ) {
			criteria.andOperator(Criteria.where("createDate").gte(begin).lte(end));
		}
		
		if(!StringUtils.isEmpty(title)) {
			criteria.andOperator(Criteria.where("mailTitle").regex(title));
		}
		if(!StringUtils.isEmpty(content)) {
			criteria.andOperator(Criteria.where("mailContent").regex(content));
		}
		if(!StringUtils.isEmpty(recipient)) {
			criteria.andOperator(Criteria.where("recipients").in(recipient));
		}
		if(!StringUtils.isEmpty(cc)) {
			criteria.andOperator(Criteria.where("cc").in(cc));
		}
		//查询总记录数
        long count = (int) mongoTemplate.count(query, PublicEmail.class);
		query.addCriteria(criteria);
		//设置起始数
        query.skip((currentPage - 1) * pageSize);
        //设置查询条数
        query.limit(pageSize);
		List<PublicEmail> emails = mongoTemplate.find(query, PublicEmail.class);
		
		Paging<PublicEmail> page=new Paging<PublicEmail>();
		page.setRows(emails);
		page.setTotalCount(count);
		page.setTotalPage(count%pageSize==0?1:count/pageSize+1);
		return page;
	}
	
	/**
	 * 通过泛型将数据写入mongoDB
	 * T 里面不能有集合类型
	 */
	public <T> void insert(T obj) {
		try {
			if(null == obj) {
				return;
			}
			String clazzName=   obj.getClass().getSimpleName();
			mongoTemplate.insert(obj, clazzName);
		}catch(Exception e) {
			logger.error("mongo insert T desc:", e);
		}
	}
	public <T> void insertByName(T obj,String docName) {
		try {
			if(null == obj) {
				return;
			}
			mongoTemplate.insert(obj, docName);
		}catch(Exception e) {
			logger.error("mongo insert T desc:", e);
		}	
	}
	/**
	 * 通过泛型将数据写入mongoDB
	 * T 里面不能有集合类型
	 */
	public <T> void insertCollection(List<T> obj) {
		try {
			if(null!=obj && obj.size()>0) {
				T t=obj.get(0);
				String clazzName= t.getClass().getSimpleName();
				mongoTemplate.insert(obj, clazzName);
			}	
		}catch(Exception e) {
			logger.error("mongo insert T desc:", e);
		}
	}
	/**
	 * 将json字符串写入mongodb中
	 * json : json字符串
	 * docName ：要写入mongo的表名称
	 */
	public void insertJson(String json,String docName) {
		try {
			Map<Object,Object>  map=JsonUtil.getMapJSON(json);
			for(Object obj:map.keySet()) {
				Object value = map.get(obj);
				if(value instanceof Date) {
					map.put(obj, (Date)value);
				}else {
					map.put(obj, value);	
				}
			}
			map.put("syscreatetime", new Date());
			mongoTemplate.insert(map,docName);
		}catch(Exception e) {
			logger.error("insertJson", e);
		}
	}
	public void insertMap(Map<String,Object> content,String docName) {
		content.put("syscreatetime", new Date());
		mongoTemplate.insert(content, docName);
	}
	
	public void insertJsonCollection(String json,String docName) {
		try {
			List<Map<Object,Object>> map=JsonUtil.getJsonList(json);
			mongoTemplate.insert(map,docName);
		}catch(Exception e) {
			logger.error("insertJson", e);
		}
	}

}
