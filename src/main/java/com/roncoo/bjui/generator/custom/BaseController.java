package com.roncoo.bjui.generator.custom;

import java.text.MessageFormat;

import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * 控制基础类，所以controller都应该继承这个类
 * 
 * @author wujing
 */
public class BaseController extends Base {

	public static final String TEXT_UTF8 = "text/html;charset=UTF-8";
	public static final String JSON_UTF8 = "application/json;charset=UTF-8";
	public static final String XML_UTF8 = "application/xml;charset=UTF-8";



	public static String redirect(String format, Object... arguments) {
		return new StringBuffer("redirect:").append(MessageFormat.format(format, arguments)).toString();
	}

	/**
	 * 成功提示，关闭当前对话框，并对tabid进行刷新
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * 
	 * @return json字符串
	 */
	protected static String success(String targetId) {
		return bjui(Bjui.OK, Bjui.CLOSE, targetId, "操作成功");
	}

	/**
	 * 成功提示，关闭当前对话框，并对tabid进行刷新
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * 
	 * @return json字符串
	 */
	protected static String success(String targetId, String message) {
		return bjui(Bjui.OK, Bjui.CLOSE, targetId, message);
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @return
	 */
	protected static String delete(String targetId) {
		return delete(targetId, "操作成功");
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String delete(String targetId, String message) {
		return bjui(Bjui.OK, Bjui.OPEN, targetId, message);
	}

	/**
	 * 信息提示，不关闭当前对话框
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String ties(String message) {
		return bjui(Bjui.OK, Bjui.OPEN, "", message);
	}

	/**
	 * 错误提示，不关闭当前对话框，自定义提示信息
	 * 
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String error(String message) {
		return bjui(Bjui.ER, Bjui.OPEN, "", message);
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	private static String bjui(int statusCode, Boolean closeCurrent, String targetId, String message) {
		Bjui bj = new Bjui();
		bj.setStatusCode(statusCode);
		bj.setCloseCurrent(closeCurrent);
		bj.setTabid(targetId);
		bj.setMessage(message);
		return JSONUtil.toJsonStr(bj);
	}

}
