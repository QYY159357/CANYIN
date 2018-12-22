package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.exception.CommonException;
import com.example.demo.mapper.FoodMapper;
import com.example.demo.mapper.OrderFormInfoMapper;
import com.example.demo.mapper.OrderFormMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.po.OrderFormInfoPo;
import com.example.demo.pojo.po.OrderFormPo;
import com.example.demo.pojo.po.Weight;
import com.example.demo.pojo.vo.Food;
import com.example.demo.pojo.vo.LimitPage;
import com.example.demo.pojo.vo.OrderForm;
import com.example.demo.service.OrderFormService;
import com.example.demo.util.Util;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfigImpl;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.github.wxpay.sdk.WXPayUtil;

@Service
public class OrderFormServiceImpl implements OrderFormService {

	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderFormMapper orderFormMapper;

	@Autowired
	private OrderFormInfoMapper orderFormInfoMapper;

	@Autowired
	private FoodMapper foodMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private WXPay wxPay;

	@Autowired
	private WXPayConfigImpl wxPayConfigImpl;

	@Autowired
	private HttpServletRequest request;

	@SuppressWarnings("unused")
	@Override
	public ResultMap create(OrderFormPo orderFormPo,String orderNos) {

		//判断订单是否存在，是则删除已存在订单并生成新的订单
		checkOrder(orderNos);
		
		// TODO Auto-generated method stub
		// 校验商品信息
		checkOrderFormInfo(orderFormPo);
		// ************************************
		// 将订单信息存入数据库，并设置状态为未支付
//		String id = Util.UUID();
		// 获得座位号
		String seat = orderFormPo.getSeat();
		// 获得备注信息
		String remarks = orderFormPo.getRemarks();
		// 获得商品总价
		Double priceTotal = orderFormPo.getPriceTotal();
		// 获得手机号
		String phone = orderFormPo.getPhone();
		// 获得优惠
		Double priceDiscount = orderFormPo.getPriceDiscount();
		// 设置订单状态 0 表示未支付
		Integer status = 0;
		// 用户ID
		String userWeiXinId = orderFormPo.getUserWeiXinId();
		// 生成订单号
		String orderNo = Util.orderNo();
		// 生成订单ID
		String id = DigestUtils.md5Hex(orderNo);
		// 保存到数据库
		orderFormMapper.create(id, seat, remarks, priceTotal, phone, priceDiscount, status, userWeiXinId, orderNo);
		// 将商品列表也存入数据库
		List<OrderFormInfoPo> orderFormInfoList = orderFormPo.getOrderFormInfoPoList();
		// 遍历
		for (OrderFormInfoPo orderFormInfoPo : orderFormInfoList) {
			// 保存详情到数据库
			orderFormInfoMapper.create(Util.UUID(), orderFormInfoPo.getImage(), orderFormInfoPo.getName(),
					orderFormInfoPo.getPrice(), orderFormInfoPo.getAmount(), orderFormInfoPo.getFoodId(), id,
					Util.toJson(orderFormInfoPo.getWeight()), orderFormInfoPo.getTaste(),
					orderFormInfoPo.getPractice());
		}

		// 获得用户openid
		String openid = userMapper.selectById(userWeiXinId).getOpenid();

		// 微信统一下单
		Double total_fee = orderFormPo.getPriceTotal();
		return ResultMap.newInstance().success(jsapiSign((int) (total_fee * 100), openid, id, orderNo));
	}

