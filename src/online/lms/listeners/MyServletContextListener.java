package online.lms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import online.lms.database.DBHandler;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DBHandler handler = DBHandler.getInstance();
		ServletContext context = sce.getServletContext();
		context.setAttribute("dbHandler", handler);
	}

}
