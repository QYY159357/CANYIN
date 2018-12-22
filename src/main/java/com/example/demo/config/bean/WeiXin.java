package com.example.demo.config.bean;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.vo.Openid;
import com.example.demo.util.HttpUtils;

@Component
public class WeiXin {

	@Value("${weixin.jscode2SessionUrl}")
	private String jscode2SessionUrl;

	@Value("${weixin.appid}")
	private String appid;

	@Value("${weixin.secret}")
	private String secret;

	public Openid getOpenid(String code) {
		try {
			return HttpUtils.sendGet(jscode2SessionUrl.replaceFirst("APPID", appid).replaceFirst("SECRET", secret)
					.replaceFirst("JSCODE", code), null, Openid.class);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
