package com.site.blog.my.core.controller.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基于此对象封装控制层对象的响应结果,
 * 在此对象中应该包含返回到客户端的数据
 * 以及一个状态码和状态信息
 */
@Data
@NoArgsConstructor
public class JsonResult implements Serializable {
	private static final long serialVersionUID = -5766977494287555486L;
	/** 状态码 */
	private int state = 1;// 1 OK,0 error
	/** 状态码对应的信息 */
	private String message = "OK";
	/** 正常数据 */
	private Object data;
	
	public JsonResult(String message) {
		this.message = message;
	}

	public JsonResult(Object data) {
		this.data = data;
	}

	public JsonResult(Throwable e) {
		this.state = 0;
		this.message = e.getMessage();
	}
}
