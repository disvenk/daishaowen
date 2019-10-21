package com.daishaowen.test.chinaMobile.annotationResolve.action;


import com.daishaowen.test.chinaMobile.annotation.ValidDto;
import com.daishaowen.test.chinaMobile.dto.ClientErrorDto;
import com.daishaowen.test.chinaMobile.exception.BusinessException;
import com.daishaowen.test.chinaMobile.exception.ExceptionEnum;
import com.daishaowen.test.chinaMobile.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * 参数预处理
 *
 * @author wangchao
 *
 */
public class ValidateAction implements IWorkAction {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ValidateAction.class);

	private static String ILLEGAL_STR = "{}";

	/**
	 * 通用mapper
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		// json->bean ,忽略bean中不存在的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		// bean->json ,忽略为null的属性
		mapper.setSerializationInclusion(Include.NON_NULL);
		// map->json ,忽略为null的key
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 转义字符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	@Override
	public void after(final ActionContext context) {

		// nothing to do
	}

	@Override
	public boolean doAction(final ActionContext context) throws IOException {

		final HandlerMethod handlerMethod = context.getHandlerMethod();
		final String jsonData = getData(context.getRequest());
		boolean result = false;
		try {
			result = validateJson(jsonData, handlerMethod);
		} catch (final BusinessException e) {
			throw e;
		} catch (final Exception e) {
			final ClientErrorDto clientDto = new ClientErrorDto();
			clientDto.setCrossFieldErrorMessage("入参格式不正确");
			final String errorMsg = JsonUtil.toString(clientDto);
			throw new RuntimeException("入参格式不正确s");
//			throw new BusinessException(
//					ExceptionEnum.BASE_CHECK_EXCP.setExceptionMsg(errorMsg));
		}
		return result;
	}

	private String getData(final HttpServletRequest request) throws IOException {

		final String method = request.getMethod();
		if ("GET".equals(method) || "DELETE".equals(method)) {
			return request.getQueryString();
		}
		final StringBuilder buffer = new StringBuilder();
		String line;
		final BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	private void validateObject(final Object validObject) {

		Validate.notNull(validObject);
		final ValidatorFactory factory = Validation
				.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		final Set<ConstraintViolation<Object>> violations = validator
				.validate(validObject);
		if (!violations.isEmpty()) {
			final Iterator<ConstraintViolation<Object>> it = violations
					.iterator();
			final ClientErrorDto clientDto = new ClientErrorDto();
			while (it.hasNext()) {
				final ConstraintViolation<Object> violation = it.next();
				clientDto.getFieldErrorMessage().put(
						String.valueOf(violation.getPropertyPath()),
						violation.getMessage());
			}
			final String errorMsg = JsonUtil.toString(clientDto);
			LOGGER.debug(errorMsg);

			// 参数验证失败 抛出自定义异常
			throw new BusinessException(
					ExceptionEnum.BASE_CHECK_EXCP.setExceptionMsg(errorMsg));
		}
	}

	public boolean validateJson(final String jsonData,
			final HandlerMethod handlerMethod) throws IOException {

		final ValidDto validDto = handlerMethod
				.getMethodAnnotation(ValidDto.class);
		if (validDto != null) {
			if (!StringUtils.isEmpty(jsonData) && !ILLEGAL_STR.equals(jsonData)) {
				final MethodParameter[] methodParameters = handlerMethod
						.getMethodParameters();
				/*
				 * for (MethodParameter methodParameter : methodParameters) {
				 * Object validObject = mapper.readValue(jsonData,
				 * mapper.getTypeFactory
				 * ().constructType(methodParameter.getParameterType()));
				 * validateObject(validObject); }
				 */
				// 只校验第一个入参
				final Object validObject = mapper.readValue(
						jsonData,
						mapper.getTypeFactory().constructType(
								methodParameters[0].getParameterType()));
				validateObject(validObject);
			} else {
				final ClientErrorDto clientDto = new ClientErrorDto();
				clientDto.setCrossFieldErrorMessage("入参不能为空");
				final String errorMsg = JsonUtil.toString(clientDto);
				throw new BusinessException(
						ExceptionEnum.BASE_CHECK_EXCP.setExceptionMsg(errorMsg));
			}
		}
		return true;
	}

	@Override
	public boolean isMatch(final ActionContext context) {

		final ValidDto validDto = context.getHandlerMethod()
				.getMethodAnnotation(ValidDto.class);
		return validDto != null;
	}

}
