package com.site.blog.my.core.util;

import org.apache.shiro.SecurityUtils;

import com.site.blog.my.core.entity.AdminUser;

public class ShiroUtil {
	// 接收用户身份
	public static AdminUser getLoginUser() {
		return (AdminUser) SecurityUtils.getSubject().getPrincipal();
	}

	// 接收用户名
	public static String getLoginName() {
		return getLoginUser().getLoginUserName();
	}

	// 接收昵称
	public static String getNickName() {
		return getLoginUser().getNickName();
	}

	// 接受用户id
	public static Integer getUserId() {
		return getLoginUser().getAdminUserId();
	}
}
