package com.site.blog.my.core.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringShiroConfig {

	@Bean
	public SecurityManager securityManager(Realm realm) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		return sManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(@Autowired SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/admin/doLogin");
		// 定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		// 静态资源允许匿名访问:"anon"
		map.put("/admin/login","anon");
		map.put("/blog/**", "anon");
		map.put("/common/**", "anon");
		map.put("/admin/dist/**", "anon");
		map.put("/admin/regist", "anon");
		map.put("/admin/doRegist", "anon");
		map.put("/admin/plugins/**", "anon");
		map.put("/search/**", "anon");
		map.put("/blog/**", "anon");
		map.put("/", "anon");
		map.put("/admin/logout", "logout");
		// 除了匿名访问的资源,其它都要认证("authc")后访问
		map.put("/**", "authc");
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}
	
	
}
