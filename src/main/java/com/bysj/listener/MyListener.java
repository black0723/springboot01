package com.bysj.listener;

import com.bysj.utils.MyConstant;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 注册servlet三大组件的监听器
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("web应用启动了。。。");
        ServletContext context = sce.getServletContext();
        MyConstant.DICTIONARIES = MyConstant.getDictionary();
        context.setAttribute("SYS_NAME", MyConstant.SYS_NAME);
        context.setAttribute("dictionary", MyConstant.DICTIONARIES);
        System.out.println(context.getContextPath() + " starting ....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
