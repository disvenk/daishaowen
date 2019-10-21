package com.daishaowen.test.httpClient;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * HttpClient调用工具类
 * 
 * @author wangxuejun
 */
public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static final String CHARSET_UTF8 = Consts.UTF_8.displayName();
	private static HttpClient _client;

	public static PoolingClientConnectionManager cm = null;

	public static synchronized PoolingClientConnectionManager initPoolHttpClient(final SchemeRegistry schemeRegistry) {
		if (_client == null) {
			cm = new PoolingClientConnectionManager(schemeRegistry, 60000, TimeUnit.MILLISECONDS);
			cm.setDefaultMaxPerRoute(10);
			cm.setMaxTotal(1000);
			return cm;
		}
		return null;
	}

	public static synchronized HttpClient getHttpClient() {
		if (null == _client) {
			HttpParams params = new BasicHttpParams();
			// 设置一些基本参数
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, CHARSET_UTF8);
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpProtocolParams.setUserAgent(params, "Mozilla/5.0 (Windows NT 6.2; WOW64) " + "AppleWebKit/537.36 (KHTML, like Gecko) "
					+ "Chrome/27.0.1453.94 Safari/537.36");
			/* 连接超时 */
			HttpConnectionParams.setConnectionTimeout(params, 3000);
			/* 请求超时 */
			HttpConnectionParams.setSoTimeout(params, 10000);
			params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

			// 设置我们的HttpClient支持HTTP和HTTPS两种模式
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
			schReg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

			// 使用线程安全的连接管理来创建HttpClient
			BasicClientConnectionManager conMgr = new BasicClientConnectionManager(schReg);

			cm = initPoolHttpClient(schReg);
			_client = new DefaultHttpClient(cm, params);
			// _client = new DefaultHttpClient(conMgr, params);
		}
		return _client;
	}

	/**
	 * 发送GET请求
	 * 
	 * @param uri
	 * @return
	 */
	public static String sendGet(String uri) {
		String responseBody = null;
		HttpClient httpClient = getHttpClient();
		try {
			HttpGet httpGet = new HttpGet(uri);
			logger.debug("getting request " + httpGet.getURI());
			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpClient.execute(httpGet, responseHandler);
			logger.debug("----------------------------------------");
			logger.debug(responseBody);
			logger.debug("----------------------------------------");
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return responseBody;
	}

	/**
	 * 下载URL成为byte[]
	 * 
	 * @param uri
	 * @return
	 */
	public static byte[] download(String uri) {
		byte[] responseBody = null;
		HttpClient httpClient = getHttpClient();
		try {
			HttpGet httpGet = new HttpGet(uri);
			logger.debug("getting request " + httpGet.getURI());
			// Create a response handler
			HttpResponse resp = httpClient.execute(httpGet);
			HttpEntity entity = resp.getEntity();
			responseBody = EntityUtils.toByteArray(entity);
			logger.debug("----------------------------------------");
			logger.debug("byte[] length: " + responseBody.length);
			logger.debug("----------------------------------------");
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return responseBody;
	}

	/**
	 * 发送POST请求
	 * 
	 * @param uri
	 * @param paramMap
	 *            请求参数
	 * @return
	 */
	public static String sendPost(String uri, Map<String, String> paramMap) {
		return sendPost(uri, paramMap, "UTF-8");
	}

	/**
	 * 发送POST请求
	 * 
	 * @param uri
	 * @param paramMap
	 *            请求参数
	 * @param charset
	 *            参数编码
	 * @return
	 */
	public static String sendPost(String uri, Map<String, String> paramMap, String charset) {
		return sendPost(uri, paramMap, charset, null);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param uri
	 * @param paramMap
	 *            请求参数
	 * @param charset
	 *            参数编码
	 * @param cookieMap
	 *            cookie数据
	 * @return
	 */
	public synchronized static String sendPost(String uri, Map<String, String> paramMap, String charset, Map<String, String> cookieMap) {
		String responseBody = null;
		HttpClient httpClient = getHttpClient();
		try {
			HttpPost httpPost = new HttpPost(uri);
			logger.debug("posting request " + httpPost.getURI());
			if (paramMap != null) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>(paramMap.size());
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
					nvps.add(nvp);
				}
				if (charset != null) {
					httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
				} else {
					httpPost.setEntity(new UrlEncodedFormEntity(nvps));
				}
			}

			if (cookieMap != null) {
				for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
					httpPost.addHeader(new BasicHeader("Cookie", entry.getKey() + "=" + entry.getValue()));
				}
			}

			HttpResponse resp = httpClient.execute(httpPost);

			HttpEntity entity = resp.getEntity();
			if (charset != null) {
				responseBody = EntityUtils.toString(entity, charset).trim();
			} else {
				responseBody = EntityUtils.toString(entity).trim();
			}

			logger.debug("----------------------------------------");
			logger.debug(responseBody);
			logger.debug("----------------------------------------");
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return responseBody;

	}

	/**
	 * 发送JSON格式参数的POST请求
	 * 
	 * @param uri
	 * @param paramMap
	 *            请求参数
	 * @return
	 */
	public static String sendJsonPost(String uri, String content) {
		return sendPost(uri, ContentType.APPLICATION_JSON, null, content);
	}

	/**
	 * 指定格式参数的POST请求
	 * 
	 * @return
	 */
	public synchronized static String sendPost(String uri, ContentType contentType, Map<String, String> headerMap, String content) {
		String responseBody = null;
		HttpClient httpClient = getHttpClient();
		try {
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("Content-Type", contentType + ";charset=utf-8");
			logger.debug("posting request " + httpPost.getURI());
			if (headerMap != null && CollectionUtils.isNotEmpty(headerMap.keySet())) {
				for (String key : headerMap.keySet()) {
					httpPost.setHeader(key, headerMap.get(key));
				}
			}
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(content.getBytes("utf-8")));
			requestBody.setContentLength(content.getBytes("utf-8").length);
			httpPost.setEntity(requestBody);

			HttpEntity entity = httpClient.execute(httpPost).getEntity();
			if (entity != null) {
				responseBody = EntityUtils.toString(entity, "utf-8");
				EntityUtils.consume(entity);
			}
			logger.debug("----------------------------------------");
			logger.debug(responseBody);
			logger.debug("----------------------------------------");
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return responseBody;

	}

	/**
	 * 获取请求数据
	 * 
	 * @param uriStr
	 *            请求路径
	 * @param param
	 *            请求参数
	 * @return
	 * */
	private static String getResponse(String uriStr, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(uriStr);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(50000);
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 测试支付
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	// String key = "5E1524AABA00627C87DD2E28726AA785";
	// SortedMap<String, String> packageParams = new TreeMap<String, String>();
	// packageParams.put("apiCode", LF_BARCODEPAY.APICODE.getString());
	// packageParams.put("versionCode", LF_BARCODEPAY.VERSIONCODE.getString());
	// packageParams.put("inputCharset",
	// LF_BARCODEPAY.INPUTCHARSET.getString());
	// packageParams.put("signType", LF_BARCODEPAY.SIGNTYPE.getString());
	// packageParams.put("notifyURL",
	// "http://165c16y687.iok.la:24783/manager/app/notifyUrl");
	// packageParams.put("outOrderId", "DD123324");
	// packageParams.put("amount", "0.01");
	// packageParams.put("paymentType",
	// LF_BARCODEPAY.PAYMENTTYPE_ALL.getString());
	// // packageParams.put("interfaceCode",
	// LF_BARCODEPAY.ALIPAYTM_ALIPAY_WALLET.getString());// 支付宝
	// packageParams.put("interfaceCode",
	// LF_BARCODEPAY.WXMICROPAY_WECHAT_WALLET.getString());// 微信
	// packageParams.put("retryFalg", "FALSE");
	// packageParams.put("submitTime", new
	// SimpleDateFormat("yyyyMMddHHmmss").format(new
	// Date(System.currentTimeMillis())));
	// packageParams.put("timeout", "10H");
	// packageParams.put("productName", "测试商品");
	// packageParams.put("partner", "8614271579");
	// packageParams.put("authCode", "130617881932687307");
	//
	// String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
	//
	// packageParams.put("sign", sign);
	//
	// String url = "http://61.50.130.246:18081/gateway/api/trade.htm";
	//
	// String str = returnResponse(packageParams);
	// String content = getResponse(url, str);
	// System.out.println(content);
	// }

	/**
	 * 订单查询
	 * 
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

//		String key = "5E1524AABA00627C87DD2E28726AA785";
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("queryCode", LF_DIRECTSINGLEQUERY.QUERYCODE.getString());
//		packageParams.put("inputCharset", LF_DIRECTSINGLEQUERY.INPUTCHARSET.getString());
//		packageParams.put("partner", "8614271579");
//		packageParams.put("outOrderId", "DD123322");
//		packageParams.put("signType", LF_DIRECTSINGLEQUERY.SIGNTYPE.getString());
//
//		String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
//
//		packageParams.put("sign", sign);
//
//		String url = "http://61.50.130.246:18081/gateway/query.htm";
//
//		String str = returnResponse(packageParams);
//		String content = getResponse(url, str);
//		String contentString = java.net.URLDecoder.decode(content, "UTF-8");
//		Map<String, String> map = new HashMap<String, String>();
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			map = mapper.readValue(contentString, new TypeReference<HashMap<String, String>>() {
//			});
//			String responseCode = map.get("responseCode");
//			String partnerOrderStatus = map.get("partnerOrderStatus");
//			String refundStatus = map.get("refundStatus");
//			if (!"".equals(responseCode) && "0000".equals(responseCode)) {
//				if (!"".equals(partnerOrderStatus) && "00".equals(partnerOrderStatus)) {
//					System.out.println("**支付成功**");
//				}
//				if (!"".equals(refundStatus) && "03".equals(refundStatus)) {
//					System.out.println("**发起退款**");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(contentString);
	}

	/**
	 * 测试退款
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	//
	// String key = "5E1524AABA00627C87DD2E28726AA785";
	// SortedMap<String, String> packageParams = new TreeMap<String, String>();
	// packageParams.put("apiCode", LF_DIRECTREFUND.APICODE.getString());
	// packageParams.put("versionCode",
	// LF_DIRECTREFUND.VERSIONCODE.getString());
	// packageParams.put("inputCharset",
	// LF_DIRECTREFUND.INPUTCHARSET.getString());
	// packageParams.put("signType", LF_DIRECTREFUND.SIGNTYPE.getString());
	// packageParams.put("partner", "8614271579");
	// packageParams.put("outOrderId", "DD123322");
	// packageParams.put("outRefundNo", "TK" + DateUtil.getCurrentTimeStamp());
	// packageParams.put("amount", "0.01");
	// packageParams.put("refundAmount", "0.01");
	// packageParams.put("callerCode", "single");
	//
	// String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
	//
	// packageParams.put("sign", sign);
	//
	// String url = "http://61.50.130.246:18081/gateway/api/refund.htm";
	//
	// String str = returnResponse(packageParams);
	// String content = getResponse(url, str);
	// System.out.println(content);
	// }

	/**
	 * 异步回调方法
	 * 
	 * @param request
	 */
	@RequestMapping("/notifyUrl")
	@ResponseBody
	protected String notifyUrl(HttpServletRequest request) {
		// // 处理结果码
		String responseCode = request.getParameter("responseCode");
		if (!"".equals(responseCode) && "0000".equals(responseCode)) {

			return "SUCCESS";
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static String returnResponse(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String str = sb.substring(0, sb.length() - 1);
		return str;
	}

}
