/*
 * Copyright 2015-2017 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mybatis.generator.custom.jui;

import java.io.Serializable;

/**
 * 封装jui
 * 
 * @author wujing
 */
public class Jui implements Serializable {

	private static final long serialVersionUID = 1L;

	private int statusCode; // 必选。状态码
	private String message; // 可选。信息内容。
	private String navTabId; // 可选。
	private String rel; // 可选。
	private String callbackType; // 可选。callbackType="closeCurrent"关闭当前tab，callbackType="forward"需要forwardUrl值
	private boolean forwardUrl; // 可选
	private String confirmMsg; // 可选

	/**
	 * @param navTabId
	 */
	public Jui(int statusCode, String navTabId, String message, String callbackType) {
		this.statusCode = statusCode;
		this.navTabId = navTabId;
		this.message = message;
		this.callbackType = callbackType;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public boolean isForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(boolean forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	@Override
	public String toString() {
		return "Jui [statusCode=" + statusCode + ", message=" + message + ", navTabId=" + navTabId + ", rel=" + rel + ", callbackType=" + callbackType + ", forwardUrl=" + forwardUrl + ", confirmMsg=" + confirmMsg + "]";
	}

}
