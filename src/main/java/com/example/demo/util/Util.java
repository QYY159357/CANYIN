package com.example.demo.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	/**
	 * ID生成策略
	 * 
	 * @return
	 */
	public static final String UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 时间路径
	 * 
	 * @return
	 */
	public static final String datePath() {
		return new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
	}
	
	/**
	 * 时间路径
	 * 
	 * @return
	 */
	public static final String orderNo() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	/**
	 * 获取验证码
	 * 
	 * @param lenth
	 *            随机数长度
	 * @return
	 */
	public static final String getRandom(Integer lenth) {
		Random random = new Random();
		String sixRandom = random.nextInt((int) Math.pow(10, lenth)) + "";
		int randLength = sixRandom.length();
		if (randLength < lenth) {
			for (int i = 1; i <= lenth - randLength; i++)
				sixRandom = "0" + sixRandom;
		}
		return sixRandom;
	}

	public static final String toJson(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static final <T> T toObject(String json, Class<T> valueType) {
		try {
			return new ObjectMapper().readValue(json, valueType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static final <T> T toObject(String json, TypeReference<T> valueTypeRef) {
		try {
			return new ObjectMapper().readValue(json, valueTypeRef);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取request里的xml格式的数据
	 * 
	 * @param request
	 * @return
	 */
	public static String getNotityXml(HttpServletRequest request) {
		String notityXml = "";
		String inputLine = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("notityXml--" + notityXml);
		return notityXml;
	}

	public static String getSign(Map<String, String> map, String key) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + key;
		// Util.log("Sign Before MD5:" + result);
		System.out.println("Sign Before MD5:" + result);
		result = DigestUtils.md5Hex(result).toUpperCase();
		// Util.log("Sign Result:" + result);
		System.out.println("Sign Result:" + result);
		return result;
	}

}
