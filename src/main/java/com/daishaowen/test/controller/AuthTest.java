package com.daishaowen.test.controller;

import com.daishaowen.test.httpClient.HttpRequest;

/**
 * Created by disvenk.dai on 2018-07-30 16:28
 */
public class AuthTest {

    public static void main(String[] args) {
        //String s = HttpRequest.sendPost("http://localhost:9030/uaa/oauth/token", "grant_type=authorization_code&appId=webApp&appSecret=webApp&code=A4nUfF");
        String s = HttpRequest.sendPost("http://localhost:9030/uaa/oauth/jwt", "grant_type=authorization_code&client_id=webApp&code=x725qM");
        System.out.println(s);
       /* HttpRequest get = HttpRequest.get(
               "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa574d31b1db4000d&redirect_uri=http%3A%2F%2Fdianji.restoplus.cn%2Fwechat%2Fshop%2Fnew%2Fcurrentshop%3Fcode%3D001XKvsa1GnanQ1GJVta1dJosa1XKvs0&response_type=code&scope=snsapi_base&state=1533723447916&connect_redirect=1#wechat_redirect");
        System.out.println(get.body());*/
//        HttpGet httpPost=new HttpGet("http://ecosystem.restoplus.cn/wechat/index?vv=SZJzCUYgOjk.");
//        httpPost.setHeader("Host", "ecosystem.restoplus.cn");
//        httpPost.setHeader("Referer", "https://ecosystem.restoplus.cn");
//        httpPost.setHeader("User-Agent", "AliAppMozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1 wechatdevtools/0.7.0 MicroMessenger/6.3.9 Language/zh_CN webview/0");
//        httpPost.setHeader("Connection", "keep-alive");
//        String html="";
//        try {
//            CloseableHttpClient https = HttpClients.createDefault();
//            HttpResponse response = https.execute(httpPost);
//            HttpEntity entitySort = response.getEntity();
//            html= EntityUtils.toString(entitySort, "utf-8");
//            System.out.println(html);
//
//        } catch (ClientProtocolException e) {
//
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
   }
}