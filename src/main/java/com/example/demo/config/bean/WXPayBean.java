package com.example.demo.config.bean;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConfigImpl;

@Configuration
public class WXPayBean {

	@Value("${weixin.appid}")
	private String appid;

	@Value("${weixin.mchid}")
	private String mchid;

	@Value("${weixin.key}")
	private String key;

	@Value("classpath:cert/apiclient_cert.p12")
	private Resource resource;

	/**
	 * 支付功能
	 * 
	 * @param config
	 * @return
	 * @throws Exception
	 */
	@Bean
	public WXPay wxPay(WXPayConfig config) throws Exception {
		return new WXPay(config);
	}

	/**
	 * 微信支付配置
	 * 
	 * @return
	 */
	@Bean
	public WXPayConfigImpl wxPayConfig() {
		WXPayConfigImpl wxPayConfigImpl = null;
		try {
			wxPayConfigImpl = new WXPayConfigImpl(appid, mchid, key, resource.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wxPayConfigImpl;
	}

}
