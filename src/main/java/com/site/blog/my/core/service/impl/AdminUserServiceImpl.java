package com.site.blog.my.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.site.blog.my.core.dao.AdminUserMapper;
import com.site.blog.my.core.entity.AdminUser;
import com.site.blog.my.core.exception.ServiceException;
import com.site.blog.my.core.service.AdminUserService;
import com.site.blog.my.core.util.MD5Util;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, passwordMd5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新密码并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

	@Override
	public int insert(String userName, String password, String cfgPassword,String nickName) {
		// 参数校验
		if (nickName == null||nickName == "") 
			throw new ServiceException("未输入昵称");
		if (userName == null || userName == "") 
			throw new ServiceException("未输入用户名");
		if (password == null || cfgPassword == null) 
			throw new ServiceException("未输入密码");
		if (!(password.equals(cfgPassword))) 
			throw new ServiceException("两次密码输入不一致");
		//密码MD5加密
		String mPassword = MD5Util.MD5Encode(password, "UTF-8");
		//添加数据并返回
		int rows = adminUserMapper.insert(userName, mPassword, nickName);
		return rows;
	}

	@Override
	public AdminUser findByUserName(String userName) {
		AdminUser user = adminUserMapper.findByUserName(userName);
		return user;
	}
}
