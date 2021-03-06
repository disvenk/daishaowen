//package com.daishaowen.test.sso;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJacksonValue;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.common.pojo.TaotaoResult;
//import com.common.utils.CookieUtils;
//import com.common.utils.JsonUtils;
//import com.taotao.sso.service.UserLoginService;
//
//@Controller
//public class UserLoginController {
//
//	@Autowired
//	private UserLoginService userLoginServiceImpl;
//	@Value("${COOKIE_TOKEN_KEY}")
//	private String COOKIE_TOKEN_KEY;
//
//	@RequestMapping(value="/user/login",method=RequestMethod.POST)
//	@ResponseBody
//	public TaotaoResult userLogin(String username,String password,
//			HttpServletRequest request,HttpServletResponse response){
//		TaotaoResult result = userLoginServiceImpl.userLogin(username, password);
//		String jwt = result.getData().toString();
//		CookieUtils.setCookie(request, response, COOKIE_TOKEN_KEY, jwt);
//		return result;
//		//return null;
//	}
//
//	//根据token来去user信息的传统方式spring4.1之前的方式
///*	@RequestMapping(value="/user/jwt/{jwt}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public String getUserByToken(@PathVariable String jwt,String callback){
//		TaotaoResult result = userLoginServiceImpl.getUserByToken(jwt);
//		if(StringUtils.isNotBlank(callback)){
//			String jsonResult = callback+"("+JsonUtils.objectToJson(result)+")";
//			return jsonResult;
//		}
//
//		return JsonUtils.objectToJson(result);
//	}*/
//
//	@RequestMapping(value="/user/jwt/{jwt}")
//	@ResponseBody
//	public Object getUserByToken(@PathVariable String jwt,String callback){
//		TaotaoResult result = userLoginServiceImpl.getUserByToken(jwt);
//		if(StringUtils.isNotBlank(callback)){
//			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
//			mappingJacksonValue.setJsonpFunction(callback);
//			return mappingJacksonValue;
//			//return null;
//		}
//
//		return result;
//		//return null;
//	}
//
//	//用户注销
//		@RequestMapping("/user/logout/{jwt}")
//		@ResponseBody
//		public TaotaoResult userLogout(@PathVariable String jwt){
//			// TaotaoResult result = userLoginServiceImpl.userLogout(jwt);
//			//return result;
//			return null;
//		}
//}
