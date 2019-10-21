package com.daishaowen.test.chinaMobile.annotationResolve.resolver;


import com.daishaowen.test.chinaMobile.annotation.ResponseForJson;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * 返回值处理
 *
 * @author wangchao
 *
 */
public class ResponseForJsonProcessor implements
        HandlerMethodReturnValueHandler {

	/**
	 * json消息转换器
	 */
	private HttpMessageConverter jsonConverter;

	/**
	 * 需要编码json消息转换器
	 */
	private HttpMessageConverter encodeJsonConverter;

	private static String CHARSET = "UTF-8";

	@Override
	public void handleReturnValue(final Object obj,
			final MethodParameter methodParameter,
			final ModelAndViewContainer container,
			final NativeWebRequest nativeWebRequest) throws Exception {// NOPMD

		container.setRequestHandled(true);
		ResponseForJson annotation = null;
		final HttpServletResponse httpServletResponse = nativeWebRequest
				.getNativeResponse(HttpServletResponse.class);
		final ServletServerHttpResponse response = new ServletServerHttpResponse(
				httpServletResponse);
		if (obj != null) {
			annotation = methodParameter
					.getMethodAnnotation(ResponseForJson.class);
		} else {// NOPMD
			// FIXME 考虑返回通用的ResponseDto
		}
		if (annotation != null && annotation.dataType() == ResponseForJson.DataType.ENCODEJSON) {
			final MediaType mediaType = new MediaType(
					MediaType.APPLICATION_JSON, Collections.singletonMap(
							"charset", CHARSET));
			encodeJsonConverter.write(obj, mediaType, response);
		} else {
			final MediaType mediaType = new MediaType(
					MediaType.APPLICATION_JSON, Collections.singletonMap(
							"charset", CHARSET));
			jsonConverter.write(obj, mediaType, response);
		}

	}

	@Override
	public boolean supportsReturnType(final MethodParameter methodParameter) {

		final ResponseForJson annotation = methodParameter
				.getMethodAnnotation(ResponseForJson.class);
		return annotation != null;
	}

	public HttpMessageConverter getJsonConverter() {

		return jsonConverter;
	}

	public void setJsonConverter(final HttpMessageConverter jsonConverter) {

		this.jsonConverter = jsonConverter;
	}

	public HttpMessageConverter getEncodeJsonConverter() {

		return encodeJsonConverter;
	}

	public void setEncodeJsonConverter(
			final HttpMessageConverter encodeJsonConverter) {

		this.encodeJsonConverter = encodeJsonConverter;
	}

	public static String getCHARSET() {

		return CHARSET;
	}

	public static void setCHARSET(final String cHARSET) {

		CHARSET = cHARSET;
	}

}
