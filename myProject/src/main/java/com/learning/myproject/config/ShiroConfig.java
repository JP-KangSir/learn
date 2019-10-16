package com.learning.myproject.config;


import com.learning.myproject.config.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: huoming
 * @Email : honghuoming@zhehekeji.com
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.profiles.active}")
    private String profile;


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(10);
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCacheManager(new MemoryConstrainedCacheManager());
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }


    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap<>();
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login/in", "anon");
        filterMap.put("/login/getToken", "anon");
        filterMap.put("/login/resetPassword/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/**/*.html", "anon");
        filterMap.put("/**/*.js", "anon");
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**", "jwt");
        filterMap.put("/login/out", "logout");
        filters.put("jwt", new JwtFilter());
        shiroFilter.setFilters(filters);
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }


   /* *//**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     *//*
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","/user/403");
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("exception");     // Default is "exception"
        //r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }*/

}