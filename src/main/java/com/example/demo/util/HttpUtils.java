package com.example.demo.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

public class HttpUtils {
	/**
	 * 发送GET请求
	 */
	public static String sendGet(String url, Map<String, String> params)
			throws ParseException, IOException, URISyntaxException {
		HttpGet request = buildHttpGet(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return result;
	}

	/**
	 * 发送POST请求
	 */
	public static String sendPost(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		HttpPost request = buildHttpPost(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return result;
	}

	// 初始化HttpGet
	private static HttpGet buildHttpGet(String url, Map<String, String> params) throws URISyntaxException {
		URIBuilder builder = new URIBuilder(url);
		if (params != null) {
			Set<String> set = params.keySet();
			for (String key : set) {
				builder.setParameter(key, params.get(key));
			}
		}
		HttpGet request = new HttpGet(builder.build());
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000)
				.setConnectionRequestTimeout(6000).build();
		request.setConfig(requestConfig);
		return request;
	}

	// 初始化HttpPost
	private static HttpPost buildHttpPost(String url, Map<String, String> params)
			throws URISyntaxException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if (params != null) {
			NameValuePair nameValuePair = null;
			Iterator<Entry<String, String>> it = params.entrySet().iterator();
			Entry<String, String> en = null;
			while (it.hasNext()) {
				en = it.next();
				nameValuePair = new BasicNameValuePair(en.getKey(), en.getValue());
				parameters.add(nameValuePair);
			}
		}
		HttpPost request = new HttpPost(url);
		HttpEntity entity = new UrlEncodedFormEntity(parameters);
		request.setEntity(entity);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000)
				.setConnectionRequestTimeout(6000).build();
		request.setConfig(requestConfig);
		return request;
	}

	private static HttpPost buildRawHttpPost(String url, String params) {
		// TODO Auto-generated method stub
		HttpPost request = new HttpPost(url);
		HttpEntity entity = new StringEntity(params, ContentType.APPLICATION_JSON);
		request.setEntity(entity);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000)
				.setConnectionRequestTimeout(6000).build();
		request.setConfig(requestConfig);
		return request;
	}

	private static HttpPost buildInputHttpPost(String url, Map<String, String> params, String inputStreamName,
			InputStream inputStream, String fileName) {
		HttpPost request = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		// 添加流对象
		builder.addBinaryBody(inputStreamName, inputStream, ContentType.MULTIPART_FORM_DATA, fileName);
		if (params != null) {
			Set<Entry<String, String>> set = params.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			Entry<String, String> en = null;
			// 添加表单参数
			while (it.hasNext()) {
				en = it.next();
				builder.addPart(en.getKey(), new StringBody(en.getValue(), ContentType.MULTIPART_FORM_DATA));
			}
		}
		HttpEntity entity = builder.build();
		request.setEntity(entity);
		return request;
	}

	private static HttpPost buildInputHttpPost(String url, Map<String, String> params, String bytesName, byte[] bytes,
			String fileName) {
		HttpPost request = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		// 添加流对象
		builder.addBinaryBody(bytesName, bytes, ContentType.MULTIPART_FORM_DATA, fileName);
		if (params != null) {
			Set<Entry<String, String>> set = params.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			Entry<String, String> en = null;
			// 添加表单参数
			while (it.hasNext()) {
				en = it.next();
				builder.addPart(en.getKey(), new StringBody(en.getValue(), ContentType.MULTIPART_FORM_DATA));
			}
		}
		HttpEntity entity = builder.build();
		request.setEntity(entity);
		return request;
	}

	public static byte[] sendGet(String url) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(url);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		return IOUtils.toByteArray(entity.getContent());
	}

	public static <T> T sendPost(String url, Map<String, String> params, String bytesName, byte[] bytes,
			String fileName, Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		HttpPost request = buildInputHttpPost(url, params, bytesName, bytes, fileName);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		System.out.println(result);
		return Util.toObject(result, c);
	}

	public static <T> T sendPost(String url, Map<String, String> params, String inputStreamName,
			InputStream inputStream, String fileName, Class<T> c) throws ClientProtocolException, IOException {
		HttpPost request = buildInputHttpPost(url, params, inputStreamName, inputStream, fileName);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, c);
	}

	/**
	 * 发送Get请求，并进行返回值转化
	 */
	public static <T> T sendGet(String url, Map<String, String> params, Class<T> c)
			throws ParseException, IOException, URISyntaxException {
		HttpGet request = buildHttpGet(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, c);
	}

	public static <T> T sendGet(String url, Map<String, String> params, Boolean check, Class<T> c)
			throws URISyntaxException, ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpGet request = buildHttpGet(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		if (check) {
			String contentType = entity.getContentType().getValue();
			if (contentType.equals("image/jpeg") || contentType.equals("audio/amr")) {
				return null;
			}
		}
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, c);
	}

	/**
	 * 发送POST请求，并进行返回值转化
	 */
	public static <T> T sendPost(String url, Map<String, String> params, Class<T> c)
			throws ClientProtocolException, IOException, URISyntaxException {
		HttpPost request = buildHttpPost(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, c);
	}

	/**
	 * 发送POST请求，并进行返回值转化
	 */
	public static <T> T sendPost(String url, String params, Class<T> c)
			throws ClientProtocolException, IOException, URISyntaxException {
		HttpPost request = buildRawHttpPost(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, c);
	}

	/**
	 * 发送POST请求，并进行返回值转化
	 */
	public static <T> T sendPost(String url, String params, TypeReference<T> typeReference)
			throws ClientProtocolException, IOException, URISyntaxException {
		HttpPost request = buildRawHttpPost(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, typeReference);
	}

	public static String sendPost(String url, String params)
			throws ClientProtocolException, IOException, URISyntaxException {
		HttpPost request = buildRawHttpPost(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return result;
	}

	/**
	 * 发送Get请求，并进行返回值转化
	 */
	public static <T> T sendGet(String url, Map<String, String> params, TypeReference<T> typeReference)
			throws ParseException, IOException, URISyntaxException {
		HttpGet request = buildHttpGet(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, typeReference);
	}

	/**
	 * 发送POST请求，并进行返回值转化
	 */
	public static <T> T sendPost(String url, Map<String, String> params, TypeReference<T> typeReference)
			throws ClientProtocolException, IOException, URISyntaxException {
		HttpPost request = buildHttpPost(url, params);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return Util.toObject(result, typeReference);
	}

}