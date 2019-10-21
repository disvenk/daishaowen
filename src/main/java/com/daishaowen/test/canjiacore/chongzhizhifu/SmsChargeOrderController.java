//package com.daishaowen.test.canjiacore.chongzhizhifu;
//
//import com.daishaowen.test.canjiacore.alipay.util.AlipaySubmit;
//import com.daishaowen.test.canjiacore.entity.Result;
//import com.daishaowen.test.canjiacore.enums.ChargePayType;
//import com.daishaowen.test.canjiacore.model.SmsChargeOrder;
//import com.daishaowen.test.canjiacore.payUtil.PayConfigUtil;
//import com.daishaowen.test.canjiacore.service.SmsChargeOrderService;
//import com.daishaowen.test.canjiacore.util.WeChatPayUtils;
//import com.google.zxing.WriterException;
//
//import org.apache.commons.lang3.StringUtils;
//import org.dom4j.DocumentException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.List;
//import java.util.Map;
//
//import static com.daishaowen.test.canjiacore.payUtil.PayConfigUtil.getWxPayHtml;
//
//
//@Controller
//@RequestMapping("smschargeorder")
//public class SmsChargeOrderController extends GenericController {
//
//	@Resource
//	private SmsChargeOrderService smsChargeOrderService;
//
//	@RequestMapping("/smsCharge")
//	public void smsCharge(String chargeMoney, String paytype, HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException, DocumentException{
//		String returnHtml = PayConfigUtil.RETURNHTML;
////		String errorHtml="<h1>充值金额小于100无法充值<h1>";
//		if(StringUtils.isNotEmpty(chargeMoney) && StringUtils.isNotEmpty(paytype)){
//			//判断下单的金额是否大于100
////			if(new BigDecimal(chargeMoney).compareTo(new BigDecimal("100"))==-1){
////				log.info("充值金额不足100，无法充值");
////				outprint(errorHtml, response);
////			}
////			else{
//				SmsChargeOrder smsChargeOrder = smsChargeOrderService.saveSmsOrder(getCurrentBrandId(), chargeMoney);//创建充值订单
//				String out_trade_no = smsChargeOrder.getId();
//				if(paytype.equals(ChargePayType.ALI_PAY+"")){//支付宝支付
//					String show_url = "";///商品展示页面
//					String notify_url = getBaseUrl()+ PayConfigUtil.SMS_ALIPAY_NOTIFY_URL;
//					String return_url = getBaseUrl()+ PayConfigUtil.SMS_ALIPAY_RETURN_URL;
//
//					/*String notify_url = "http://cb56c9b7.ngrok.io/resto/"+ PayConfigUtil.SMS_ALIPAY_NOTIFY_URL;
//					String return_url = "http://cb56c9b7.ngrok.io/resto/"+ PayConfigUtil.SMS_ALIPAY_RETURN_URL;*/
//
//					String subject = PayConfigUtil.SMS_SUBJECT;
//					Map<String, String> formParame = AlipaySubmit.createFormParame(out_trade_no, subject, chargeMoney, show_url, notify_url, return_url, null);
//					returnHtml = AlipaySubmit.buildRequest(formParame, "post", "确认");
//				}else if(paytype.equals(ChargePayType.WECHAT_PAY+"")){//微信支付
//					String spbill_create_ip = InetAddress.getLocalHost().getHostAddress();
//					String notify_url =  getBaseUrl()+"paynotify/wxpay_notify";
//					log.info("微信的通知路径为："+notify_url);
//					String body = "【餐加】短信充值";
//					Map<String,String> apiReqeust = WeChatPayUtils.createWxPay(out_trade_no, chargeMoney, spbill_create_ip, notify_url,body);
//					if("true".equals(apiReqeust.get("success"))){
//						request.getSession().setAttribute("wxPayCode", apiReqeust.get("url"));
//						returnHtml = getWxPayHtml();
//					}
//				}
//			}
//			PayConfigUtil.outprint(returnHtml, response);
//		//}
//	}
//
//	/**
//	 * 生成微信支付的 二维码
//	 * @param response
//	 * @param request
//	 */
//	@RequestMapping("/createWxPayCode")
//	@ResponseBody
//	public void createWxPayCode(HttpServletResponse response, HttpServletRequest request){
//		PayConfigUtil.createWxPayCode(response,request);
//	}
//}
