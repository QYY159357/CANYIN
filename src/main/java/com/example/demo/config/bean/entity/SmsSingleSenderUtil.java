package com.example.demo.config.bean.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.bean.entity.SmsSingleSenderConfig.SmsSingleSenderParams;
import com.example.demo.config.bean.entity.SmsSingleSenderConfig.TimeConfig;
import com.example.demo.util.Util;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

@Component
public class SmsSingleSenderUtil {

	@Autowired
	private SmsSingleSender smsSingleSender;

	@Autowired
	private Map<String, SmsSingleSenderConfig> sssConfigMap;

	public String send(String phone, String business) throws JSONException, HTTPException, IOException {
		SmsSingleSenderConfig sssConfig = sssConfigMap.get(business);
		System.out.println(business);
		System.out.println(Util.toJson(sssConfig));
		SmsSingleSenderParams sssParams = sssConfig.getSmsSingleSenderParams();
		TimeConfig timeConfig = sssConfig.getTimeConfig();
		ArrayList<String> params = new ArrayList<String>();
		// 生成验证码
		String code = Util.getRandom(timeConfig.getLength());
		params.add(code);
		params.add(timeConfig.getTimeOut() + "");
		SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam(sssParams.getNationCode(), phone,
				sssParams.getTemplateId(), params, sssParams.getSign(), sssParams.getExtend(), sssParams.getExt());
		System.out.println(Util.toJson(smsSingleSenderResult));
		if (smsSingleSenderResult.result == 0) {
			return code;
		}
		return null;
	}

}
