package top.yuyg.blog.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import top.yuyg.blog.modules.sys.oauth.OAuthFilter;
import top.yuyg.blog.modules.sys.oauth.OAuthRealm;

@Configuration
public class ShiroConfig {

	@Bean("sessionManager")
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdCookieEnabled(true);
		return sessionManager;
	}

	@Bean("securityManager")
	public SecurityManager securityManager(OAuthRealm oAuthRealm, SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(oAuthRealm);
		securityManager.setSessionManager(sessionManager);

		return securityManager;
	}

	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		Map<String, Filter> filters = new HashMap<>();
		filters.put("oauth", new OAuthFilter());
		shiroFilter.setFilters(filters);
		Map<String, String> filterMap = new LinkedHashMap<>();
		filterMap.put("/webjars/**", "anon");
		filterMap.put("/druid/**", "anon");
		filterMap.put("/sys/login", "anon");
		filterMap.put("/**/*.css", "anon");
		filterMap.put("/**/*.js", "anon");
		filterMap.put("/**/*.html", "anon");
		filterMap.put("/fonts/**", "anon");
		filterMap.put("/plugins/**", "anon");
		filterMap.put("/favicon.ico", "anon");
		filterMap.put("/", "anon");
		filterMap.put("/**", "oauth");
		shiroFilter.setFilterChainDefinitionMap(filterMap);
		return shiroFilter;
	}

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

}
