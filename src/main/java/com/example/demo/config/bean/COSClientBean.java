package com.example.demo.config.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

/**
 * 腾讯云对象存储
 * @author Administrator
 *
 */
@Configuration
public class COSClientBean {

	@Value("${cosclient.accessKey}")
	private String accessKey;
	@Value("${cosclient.secretKey}")
	private String secretKey;
	@Value("${cosclient.regionName}")
	private String regionName;

	@Bean
	@Scope("prototype") // 多例
	public COSClient cosclient(COSCredentials cred, ClientConfig clientConfig) {
		COSClient cosclient = new COSClient(cred, clientConfig);
		return cosclient;
	}

	@Bean
	public ClientConfig clientConfig() {
		return new ClientConfig(new Region(regionName));
	}

	@Bean
	public COSCredentials cred() {
		return new BasicCOSCredentials(accessKey, secretKey);
	}

}
