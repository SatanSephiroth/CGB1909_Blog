package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.AdminUser;

public interface AdminUserService {

	/**
	 * 根据用户名查找对应信息
	 * 
	 * @param userName
	 * @return
	 */
	AdminUser findByUserName(String userName);

	/**
	 * 登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	AdminUser login(String userName, String password);

	/**
	 * 获取用户信息
	 *
	 * @param loginUserId
	 * @return
	 */
	AdminUser getUserDetailById(Integer loginUserId);

	/**
	 * 修改当前登录用户的密码
	 *
	 * @param loginUserId
	 * @param originalPassword
	 * @param newPassword
	 * @return
	 */
	Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

	/**
	 * 修改当前登录用户的名称信息
	 *
	 * @param loginUserId
	 * @param loginUserName
	 * @param nickName
	 * @return
	 */
	Boolean updateName(Integer loginUserId, String loginUserName, String nickName);

	/**
	 * 添加用户
	 * 
	 * @param userName
	 * @param password
	 * @param cfgPassword
	 * @return
	 */
	int insert(String userName, String password, String cfgPassword, String nickName);

}
