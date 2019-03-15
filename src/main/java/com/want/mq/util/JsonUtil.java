package com.want.mq.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private static SerializeConfig mapping = new SerializeConfig();
	private static SimpleDateFormatSerializer formatSerializer = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss");
	private static final SerializerFeature[] features;

	static {
		features = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty };
	}

	public JsonUtil() {
	}

	public static <T> T parseToClass(String jsonStr, Class<?> clazz) {
		T javaObject = (T) JSON.toJavaObject(JSON.parseObject(jsonStr), clazz);
		return javaObject;
	}

	public static String parseToJSON(Object object) {
		return JSON.toJSONString(object, configMapping(), new SerializerFeature[0]);
	}

	public static String parseUnicodeJSON(Object object) {
		return JSON.toJSONString(object, new SerializerFeature[] { SerializerFeature.BrowserCompatible });
	}

	public static String parseJSONString(Object object, SimplePropertyPreFilter filter) {
		return JSON.toJSONString(object, filter, new SerializerFeature[0]);
	}

	public static String parseJSONString(Object object, String formatDate) {
		return JSON.toJSONString(object, configMapping(formatDate), new SerializerFeature[0]);
	}

	public static List<?> getListJSONToJava(String jsonString, Class<?> pojoClass) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		List<Object> list = new ArrayList();

		for (int i = 0; i < jsonArray.size(); ++i) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Object pojoValue = JSON.toJavaObject(jsonObject, pojoClass);
			list.add(pojoValue);
		}

		return list;
	}

	public static Map<Object, Object> getMapJSON(String jsonString) {
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		Map<Object, Object> parseJSONMap = new LinkedHashMap(jsonObject);
		return parseJSONMap;
	}

	private static SerializeConfig configMapping() {
		mapping.put(Date.class, formatSerializer);
		return mapping;
	}

	private static SerializeConfig configMapping(String format) {
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(format));
		return mapping;
	}

	public static SimplePropertyPreFilter filterProperty(Class<?> className, String... param) {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(className, param);
		return filter;
	}

	public static Map<Object, Object> getAllKeyValue(JSONObject jsonObject, Map<Object, Object> map) {
		Iterator<String> keys = jsonObject.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			if (jsonObject.get(key) instanceof JSONObject) {
				JSONObject innerObject = (JSONObject) jsonObject.get(key);
				Map<Object, Object> objMap=getAllKeyValue(innerObject, new HashMap<Object, Object>());
				map.put(key, objMap);
			} else if (jsonObject.get(key) instanceof JSONArray) {
				JSONArray innerObject = (JSONArray) jsonObject.get(key);
				List<Object> arrObj=getAllKeyValueByJArr(innerObject, new ArrayList<Object>());
				map.put(key, arrObj);
			} else {
				Object v = jsonObject.get(key);
				if(v instanceof Date) {
					map.put(key, (Date)jsonObject.get(key));
				}else {
					map.put(key,   (String)jsonObject.get(key));
					map.put("syscreatetime", new Date());
				}
			}
		}
		return map;
	}
	public static List< Object> getAllKeyValueByJArr(JSONArray jsonarr, List<Object> obj) {
		if (jsonarr != null) {
			Iterator i1 = jsonarr.iterator();
			while (i1.hasNext()) {
				Object key = i1.next();
				if (key instanceof JSONObject) {
					JSONObject innerObject = (JSONObject) key;
					obj.add(getAllKeyValue(innerObject, new HashMap<Object, Object>()));
				} else if (key instanceof JSONArray) {
					JSONArray innerObject = (JSONArray) key;
					obj.add(getAllKeyValueByJArr(innerObject, new ArrayList<Object>()));
				} else {
					obj.add(key);
				}
			}
		}
		return obj;
	}
	public static List<Map<Object, Object>> getJsonList(String jsonList) {
		List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>();
		JSONArray jarr = JSONArray.parseArray(jsonList);
		Iterator it = jarr.iterator();
		while (it.hasNext()) {
			JSONObject innerObject = (JSONObject) it.next();
			Map<Object, Object> map = new HashMap<Object, Object>();
			getAllKeyValue(innerObject, map);
			result.add(map);
		}
		return result;
	}
}
