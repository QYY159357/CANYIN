package com.example.demo.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.RedisTool;
import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.config.bean.entity.SmsSingleSenderUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.vo.PhoneCode;
import com.example.demo.service.PhoneCodeService;
import com.github.qcloudsms.httpclient.HTTPException;

@Service
public class PhoneCodeServiceImpl implements PhoneCodeService {

	@Autowired
	private SmsSingleSenderUtil sssUtil;

	@Resource
	private RedisTool<PhoneCode> redisTool;

	@Autowired
	private UserMapper userMapper;

	@Override
	public Object sendForBusiness(String id, String phone, String business) {
		// TODO Auto-generated method stub
		try {
			// 发送并返回验证码
			String code = sssUtil.send(phone, business);
			// 将验证码存入redis
			if (code != null) {
				PhoneCode phoneCode = new PhoneCode();
				phoneCode.setId(id);
				phoneCode.setPhone(phone);
				phoneCode.setCode(code);
				redisTool.set(business + ":" + id + ":" + phone, phoneCode);
				return ResultMap.newInstance().success(true);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HTTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMap.newInstance().fail(10000, "验证码发送失败");
	}

	@Override
	public Object checkForBusiness(String id, String code, String phone, String business) {
		// TODO Auto-generated method stub
		PhoneCode phoneCode = redisTool.get(business + ":" + id + ":" + phone);
		if (code != null && code.equals(phoneCode.getCode())) {
			userMapper.update(id, null, null, null, phone);
			return ResultMap.newInstance().success("手机号修改成功！");
		}

		return ResultMap.newInstance().fail(10008, "手机号修改失败！");
	}

}
