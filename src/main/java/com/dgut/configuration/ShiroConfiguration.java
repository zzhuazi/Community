package com.dgut.configuration;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.dgut.realm.UserRealm;

/**
 * @ClassName: ShiroConfiguration
 * @Description: spring-boot集成shiro配置类
 * @author 闲明苑
 * @date 2017年9月13日 上午11:26:56
 */
@Configuration
public class ShiroConfiguration {

	/**
	 * @Title: getLifecycleBeanPostProcessor
	 * @Description: 实例化lifecycleBeanPostProcessor
	 * Shiro生命周期处理器
	 * @return LifecycleBeanPostProcessor
	 * @throws
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * @Title: getDefaultAdvisorAutoProxyCreator
	 * @Description: 实例化DefaultAdvisorAutoProxyCreator
	 * @return DefaultAdvisorAutoProxyCreator
	 * @throws
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	/**
	 * @Title: getRealm
	 * @Description: 实例化自定义realm
	 * @return Realm
	 * @throws
	 */
	@Bean
	public Realm getRealm() {
		return new UserRealm();
	}

	/**
	 * @Title: getDefaultWebSecurityManager
	 * @Description: 实例化securityManager
	 * @return DefaultWebSecurityManager
	 * @throws
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(getRealm());
		return securityManager;
	}

	/**
	 * @Title: getShiroFilterFactoryBean
	 * @Description: 实例化shiroFilter
	 * @return ShiroFilterFactoryBean
	 * @throws
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面;
		return shiroFilterFactoryBean;
	}

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * @return
	 */
	@Bean
	@DependsOn({ "lifecycleBeanPostProcessor" })
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		advisorAutoProxyCreator.setApplyCommonInterceptorsFirst(true);
		return advisorAutoProxyCreator;
	}

}