	// 二次签名
	private Map<String, String> jsapiSign(Integer total_fee, String openid, String orderId, String orderNo) {
		Map<String, String> result = unifiedOrder(total_fee, openid, orderId, orderNo);
		// 生成签名，返回jsapi签名数据
		// ******************
		String prepay_id = result.get("prepay_id");
		String packages = "prepay_id=" + prepay_id;
		Map<String, String> wxPayMap = new HashMap<String, String>();
		wxPayMap.put("appId", wxPayConfigImpl.getAppid());
		wxPayMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
		wxPayMap.put("nonceStr", Util.UUID().toUpperCase());
		wxPayMap.put("package", packages);
		wxPayMap.put("signType", "HMAC-SHA256");
		// 加密串中包括 appId timeStamp nonceStr package signType 5个参数, 通过sdk WXPayUtil类加密,
		// 注意, 此处使用 MD5加密 方式
		String paySign = null;
		try {
			paySign = WXPayUtil.generateSignature(wxPayMap, wxPayConfigImpl.getKey(), SignType.HMACSHA256);
			wxPayMap.remove("appId");
			wxPayMap.put("paySign", paySign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ******************************************
		// 待解决
		// ******************
		wxPayMap.put("orderNo", orderNo);
		wxPayMap.put("orderId", orderId);
		logger.info(wxPayMap);
		return wxPayMap;
	}

	// 微信统一下单
	private Map<String, String> unifiedOrder(Integer total_fee, String openid, String orderId, String orderNo) {

		Map<String, String> data = new HashMap<String, String>();
		data.put("body", "点菜");
		data.put("out_trade_no", orderNo);
		data.put("nonce_str", orderId);
		data.put("fee_type", "CNY");
		data.put("total_fee", total_fee + "");
		data.put("spbill_create_ip", "127.0.0.1");
		data.put("notify_url", "http://118.24.52.233/order/form/wx/notify");
		data.put("trade_type", "JSAPI");
		data.put("fee_type", "CNY");
		data.put("attach", "");// 加数据
		data.put("device_info", "WEB");// 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
		data.put("detail", "");// 商品详情
		data.put("openid", openid);// open id
		Map<String, String> result = null;
		try {
			result = wxPay.unifiedOrder(data);
			logger.info(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private void checkOrderFormInfo(OrderFormPo orderFormPo) {
		// TODO Auto-generated method stub
		// 对商品信息进行校验
		List<OrderFormInfoPo> orderFormInfoList = orderFormPo.getOrderFormInfoPoList();
		// 遍历商品信息
		String id = null;
		Food food = null;
		Double priceTotal = (double) 0;
		for (OrderFormInfoPo orderFormInfoPo : orderFormInfoList) {
			// 获得商品ID
			id = orderFormInfoPo.getFoodId();
			// 查询数据库的商品信息
			food = foodMapper.selectById(id);
			// 获取规格
			Weight weight = orderFormInfoPo.getWeight();

			// 校验商品单价
			if (!food.getPrice().equals(orderFormInfoPo.getPrice())) {
				throw new CommonException(ResultMap.newInstance().fail(10003, orderFormInfoPo.getName() + "商品单价被修改"));
			}

			// 假如规格存在
			if (weight != null && weight.getPrice() != null) {
				// 计算商品总价
				priceTotal += weight.getPrice() * orderFormInfoPo.getAmount() * 100;
				continue;
			}

			// 计算商品总价
			priceTotal += orderFormInfoPo.getPrice() * orderFormInfoPo.getAmount() * 100;
			continue;
		}

		// 校验商品总价
		if (!(priceTotal).equals(orderFormPo.getPriceTotal() * 100)) {
			logger.info("后台计算总价：" + priceTotal);
			logger.info("前台计算总价：" + orderFormPo.getPriceTotal());
			throw new CommonException(ResultMap.newInstance().fail(10003, "商品总价被修改"));
		}

	}

	/**
	 * 微信支付回调
	 */
	@Override
	public String wxNotify() {
		// TODO Auto-generated method stub
		String notityXml = Util.getNotityXml(request);
		Boolean endsign = false;
		// 微信给返回的东西
		try {
			logger.info(notityXml);
			Map<String, String> map = WXPayUtil.xmlToMap(notityXml);
			logger.info(map);
			// 解析各种数据
			String appid = (String) map.get("appid");// 应用ID
			String attach = (String) map.get("attach");// 商家数据包
			String bank_type = (String) map.get("bank_type");// 付款银行
			String cash_fee = (String) map.get("cash_fee");// 现金支付金额
			String fee_type = (String) map.get("fee_type");// 货币种类
			String is_subscribe = (String) map.get("is_subscribe");// 是否关注公众账号
			String mch_id = (String) map.get("mch_id");// 商户号
			String nonce_str = (String) map.get("nonce_str");// 随机字符串
			String openid = (String) map.get("openid");// 用户标识
			String out_trade_no = (String) map.get("out_trade_no");// 获取商户订单号
			String result_code = (String) map.get("result_code");// 业务结果
			String return_code = (String) map.get("return_code");// SUCCESS/FAIL
			String sign = (String) map.get("sign");// 获取签名
			String time_end = (String) map.get("time_end");// 支付完成时间
			String total_fee = (String) map.get("total_fee");// 获取订单金额
			String trade_type = (String) map.get("trade_type");// 交易类型
			String transaction_id = (String) map.get("transaction_id");// 微信支付订单号
			// 数据加密
			endsign = WXPayUtil.isSignatureValid(map, wxPayConfigImpl.getKey(), SignType.HMACSHA256);
			logger.info(
					"**************************************************************************************************");
			logger.info(appid + "-------------------应用ID");
			logger.info(attach + "-------------------商家数据包");
			logger.info(bank_type + "-------------------付款银行");
			logger.info(cash_fee + "-------------------现金支付金额");
			logger.info(fee_type + "-------------------货币种类");
			logger.info(is_subscribe + "-------------------是否关注公众账号");
			logger.info(mch_id + "-------------------商户号");
			logger.info(nonce_str + "-------------------随机字符串");
			logger.info(openid + "-------------------用户标识");
			logger.info(out_trade_no + "-------------------获取商户订单号");
			logger.info(result_code + "-------------------业务结果");
			logger.info(return_code + "------------------- SUCCESS/FAIL");
			logger.info(sign + "-------------------获取签名-微信回调的签名");
			logger.info(time_end + "-------------------支付完成时间");
			logger.info(total_fee + "-------------------获取订单金额");
			logger.info(trade_type + "-------------------交易类型");
			logger.info(transaction_id + "-------------------微信支付订单号");
			logger.info(endsign + "-------------------第二次加密sign");
			logger.info(
					"**************************************************************************************************");

			if (endsign) {

				// 签名成功
				// 处理业务
				// 将订单设置为已处理,库存-1,销量+1
				orderFormMapper.update(DigestUtils.md5Hex(out_trade_no), 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return payResult(endsign);
	}

	private String payResult(Boolean endsign) {
		// TODO Auto-generated method stub
		if (endsign) {
			// 签名成功
			logger.info("回调成功");
			return setXml("SUCCESS", "OK");
			// start
		} else {// 签名不一致
			System.err.println("签名不一致！");
			return setXml("FAIL", "签名不一致！");
		}
	}

	private String setXml(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]>" + "</return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}

	@Override
	public ResultMap selectAll(String id, Boolean isForUser, String userWeiXinId, Integer currentPage,
			Integer pageSize) {
		// TODO Auto-generated method stub
		if (isForUser && userWeiXinId == null) {
			return ResultMap.newInstance().fail(10002, "isForUser为true时,userWeiXinId为必传参数");
		}
		List<OrderForm> data = orderFormMapper.selectAll(id, isForUser, userWeiXinId, (currentPage - 1) * pageSize,
				pageSize);

		// 分页
		LimitPage limitPage = new LimitPage();
		Integer maxSize = orderFormMapper.selectMaxSize(id, isForUser, userWeiXinId);
		limitPage.setMaxSize(maxSize);
		limitPage.setCurrentPage(currentPage);
		limitPage.setPageSize(pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		map.put("limitPage", limitPage);
		return ResultMap.newInstance().success(map);
	}

	@Override
	public ResultMap selectOneById(String id) {
		// TODO Auto-generated method stub
		OrderForm data = orderFormMapper.selectOneById(id);
		return ResultMap.newInstance().success(data);
	}

	@Override
	public ResultMap update(String id, Integer status) {
		// TODO Auto-generated method stub
		Boolean data = orderFormMapper.update(id, status);
		return ResultMap.newInstance().success(data);
	}
	//add by qinyanyu 判断订单是否存在，是则删除已存在订单并生成新的订单
	public void checkOrder(String orderNos){
		try {
			if(orderNos != null && !"".equals(orderNos)){
				orderFormMapper.deleteOrderFormByNo(orderNos);
				orderFormInfoMapper.deleteOrderInfoById(orderNos);
				logger.info("删除重复订单："+orderNos);
			}
		} catch (Exception e) {
			logger.info("删除订单列表异常"+"订单号:"+orderNos+" 异常："+e.getMessage());
		}
	}
	//end by qinyanyu 判断订单是否存在，是则删除已存在订单并生成新的订单
}	
