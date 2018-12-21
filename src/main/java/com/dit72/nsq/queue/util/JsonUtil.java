package com.dit72.nsq.queue.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * json 工具类
 * 
 * @author Jon Chiang
 * @date 2018年4月26日
 */
public class JsonUtil {

	/**
	 * json字符串转对象
	 * 
	 * @param json
	 * @param clazz
	 * @return 2018年4月26日
	 */
	public static <T> T json2Object(String json, Class<T> clazz) {
		T t = JSONObject.parseObject(json, clazz);

		return t;
	}
	public static <T> List<T> json2List(String json, Class<T> clazz) {
		return JSONObject.parseArray(json, clazz);
	}
	/**
	 * json字符串转对象
	 * 
	 * @param json
	 * @param clazz
	 * @return 2018年4月26日
	 */
	public static <T> T json2Object(String json, TypeReference<T> typeReference) {
		T t = JSONObject.parseObject(json, typeReference);
		return t;
	}
	public static <T> T objectToBean(Object obj, Class<T> clazz) {
		return JSONObject.parseObject(JSONObject.toJSONString(obj), clazz);
	}
	/**
	 * 对象转Json String
	 * @param t
	 * @return
	 * 2018年4月26日
	 */
	public static <T> String toJsonString(T t) {
		return JSONObject.toJSONString(t);
	}

}
