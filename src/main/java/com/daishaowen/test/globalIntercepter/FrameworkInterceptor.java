package com.daishaowen.test.globalIntercepter;

import com.daishaowen.test.chinaMobile.annotationResolve.action.ActionContext;
import com.daishaowen.test.chinaMobile.annotationResolve.action.IWorkAction;
import com.daishaowen.test.chinaMobile.constants.FrameConstants;
import com.daishaowen.test.chinaMobile.dto.ClientErrorDto;
import com.daishaowen.test.chinaMobile.exception.*;
import com.daishaowen.test.chinaMobile.utils.BusinessContext;
import com.daishaowen.test.chinaMobile.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 通用拦截器
 *
 * @author wangchao
 *
 */
public class FrameworkInterceptor implements HandlerInterceptor {

	private static final String encoding = "UTF-8";

	/**
	 * 拦截器中依次执行的所有动作
	 */
	List<IWorkAction> workActions;

	/**
	 * 拦截器中异常信息 是否使用编码<br>
	 * <li>NO : 不使用编码</li><br>
	 * <li>URL : 使用url编码</li><br>
	 * <li>BASE64 : 使用base64编码</li>
	 */
	String ENCODETYPE = "NO";

	/*
	 * @Resource FIXME luyao private OperationLogUtil operationLogUtil;
	 */

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FrameworkInterceptor.class);

	/*
	 * @Resource FIXME luyao private ISessionService sessionService;
	 */

	/**
	 * 通用mapper
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
				.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,
						true)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
						false)
				.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	}

	@Override
	public void afterCompletion(final HttpServletRequest arg0,
                                final HttpServletResponse arg1, final Object arg2,
                                final Exception arg3) throws Exception {// NOPMD

		logDeubg(LOGGER, "afterCompletion executed");

		// 清空上下文
		BusinessContext.clear();
	}

	@Override
	public void postHandle(final HttpServletRequest arg0,
                           final HttpServletResponse arg1, final Object arg2,
                           final ModelAndView arg3) throws Exception {// NOPMD

	}

	@Override
	public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response, final Object obj)
			throws Exception {// NOPMD

		// 预设的拦截器预处理结果标记
		boolean flag = true;
		try {
			// 初始化方法
			init(request);

			/*
			 * // 获取日志信息入口 FIXME luyao
			 * operationLogUtil.getLogFromInterceptor(request, obj);
			 */

			// 设置响应头
			//setHeader(response);
			final ActionContext context = new ActionContext(request,
					(HandlerMethod) obj);

			for (final IWorkAction iWorkAction : workActions) {

				if (iWorkAction.isMatch(context)) {
					iWorkAction.doAction(context);
					iWorkAction.after(context);
				}
			}
		} catch (final NeedLoginException e) {
			throw e;
		} catch (final RuntimeException e) {
			LOGGER.error(e.getLocalizedMessage(), e);
			flag = false;
			// 拼装返回报文
			//to do something
			sendResponse(new Object(), HttpServletResponse.SC_OK, response);
		} catch (final Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
			flag = false;
			// 初始化一个基础系统异常类型定义
			ExceptionType excpType = ExceptionEnum.EXECUTE_RUNTIME_EXCP
					.getExceptionType();
			if (e instanceof BusinessException) {// NOPMD
				excpType = ((BusinessException) e).getExceptionType();
			} else if (e instanceof SystemException) {// NOPMD
				excpType = ((SystemException) e).getExceptionType();
			}
			// 拼装返回报文
			//to do something
			sendResponse(new Object(), HttpServletResponse.SC_OK, response);
		}

		return flag;
	}

	private void init(final HttpServletRequest request) {

	}

	private void setHeader(final HttpServletResponse response) {

		// 支持跨站调用 FIXME 修改成相应的域名 不要使用*
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");

	}

	/**
	 * 如果是debug级别 记录日志
	 *
	 * @param LOGGER
	 * @param content
	 */
	private void logDeubg(final Logger LOGGER, final String content) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(content);
		}
	}

	/**
	 * 返回response
	 *
	 * @param data
	 * @param responseStatus
	 * @param response
	 */
	public void sendResponse(final Object data, final int responseStatus,
			final HttpServletResponse response) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		try {
			final PrintWriter writer = response.getWriter();
			String afterStr = null;
			switch (ENCODETYPE) {
			case "NO":
				afterStr = new String(mapper.writeValueAsBytes(data), encoding);
				break;
			case "URL":
				afterStr = URLEncoder.encode(mapper.writeValueAsString(data),
						encoding);
				break;
			case "BASE64":
				afterStr = String.valueOf(Base64.encodeBase64(mapper
						.writeValueAsBytes(data)));
				break;
			default:
				break;
			}
			writer.write(afterStr);
			response.setStatus(responseStatus);
			response.flushBuffer();
		} catch (final IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	public List<IWorkAction> getWorkActions() {

		return workActions;
	}

	public void setWorkActions(final List<IWorkAction> workActions) {

		this.workActions = workActions;
	}

}
