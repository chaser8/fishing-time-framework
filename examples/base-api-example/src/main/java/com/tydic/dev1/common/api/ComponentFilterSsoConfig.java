package com.tydic.dev1.common.api;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-05-31.
 * filter注入配置
 */

//@Configuration("componentFilterSsoConfig")
public class ComponentFilterSsoConfig {
    @Autowired
    WebApplicationContext webApplicationConnect;
    @Bean
    public FilterRegistrationBean encodingFilter() {
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CharacterEncodingFilter("UTF-8",true));
        filterRegistrationBean.setName("encodingFilter");
        filterRegistrationBean.addServletNames("dispatcherServlet");
        filterRegistrationBean.setOrder(6);
        return filterRegistrationBean;
    }
//    @Bean
//    public FilterRegistrationBean accessControlFilter(){
//
//        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new AccessControlFilter(webApplicationConnect));
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setName("accessControlFilter");
//        filterRegistrationBean.setOrder(7);
//        return filterRegistrationBean;
//    }
    @Bean
    public FilterRegistrationBean casFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("CASFilter");
        filterRegistrationBean.setOrder(8);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean casValidationFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("CASValidationFilter");
        filterRegistrationBean.setOrder(9);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean casHttpServletRequestWrapperFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HttpServletRequestWrapperFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("CASHttpServletRequestWrapperFilter");
        filterRegistrationBean.setOrder(10);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean casAssertionThreadLocalFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AssertionThreadLocalFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("CASAssertionThreadLocalFilter");
        filterRegistrationBean.setOrder(11);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean casSingleSignOutFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SingleSignOutFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("CASSingleSignOutFilter");
        filterRegistrationBean.setOrder(12);
        return filterRegistrationBean;
    }

//    @Bean
//    public FilterRegistrationBean sessionFilter(){
//        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new SessionFilter(webApplicationConnect));
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setName("sessionFilter");
//        filterRegistrationBean.setOrder(13);
//        return filterRegistrationBean;
//    }


    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener(){
        ServletListenerRegistrationBean<RequestContextListener> bean = new ServletListenerRegistrationBean<>(new RequestContextListener());
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener(){
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> bean = new ServletListenerRegistrationBean<>(new SingleSignOutHttpSessionListener());
        return bean;
    }



}
