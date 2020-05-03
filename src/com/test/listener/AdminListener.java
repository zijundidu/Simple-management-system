package com.test.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AdminListener
 *
 */
@WebListener
public class AdminListener implements ServletContextListener {
	
    public AdminListener() {
    	
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }
    
    public void contextInitialized(ServletContextEvent sce)  { 
    	//创建map
    	Map<String, Integer> map = new LinkedHashMap<String, Integer>();
    	//得到ServletContext
    	ServletContext application = sce.getServletContext();
    	//把map保存到application
    	application.setAttribute("map", map);
    }
	
}
