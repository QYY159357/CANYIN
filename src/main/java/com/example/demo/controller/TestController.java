package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.TestService;
import com.example.demo.util.Util;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfigImpl;
import com.github.wxpay.sdk.WXPayUtil;

@Validated
@Controller
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private WXPay wxPay;

	@Autowired
	private WXPayConfigImpl wxPayConfigImpl;

	@ResponseBody
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public Object test() {
		Map<String, String> result = unifiedOrder(100);
		// 生成签名，返回jsapi签名数据
		// ******************
		String prepay_id = result.get("prepay_id");
		String packages = "prepay_id=" + prepay_id;
		Map<String, String> wxPayMap = new HashMap<String, String>();
		wxPayMap.put("appId", wxPayConfigImpl.getAppid());
		wxPayMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
		wxPayMap.put("nonceStr", Util.UUID().toUpperCase());
		wxPayMap.put("package", packages);
		wxPayMap.put("signType", "MD5");
		// 加密串中包括 appId timeStamp nonceStr package signType 5个参数, 通过sdk WXPayUtil类加密,
		// 注意, 此处使用 MD5加密 方式
		String paySign = null;
		try {
			System.out.println(wxPayMap);
			System.out.println(wxPayConfigImpl.getKey());
			paySign = WXPayUtil.generateSignature(wxPayMap, wxPayConfigImpl.getKey());
			System.out.println(paySign);
			wxPayMap.put("paySign", paySign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ******************************************
		// 待解决
		// ******************
		System.out.println(wxPayMap);
		return ResultMap.newInstance().success(wxPayMap);
	}

	// 微信统一下单
	private Map<String, String> unifiedOrder(Integer total_fee) {

		Map<String, String> data = new HashMap<String, String>();
		data.put("body", "点菜");
		data.put("out_trade_no", Util.UUID());
		data.put("nonce_str", Util.UUID());
		data.put("fee_type", "CNY");
		data.put("total_fee", total_fee + "");
		data.put("spbill_create_ip", "127.0.0.1");
		data.put("notify_url", "http://www.example.com/wxpay/notify");
		data.put("trade_type", "JSAPI");
		data.put("fee_type", "CNY");
		data.put("attach", "");// 加数据
		data.put("device_info", "WEB");// 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
		data.put("detail", "");// 商品详情
		data.put("openid", "oy1co4zDOo9VVli9ZZPzg35qs3LM");// open id
		Map<String, String> result = null;
		try {
			result = wxPay.unifiedOrder(data);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/xxx", method = { RequestMethod.GET })
	public Object xxx(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		return testService.test();
	}

	@ResponseBody
	@RequestMapping(value = "/ddd", method = { RequestMethod.GET })
	public Object ddd(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		return testService.test();
	}

}
