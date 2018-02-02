package com.almundo.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInit implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Spring root application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		
		//Add this root context to the servlet context as listener
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		//Create spring servlet context
		AnnotationConfigWebApplicationContext servletConfig = new AnnotationConfigWebApplicationContext();
		servletConfig.register(WebConfig.class);
		
		//Add this context to servlet context as servlet
		ServletRegistration.Dynamic registration = servletContext.addServlet(
			"dispatcher", new DispatcherServlet(servletConfig));
		registration.setLoadOnStartup(1);
		registration.addMapping("/almundo/*");
		
	}

}