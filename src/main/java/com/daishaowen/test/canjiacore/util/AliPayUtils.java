//package com.daishaowen.test.canjiacore.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.domain.AlipayTradePayModel;
//import com.alipay.api.request.*;
//import com.alipay.api.response.AlipayTradeCancelResponse;
//import com.alipay.api.response.AlipayTradePayResponse;
//import com.alipay.api.response.AlipayTradeQueryResponse;
//import com.alipay.api.response.AlipayTradeRefundResponse;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by KONATA on 2016/11/20.
// * 支付宝工具类
// */
//public class AliPayUtils {
//
//
////    private static final String baseUrl = "https://openapi.alipaydev.com/gateway.do"; //沙箱环境
//    private static final String baseUrl = "https://openapi.alipay.com/gateway.do"; //正式环境
//
//    private static AlipayClient alipayClient;
//
//
//    public static void connection(String appId, String privateKey, String publicKey, Integer aliEncrypt) {
//        if (aliEncrypt == 0) {
//            alipayClient = new DefaultAlipayClient(baseUrl, appId, privateKey, "json", "GBK", publicKey);
//        } else if (aliEncrypt == 1) {
//            alipayClient = new DefaultAlipayClient(baseUrl, appId, privateKey, "json", "GBK", publicKey, "RSA2");
//        }
//    }
//
//    public static void phonePay(HttpServletResponse httpResponse, Map map, String returnUrl, String nofityUrl) throws Exception {
//        JSONObject jsonObject = new JSONObject(map);
//        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
//        if(!StringUtils.isEmpty(returnUrl)){
//            alipayRequest.setReturnUrl(returnUrl); //支付完成后回调地址
//        }
//
//        alipayRequest.setNotifyUrl(nofityUrl);//在公共参数中设置回跳和通知地址
//        alipayRequest.setBizContent(jsonObject.toString());
//        String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//        httpResponse.setContentType("text/html;");
//        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
//        httpResponse.getWriter().flush();
//    }
//
//    /**
//     * 面对面支付->条码支付
//     * @param json
//     * @return
//     * @throws AlipayApiException
//     */
//    public static Map<String, Object> tradePay(JSONObject json) throws AlipayApiException {
//        AlipayTradePayRequest request = new AlipayTradePayRequest();
//        AlipayTradePayModel model = new AlipayTradePayModel();
//        request.setBizModel(model);
//
//        model.setOutTradeNo(json.getString("out_trade_no"));
//        model.setSubject(json.get("subject").toString());
//        model.setTotalAmount(json.getString("total_amount"));
//        model.setAuthCode(json.getString("auth_code"));//沙箱钱包中的付款码
//        model.setScene(json.getString("scene"));
//
//        AlipayTradePayResponse response = null;
//        Map result = new HashMap();
//        try {
//            response = alipayClient.execute(request);
//            if(response.isSuccess()){
//                result.put("success", true);
//                result.put("msg", response.getOutTradeNo());
//            }else{
//                result.put("success", false);
//                result.put("msg", response.getSubMsg());
//                result.put("sub_code", response.getSubCode());
//            }
//            return result;
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//            result.put("success", false);
//            result.put("msg", "付款出错，请线下处理");
//            return result;
//        }
//    }
//
//
//    /**
//     * 面对面支付->查询订单状态
//     * @param jsonObject
//     * @return
//     */
//    public static Map<String, Object> tradeQuery(JSONObject jsonObject)  {
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        Map result = new HashMap();
//        request.setBizContent(jsonObject.toString());
//        AlipayTradeQueryResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//            if(response.isSuccess()){
//                result.put("success", true);
//                result.put("msg", response.getBody());
//                result.put("trade_status", response.getTradeStatus());
//                result.put("trade_no", response.getTradeNo());
//                result.put("total_amount", response.getTotalAmount());
//                return result;
//            }else{
//                result.put("success", false);
//                result.put("msg", response.getSubMsg());
//                result.put("sub_code", response.getSubCode());
//                return result;
//            }
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//            result.put("success", false);
//            result.put("msg", "查询出错，请线下处理");
//            return result;
//        }
//
//    }
//
//    /**
//     * 面对面支付撤销订单接口
//     * @param jsonObject
//     * @return
//     */
//    public static Map<String, Object> tradeCancel(JSONObject jsonObject){
//        //返回的map
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("success", false);
//        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
//        //封装订单信息
//        request.setBizContent(jsonObject.toJSONString());
//        AlipayTradeCancelResponse response = null;
//        try{
//            response = alipayClient.execute(request);
//            if (response.isSuccess()){
//                resultMap.put("success", true);
//            }else{
//                if (StringUtils.isNotBlank(response.getSubMsg())){
//                    resultMap.put("msg", response.getSubMsg());
//                } else{
//                    resultMap.put("msg", "撤销失败，请线下处理");
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            resultMap.put("msg", "撤销出错，请线下处理");
//        }
//        return resultMap;
//    }
//
//
//    public static String refundPay(Map map){
//        JSONObject jsonObject = new JSONObject(map);
//        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//        request.setBizContent(jsonObject.toString());
//        AlipayTradeRefundResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//            return e.getMessage();
//
//        }
//        return response.getBody();
//    }
//
//    public static void main(String[] args) throws AlipayApiException {
//        connection("2016102800773386",
//                        "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANP6llbFMPEF+Kzn" +
//                        "ZbYUF4Tw30zVIR15geFQWCCYMveflqCyCIYzDctEBhoeXj674Nofv3xyIxVZSTPe" +
//                        "SpGOhjjZRDke1+M++8AUZlIIdDZfkOuoFSxeGYja5m2hWCvUvVg8YbnspRuwjda+" +
//                        "fYeWsk6/aDukpULZqTgkii3f2reXAgMBAAECgYBUXlQfzPQhueKzzpVo1q5Vtxjp" +
//                        "F5rKhGXxK20n6+u9KsNkyfcikodW84gKNTQFe/mOVzx7Z2IXSSYdgsfjDvrUQ72G" +
//                        "UWBESSd+g91SmP0sLwTBk7kQDTzPyNXTKDWdK9ouC3Oho5i+2FLGrdBLizozQpEC" +
//                        "ApNbPeSmiy7dGil+gQJBAPeb3dIH4kSWZWkjdBM5RPBARpZAx1PSyJtHlVR+z2TW" +
//                        "1mPmT9lbecZlPt2YHTvxGClWZ9nwpxcYi3QasCVf93ECQQDbKZzA6WMl2z+gbXoQ" +
//                        "PepJvHDaIt+KhegeRlujAyg1ugx89KvC3UHNF4ajd8FQsAh9c88fzY79LZVpNUTf" +
//                        "g2uHAkEA7Pc+UsM4yGsmonhLnhow37yj0Sgtmwse8XyQbUzvLpJsmy7PPDVPVY+P" +
//                        "moL5d2REu0r2GJ03S+Mxkuv3p80wAQJBAI8G/yfeqDgCd+moyKpk3cu1USjq7Vwn" +
//                        "u65WWGNwIgO+IXxC6P1JDDJekh2If/66gy/sLlYg/po373QzsXj0+W0CQQCu11Ly" +
//                        "56Wz2g/8oHFxHgXy0B1QpUyw8nLq5zoUnPXGx+w1pSA+dtXHo9E+cyFq4l1SWQ6B" +
//                        "KsZHaJMNcA9svoJi",
//                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB"
//        , 0);
//        Map map = new HashMap();
//        map.put("out_trade_no","ac10efee57e04a30b3f2cbd0652ee5d3");
//        map.put("trade_no","2016112121001004850200065496");
//        map.put("refund_amount","0.01");
//        refundPay(map);
//    }
//}
