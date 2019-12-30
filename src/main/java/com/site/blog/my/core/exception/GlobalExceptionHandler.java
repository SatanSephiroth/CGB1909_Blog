package com.site.blog.my.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.blog.my.core.controller.vo.JsonResult;

/**
 * @ControllerAdvice 注解描述的类为全局异常处理类
 * 	此类中可以定义全局的异常处理方法
 */
//@ResponseBody
//@RestControllerAdvice //==@ControllerAdvice+@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * @ExceptionHandler 注解描述的方法为一个异常处理方法
	 * @author tarena
	 */
	@ExceptionHandler(NullPointerException.class)
	public String doHandleRuntimeException(NullPointerException e) {
		return "admin/login";
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException(ServiceException e) {
		e.printStackTrace();
		return new JsonResult(e);
	}
	
}
