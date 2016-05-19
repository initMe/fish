package com.fish.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

/**
 * 扩展contextLoad功能,能够对log4j
 *       的配置文件进行替换,实现日志的配置功能,
 * 动态设置日志输出路径，设置日志级别
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener{
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		Properties property = new Properties();
		try {
			InputStream inStream  = ContextLoaderListener.class.getClassLoader().getResourceAsStream("config.properties");
			property.load(inStream);
			System.setProperty("loggingRoot", property.getProperty("loggingRoot"));
			System.setProperty("loggingLevel", property.getProperty("loggingLevel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.contextInitialized(event);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

}
