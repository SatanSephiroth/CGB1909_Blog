package com.site.blog.my.core.exception;

public class ServiceException extends RuntimeException {//非检查异常
	private static final long serialVersionUID = -5598865415547474216L;

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
