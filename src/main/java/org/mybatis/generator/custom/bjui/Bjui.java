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
package org.mybatis.generator.custom.bjui;

import java.io.Serializable;

public class Bjui implements Serializable {
	private static final long serialVersionUID = 486427439928047616L;

	public static final int OK = 200;
	public static final int ER = 300;
	public static final int TO = 301;
	public static final boolean CLOSE = true;
	public static final boolean OPEN = false;

	private int statusCode; // 必选。状态码
	private String message; // 可选。信息内容。
	private String tabid; // 可选。
	private String dialogid; // 可选。
	private String divid; // 可选。
	private boolean closeCurrent; // 可选
	private String forward; // 可选
	private String forwardConfirm; // 可选

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

	public String getTabid() {
		return tabid;
	}

	public void setTabid(String tabid) {
		this.tabid = tabid;
	}

	public String getDialogid() {
		return dialogid;
	}

	public void setDialogid(String dialogid) {
		this.dialogid = dialogid;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public boolean isCloseCurrent() {
		return closeCurrent;
	}

	public void setCloseCurrent(boolean closeCurrent) {
		this.closeCurrent = closeCurrent;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getForwardConfirm() {
		return forwardConfirm;
	}

	public void setForwardConfirm(String forwardConfirm) {
		this.forwardConfirm = forwardConfirm;
	}

	@Override
	public String toString() {
		return "Bjui [statusCode=" + statusCode + ", message=" + message + ", tabid=" + tabid + ", dialogid=" + dialogid + ", divid=" + divid + ", closeCurrent=" + closeCurrent + ", forward=" + forward + ", forwardConfirm=" + forwardConfirm + "]";
	}

}
