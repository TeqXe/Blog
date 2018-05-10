package top.yuyg.blog.config;

import java.util.HashMap;
import java.util.Map;

import top.yuyg.blog.common.xss.XssFilter;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean shiroFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		registration.addInitParameter("targetFilterLifecycle", "true");
		registration.setEnabled(true);
		registration.setOrder(Integer.MAX_VALUE - 1);
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.setFilter(new XssFilter());
		registration.addUrlPatterns("/*");
		registration.setName("xssFilter");
		registration.setOrder(Integer.MAX_VALUE);
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("allowOrigins", "*");
		initParameters.put("allowMethods", "GET,POST,PUT,DELETE,OPTIONS");
		registration.setInitParameters(initParameters);
		return registration;
	}
}
