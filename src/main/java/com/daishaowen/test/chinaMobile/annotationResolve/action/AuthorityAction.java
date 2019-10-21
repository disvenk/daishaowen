package com.daishaowen.test.chinaMobile.annotationResolve.action;


import com.daishaowen.test.chinaMobile.annotation.Authority;
import com.daishaowen.test.chinaMobile.constants.FrameConstants;
import com.daishaowen.test.chinaMobile.dto.AuthorityDto;
import com.daishaowen.test.chinaMobile.exception.BusinessException;
import com.daishaowen.test.chinaMobile.exception.ExceptionEnum;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * To verify authority
 *
 * @author yuanjian
 *
 */
public class AuthorityAction implements IWorkAction {

//	@Resource
//	private IAuthorityService authorityService;

//	@Resource
//	private OperatorAuthorityCache operatorAuthorityCache;

	@Override
	public boolean isMatch(final ActionContext context) {

		final Authority authority = context.getHandlerMethod()
				.getMethodAnnotation(Authority.class);
		return authority != null;
	}

	@Override
	public boolean doAction(final ActionContext context) throws Exception {// NOPMD

		final HttpSession session = context.getRequest().getSession();
		final Object obj = session.getAttribute(FrameConstants.USER_ID);
		if (null == obj) {// 该用户没有登录
			throw new BusinessException(
					ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg("请登录！"));
		} else {// 用户登录了
			final int uid = (int) obj;
			// int uid = 1;
			if (!isAuthority(uid, context)) {// 检查权限
				throw new BusinessException(
						ExceptionEnum.BUSINESS_CHECK_EXCP
								.setExceptionMsg("你没有权限！"));
			}
		}

		return true;
	}

	/**
	 * To check if this operator is of authority to invoke
	 *
	 * @param uid
	 *            - operator id
	 * @return true : yes | false : no
	 * @throws Exception
	 */
	private boolean isAuthority(final int uid, final ActionContext context)
			throws Exception {// NOPMD

		final Authority authority = context.getHandlerMethod()
				.getMethodAnnotation(Authority.class);
		if (null == authority) {// 检查是否有权限检查要求，如果没有权限检查要求那么默认就是有访问该接口的权限
			return true;
		}
		final Authority.Name[] names = authority.name();
		final List<String> nameList = new ArrayList<String>();
		for (final Authority.Name name : names) {
			nameList.add(name.value);
		}
		// 从redis缓存中获取该用户的权限列表
		//List<AuthorityDto> list = operatorAuthorityCache.getList(uid);
//		if (null == list || 0 == list.size()) {// 如果缓存中没有这个用户的权限列表
//			list = authorityService.getAuthorityListByID(uid);
//			/*
//			 * 1. 如果数据库中, list还是空， 那么证明这个运营者没有权限 (系统完成之后这种情况应该不会发生，
//			 * 因为创建运营者的时候都会有一个角色， 角色对应有相关的权限) 2. 如果该运营者没有方法所标注的权限
//			 */
//			if (!isContains(list, nameList)) {
//				return false;
//			} else {
//				// 如果数据库里存在权限，那么放到缓存里
//				operatorAuthorityCache.set(uid, list);
//			}
//		} else if (!isContains(list, nameList)) {
//			return false;
//		}
		return true;
	}

	@Override
	public void after(final ActionContext context) {

		// nothing to do
	}

	/**
	 * 判断AuthorityDto列表是否含有name
	 *
	 * @param list
	 *            运营者所拥有的权限编号
	 * @param nameList
	 *            注解的权限编号
	 * @return true/false
	 */
	private boolean isContains(final List<AuthorityDto> list,
			final List<String> nameList) {

		boolean flag = false;
		if (null != list && null != nameList) {
			for (final String name : nameList) {
				for (final AuthorityDto authorityDto : list) {
					if (authorityDto.getName().equalsIgnoreCase(name)) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 判断AuthorityDto列表是否含有name
	 *
	 * @param list
	 *            运营者所拥有的权限编号
	 * @param nameList
	 *            注解的权限编号
	 * @return true/false
	 */
	// private boolean isContains1(final List<AuthorityDto> list,
	// final List<String> nameList) {
	//
	// boolean flag = false;
	// if (null != list && null != nameList) {
	//
	// for (final AuthorityDto authorityDto : list) {
	// for (final String name : nameList) {
	// if (authorityDto.getName().equalsIgnoreCase(name)) {
	// flag = true;
	// break;
	// }
	// }
	// }
	// }
	// return flag;
	// }

}
