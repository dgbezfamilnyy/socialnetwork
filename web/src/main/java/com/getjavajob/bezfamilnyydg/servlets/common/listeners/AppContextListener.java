package com.getjavajob.bezfamilnyydg.servlets.common.listeners;

import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import com.getjavajob.bezfamilnyydg.servlets.common.util.WebAppContainer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        WebAppContainer.setAccountService(webApplicationContext.getBean(AccountService.class));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
