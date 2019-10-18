package com.yangzb.framework.common.base.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@SuppressWarnings("all")
@Component
public class SpringBeanHelper implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static MutablePropertySources propertySources;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringBeanHelper.applicationContext == null) {
            SpringBeanHelper.applicationContext = applicationContext;
		}
	}

	/**
	 * 根据名称获取唯一Bean
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	/**
	 * 根据类型获取唯一Bean，若该类型存在多个Bean，则抛出异常
	 * 
	 * @param beanName
	 * @return
	 */
	public static <T> T getBean(Class<T> beanType) {
		return (T) applicationContext.getBean(beanType);
	}

	/**
	 * 根据名称获取唯一Bean，若该名称对应Bean不存在，则根据类型获取Bean，若该类型存在多个Bean，则抛出异常
	 * 
	 * @param beanName
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> beanType) {
		Object obj = getBean(beanName);
		if (obj == null) {
			return (T) getBean(beanType);
		}
		return (T) obj;
	}

	/**
	 * 指定名称的Bean是否存在
	 * 
	 * @param beanName
	 * @return
	 */
	public static boolean existsBean(String beanName) {
		return applicationContext.containsBean(beanName);
	}

	/**
	 * 指定名称的Bean的定义是否存在
	 * 
	 * @param beanName
	 * @return
	 */
	public static boolean existsBeanDefinition(String beanName) {
		return applicationContext.containsBeanDefinition(beanName);
	}

	/**
	 * Return whether the local config factory contains a config of the given name,
	 * ignoring beans defined in ancestor contexts.
	 * <p>
	 * This is an alternative to {@code containsBean}, ignoring a config of the given
	 * name from an ancestor config factory.
	 * 
	 * @param name
	 *            the name of the config to query
	 * @return whether a config with the given name is defined in the local factory
	 * @see BeanFactory#containsBean
	 */
	public static boolean existsLocalBean(String beanName) {
		return applicationContext.containsLocalBean(beanName);
	}

	/**
	 * 是否存在指定类型的Bean
	 * 
	 * @param beanType
	 * @return
	 */
	public static boolean existsBean(Class<?> beanType) {
		return applicationContext.getBeanNamesForType(beanType).length > 0;
	}

	/**
	 * 得到Spring ApplicationContext容器
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static String getLocalProperty(String propertyKey) {
		return getLocalProperty(propertyKey, null);
	}

	public static void registerBeanDefinition(String beanName,Class baenClazz) {
		// 将applicationContext转换为ConfigurableApplicationContext
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
		// 获取bean工厂并转换为DefaultListableBeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
		// 通过BeanDefinitionBuilder创建bean定义
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(baenClazz);
		// 注册bean
		defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
	}

	public static String getLocalProperty(String propertyKey, String defaultValue) {
		if (propertySources == null && applicationContext != null) {
			PropertySourcesPlaceholderConfigurer placeholder = applicationContext.getBean(PropertySourcesPlaceholderConfigurer.class);
			if (placeholder != null) {
				try {
					Class<?> clazz = placeholder.getClass();
					Field field = clazz.getDeclaredField("propertySources");
					field.setAccessible(true);
					Object obj = field.get(placeholder);
					if (obj != null && obj instanceof MutablePropertySources) {
						propertySources = (MutablePropertySources) obj;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (propertySources == null) {
			return defaultValue;
		} else {
			PropertySource<?> source = propertySources.get("localProperties");
			Object property = source.getProperty(propertyKey);
			return property == null ? defaultValue : property.toString();
		}
	}

}
