package com.site.blog.my.core.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.blog.my.core.dao.AdminUserMapper;
import com.site.blog.my.core.entity.AdminUser;

@Service
public class ShiroAdminRealm extends AuthorizingRealm {
	@Autowired
	private AdminUserMapper adminUserMapper;

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		// 构建凭证匹配对象
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		// 设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		// 设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.获取用户名(用户页面输入)
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		// 2.基于用户名查询用户信息
		AdminUser user = adminUserMapper.findByUserName(username);
		// 5.封装用户信息
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, // principal (身份)
				user.getLoginPassword(), // hashedCredentials
				getName());// realmName
		// 6.返回封装结果
		return info;// 返回值会传递给认证管理器
		// (后续认证管理器会通过此信息完成认证操作)
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
