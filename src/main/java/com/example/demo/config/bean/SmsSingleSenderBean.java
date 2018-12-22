package com.example.demo.config.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.example.demo.config.bean.entity.SmsSingleSenderConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.qcloudsms.SmsSingleSender;
import com.qcloud.cos.utils.IOUtils;

/**
 * 腾讯云短信
 * 
 * @author Administrator
 *
 */
@Configuration
public class SmsSingleSenderBean {

	@Value("${smssinglesender.appid}")
	private Integer appid;

	@Value("${smssinglesender.appkey}")
	private String appkey;

	@Value("classpath:sms.json")
	private Resource resource;

	@Bean
	public SmsSingleSender SmsSingleSender() {
		return new SmsSingleSender(appid, appkey);
	}

	@Bean(value = { "smsSingleSenderConfig" })
	public Map<String, SmsSingleSenderConfig> smsSingleSenderConfig() {
		Map<String, SmsSingleSenderConfig> map = new HashMap<String, SmsSingleSenderConfig>();
		try {
			String json = IOUtils.toString(resource.getInputStream());
			ObjectMapper om = new ObjectMapper();
			map = om.readValue(json, new TypeReference<Map<String, SmsSingleSenderConfig>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
