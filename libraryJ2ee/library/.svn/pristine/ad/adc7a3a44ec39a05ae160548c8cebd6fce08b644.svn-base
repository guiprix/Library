package it.univaq.mwt.j2ee.library.presentation.common;

import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.impl.JDBCLibraryBusinessFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class LibraryServletContextListener implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = ( Context ) initCtx.lookup( "java:comp/env" );
			DataSource dataSource = ( DataSource ) envCtx.lookup( "jdbc/librarydb" );		

			LibraryBusinessFactory.setInstance(new JDBCLibraryBusinessFactory(dataSource));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}


	
	
}
