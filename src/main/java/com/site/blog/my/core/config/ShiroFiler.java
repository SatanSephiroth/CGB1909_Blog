package com.site.blog.my.core.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 解决在没有注销的情况下重新登陆不跳转
 * @author YouZiao
 */
public class ShiroFiler extends FormAuthenticationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginSubmission(request, response) && isLoginRequest(request, response)) {
			return false;
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}
}
