package com.daishaowen.test.chinaMobile.annotationResolve.action;

/**
 * 在拦截器中执行的动作
 *
 * @author wangchao
 *
 */
public interface IWorkAction {

	/**
	 * 方法后处理
	 */
	void after(ActionContext context);

	/**
	 * 主业务，如果正常处理完毕返回true
	 *
	 * @return
	 */
	boolean doAction(ActionContext context) throws Exception;// NOPMD

	/**
	 * 判断是否执行此action
	 *
	 * @return true 需要执行 false 不需要
	 */
	boolean isMatch(ActionContext context);

}
