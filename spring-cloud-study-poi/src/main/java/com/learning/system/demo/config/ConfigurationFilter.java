package com.learning.system.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;

import com.learning.system.demo.util.BasePath;


@Configuration
public class ConfigurationFilter{

	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}
	@Bean
	public FilterRegistrationBean<MyFilter> testFilterRegistration() {
		FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<MyFilter>();
		registration.setFilter(new MyFilter());//添加过滤器
		registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
		registration.addInitParameter("admin", "moshow");//添加默认参数
		registration.setName("MyFilter");//设置优先级
		registration.setOrder(1);//设置优先级
		return registration;
	}
	@Bean
	public FilterRegistrationBean<HttpPutFormContentFilter> testFilterRegistration2() {
		FilterRegistrationBean<HttpPutFormContentFilter> registration = new FilterRegistrationBean<HttpPutFormContentFilter>();
		registration.setFilter(new HttpPutFormContentFilter());//添加过滤器
		registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
		registration.setName("HttpPutFormContentFilter");//设置优先级
		registration.setOrder(2);//设置优先级
		return registration;
	}
	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> testFilterRegistration3() {
		FilterRegistrationBean<HiddenHttpMethodFilter> registration = new FilterRegistrationBean<HiddenHttpMethodFilter>();
		registration.setFilter(new HiddenHttpMethodFilter());//添加过滤器
		registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
		registration.setName("HiddenHttpMethodFilter");//设置优先级
		registration.setOrder(2);//设置优先级
		return registration;
	}
	public class MyFilter implements Filter {
		public void destroy() {
		}

		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain
				filterChain)
						throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) srequest;
			//放入basePath，供html页面调用
			request.setAttribute("basePath", BasePath.getBasePath(request));
			filterChain.doFilter(srequest, sresponse);
		}

		public void init(FilterConfig arg0) throws ServletException {
		}
	}

}
