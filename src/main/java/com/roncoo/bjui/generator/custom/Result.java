package com.roncoo.bjui.generator.custom;

import java.io.Serializable;

/**
 * 接口返回对象实体
 * 
 * @author wujing
 * @param <T>
 */
public class Result<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	private Integer errCode = 99;

	/**
	 * 错误信息
	 */
	private String errMsg = null;

	/**
	 * 返回结果实体
	 */
	private T resultData = null;

	public Result() {
	}

	public Result(T resultData) {
		this.resultData = resultData;
	}

	public Result(int errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "Result [errCode=" + errCode + ", errMsg=" + errMsg + ", resultData=" + resultData + "]";
	}

}
