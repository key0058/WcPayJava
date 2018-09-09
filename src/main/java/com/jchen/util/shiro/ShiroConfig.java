package com.jchen.util.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.jchen.util.jwt.JwtShiroFilter;

@Configuration
public class ShiroConfig {
	
	/**
	 * 	将自己的验证方式加入容器
	 * 	@return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public ShiroRealm myRealm() {
		return new ShiroRealm();
	}
	
	/**
	 *	 权限管理，配置主要是Realm的管理认证
	 * @param myRealm
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(ShiroRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		return securityManager;
	}
	
	/**
	 * Filter工厂，设置对应的过滤条件和跳转条件
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<String, Filter>();
		filterMap.put("jwt", new JwtShiroFilter());
		filterMap.put("user", new UserShiroFilter());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("/index", "anon");
//		map.put("/user/login", "anon");
//		map.put("/user/**", "jwt");
		map.put("/jwt/**", "jwt");	
		map.put("/**", "authc");
		
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/index");
		shiroFilterFactoryBean.setSuccessUrl("/menu");
		shiroFilterFactoryBean.setUnauthorizedUrl("/index");
		shiroFilterFactoryBean.setFilters(filterMap);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}
	
	
	/**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
	
	/**
     * 	LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 	负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 	主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	/**
     * 	AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 	内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	

}




