package com.want.mq;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.want.mq.email.service.IHessianEmailService;
import com.want.mq.email.service.IHessianMongoService;
import com.want.mq.model.PublicEmail;
import com.want.mq.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WantRabbitmqApplicationTests {

/*	@Autowired
	private IEmailService iEmailService;
	@Autowired
	private IRabbitMqService iRabbitMqService;
	@Autowired
	private IMongoService mongoService;*/

	@Test
	public void contextLoads() {
	}

	@Test
	public void sendmail() {
		/*
		 * PublicEmail email =new PublicEmail(); email.setMailTitle("title");
		 * email.setMailContent("content"); email.setSender("00291315@want-want.com");
		 * List<String> recipients =new ArrayList<String>();
		 * recipients.add("00291315@want-want.com"); email.setRecipients(recipients);
		 * iEmailService.sendSimpleMail(email);
		 */

	}

	@Test
	public void sendrabbit() {
		/*
		 * PublicEmail email =new PublicEmail(); email.setMailTitle("title");
		 * email.setMailContent("content"); email.setSender("00291315@want-want.com");
		 * List<String> recipients =new ArrayList<String>();
		 * recipients.add("00291315@want-want.com"); email.setRecipients(recipients);
		 * iRabbitMqService.send(email);
		 */
	}

	@Test
	public void sendrpc() {

		/* iRabbitMqService.rpcSendAndRec("hrllo"); */
	}

	@Test
	public void testHession() throws MalformedURLException {
		/*SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String url = "http://10.0.26.130:8090/hessianEmailService";
		HessianProxyFactory factory = new HessianProxyFactory();
		IHessianEmailService emailservice = (IHessianEmailService) factory.create(IHessianEmailService.class, url);
		PublicEmail email = new PublicEmail();
		email.setMailTitle("test send mail hession title");
		email.setMailContent("test send mail hession content "+format.format(new Date()));
		List<String> rece = new ArrayList<String>();
		rece.add("00314254");
		rece.add("00298757");
		rece.add("00291315");
		email.setRecipients(rece);
		emailservice.sendHtmlMail(email);*/
	    
	}
	/*@Test
	public void ldaptest() {
		LdapConfig ldap= SpringUtils.getBean(LdapConfig.class);
		System.out.println(ldap.toString());
	}*/
	/*@Test
	public void testLadp() {
		List<String> addressList =new ArrayList<String>();
		addressList.add("00291315");
		List<String> result =StringUtil.convertEmailRec(addressList);
		
		System.out.println(result.toString());
		
	}*/
	@Test
	public void testString() {
		/*String obj = "";
		
		if(StringUtil.isNull(obj)) {
			System.out.println("null value");
		}*/
	}
	@Test
	public void testMongo() {
		/*Paging<PublicEmail> p=mongoService.searchPaging(null, null, "test", null, null, null, 1, 3);
		System.out.println(p.getTotalCount());  
		System.out.println(p.getTotalPage()); */	
	}
	@Test
	public void testMongoDb() {
		/*PublicEmail email=new PublicEmail();
		email.setMailTitle("ttt");
		mongoService.insert(email);*/
		
		/*List<PublicEmail> l=new ArrayList<PublicEmail>();
		PublicEmail email=new PublicEmail();
		email.setMailTitle("ttt");
		email.setMailContent("eeee");
		l.add(email);
		mongoService.insert(l);*/
	}
	@Test
	public void testinsertjson() {
		/*PublicEmail email=new PublicEmail();
		email.setMailTitle("ttt");
		String obj=JsonUtil.parseToJSON(email);
		mongoService.insertJson(obj);*/
		
	}
	@Test
	public void insertMongo() throws MalformedURLException {
		/*String url = "http://localhost:8090/hessianMongoService";
		HessianProxyFactory factory = new HessianProxyFactory();
		IHessianMongoService mongo =(IHessianMongoService) factory.create(IHessianMongoService.class, url);
		
		List<PublicEmail> l=new ArrayList<PublicEmail>();
		PublicEmail email=new PublicEmail();
		email.setMailTitle("ttt");
		l.add(email);
		
		mongo.insertCollection(l,"formmasterinfo");*/
	}
	
	@Test
	public void insertMongoJson() throws MalformedURLException {
		/*String url = "http://localhost:8090/hessianMongoService";
		HessianProxyFactory factory = new HessianProxyFactory();
		IHessianMongoService mongo =(IHessianMongoService) factory.create(IHessianMongoService.class, url);
		PublicEmail email=new PublicEmail();
		email.setMailTitle("ttt");
		String obj=JsonUtil.parseToJSON(email);
		System.out.println(obj);
		mongo.insertJson(obj, "TTTTTTTTTTT");*/
	}
	
	@Test
	public void testJson() {
		/*Organization org=new Organization();
		org.setAreaId("aaa");
		org.setId("555");
		List<String>  members=new ArrayList<String>();
		members.add("aaa");
		members.add("bbb");
		org.setMember(members);
		
		List<Organization> lo=new ArrayList<Organization>();
		lo.add(org);
		lo.add(org);
		
		TestDTO  dto=new TestDTO();
		dto.setName("uuuuu");
		dto.setMember(lo);
		
		List<TestDTO> dtol=new ArrayList<TestDTO>();
		dtol.add(dto);
		//dtol.add(dto);
		
		String jsonStr=JsonUtil.parseToJSON(dtol);
		System.out.println(jsonStr);
		List<Map<Object, Object>> ll=JsonUtil.getJsonList(jsonStr);
		for(Map<Object, Object> map:ll) {
			 for(Object obj:map.keySet()) {
				 System.out.println(obj +" === " +map.get(obj).toString());
			 }
			
		}
		*/
		
		
		/*Organization org=new Organization();
		org.setAreaId("aaa");
		org.setId("555");
		List<String>  member=new ArrayList<String>();
		member.add("aaa");
		member.add("bbb");
		org.setMember(member);
		
		List<Organization> lo=new ArrayList<Organization>();
		lo.add(org);
		lo.add(org);
		
		String jsonStr=JsonUtil.parseToJSON(lo);
		
		List<Map<Object, Object>> ll=JsonUtil.getJsonList(jsonStr);
		for(Map<Object, Object> map:ll) {
			 for(Object obj:map.keySet()) {
				 System.out.println(obj +" === " +map.get(obj).toString());
			 }
			
		}*/
		
		/*
		 JSONArray jarr=JSONArray.parseArray(jsonStr);
		 Iterator it = jarr.iterator();
		 while (it.hasNext()) {
			 JSONObject jsonObject = (JSONObject) it.next();
			
			 Iterator<String> keys = jsonObject.keySet().iterator();
		        while (keys.hasNext()) {
		            String key = keys.next();
		            if (jsonObject.get(key) instanceof JSONObject) {
		                JSONObject innerObject = (JSONObject) jsonObject.get(key);
		                System.out.println("465435543");
		            } else if (jsonObject.get(key) instanceof JSONArray) {
		                JSONArray innerObject = (JSONArray) jsonObject.get(key);
		                System.out.println("988988989");
		            }else {
		            	System.out.println("keykeykey"+key);
		            }
		        }
		 }
		*/
		
		 /*JSONArray jsonarr=JSONArray.parseArray(jsonStr);
		 if (jsonarr != null ) {
	            Iterator i1 = jsonarr.iterator();
	            while (i1.hasNext()) {
	                Object key = i1.next();
	                if (key instanceof  JSONObject) {
	                    JSONObject innerObject = (JSONObject) key;
	                    System.out.println("obj     99999");
	                } else if (key instanceof JSONArray) {
	                    JSONArray innerObject = (JSONArray) key;
	                    System.out.println("obj    5555");
	                }else{
	                	System.out.println("obj    44444");
	                }
	            }
	        }*/
		
		/*
		List<Map<Object, Object>> l=JsonUtil.getJsonList(jsonStr);
		for(Map<Object, Object> map:l) {
			 for(Object obj:map.keySet()) {
				 System.out.println(obj +" === " +map.get(obj));
			 }
			
		}*/
		
	/*	Map<Object,Object> map =new HashMap<Object,Object>();
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		 Iterator<String> keys = jsonObject.keySet().iterator();
	        while (keys.hasNext()) {
	            String key = keys.next();
	            System.out.println("keykeykey"+key);
	            
	            if (jsonObject.get(key) instanceof JSONObject) {
	                JSONObject innerObject = (JSONObject) jsonObject.get(key);
	                
	            } else if (jsonObject.get(key) instanceof JSONArray) {
	                JSONArray innerObject = (JSONArray) jsonObject.get(key);
	               System.out.println("srrrrr");
	            }else {
	            	map.put(key, jsonObject.get(key))  ;
	            }
	        }
	        
	        
	    for(Object obj:map.keySet()) {
	    	System.out.println(obj+"===" +map.get(obj));
	    }*/
	}
	public void testinsertmongo() {
		/*Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "ivanzhang");
		List<String> l=new ArrayList<String>();
		l.add("shanghaiu");
		l.add("beij");
		map.put("addr", l);*/
		//Map<String,Object> addr=new HashMap<String,Object>();	
	}
	@Test
	public void testmongo2() throws MalformedURLException {
		/*String url = "http://10.0.26.130:8090/hessianMongoService";
		HessianProxyFactory factory = new HessianProxyFactory();
		IHessianMongoService mongoService = (IHessianMongoService)factory.create(IHessianMongoService.class, url);
		String json = "{\"empId\":\"00007383\",\"empName\":\"梁燕\",\"orgId\":\"20170000\",\"orgName\":\"南宁分公司\",\"oldPosId\":\"00036565_1\",\"newPosId\":\"\",\"note\":\"人员多岗，不调整\"}";
		System.out.println("json:"+json);
		mongoService.insertJson(json, "BPM_ASSISTANT_CHECK_LOG");*/
	}
	@Test
	public void testmongo3() throws MalformedURLException {
		/*String url = "http://localhost:8090/hessianMongoService";
		HessianProxyFactory factory = new HessianProxyFactory();
		IHessianMongoService mongoService = (IHessianMongoService)factory.create(IHessianMongoService.class, url);
		TestData d=new TestData();
		d.setName("yyyy");
		d.setBegin(new Date());
		String jsonStr= 	JsonUtil.parseToJSON(d);
		System.out.println(jsonStr);
		mongoService.insertJson(jsonStr, "tttttt");*/
	}
	
	
	

}
