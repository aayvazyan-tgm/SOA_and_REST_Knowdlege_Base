package server;

import model.Author;
import model.Eintrag;
import model.Tag;
import mysql.ConnectMysql;
import mysql.DataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persist.MySessionFactory;
import rest.Rest;
import servlet.Servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Embeds a Jetty server to host the Servlet.
 */
public class JettyEmbeddedRunner {
	public void startServer(int port) {
		try {
			Server server = new Server();
			ServerConnector c = new ServerConnector(server);
			c.setIdleTimeout(1000);
			c.setAcceptQueueSize(10);
			c.setPort(port);
			c.setHost("localhost");
			ServletContextHandler handler = new ServletContextHandler(server,
					"/", true, false);
			ServletHolder servletHolder = new ServletHolder(
					Servlet.class);
			handler.addServlet(servletHolder, "/*");
			server.addConnector(c);
			servletHolder.setInitOrder(0);


			ServletHolder jerseyServlet = handler.addServlet(
					org.glassfish.jersey.servlet.ServletContainer.class, "/rest/*");
			jerseyServlet.setInitOrder(0);

			// Tells the Jersey Servlet which REST service/class to load.
			jerseyServlet.setInitParameter(
					"jersey.config.server.provider.classnames",
					Rest.class.getCanonicalName());

			server.start();

			DataSource ds= DataSource.get();
			ds.setDatabasename("knowldegebase");
			ds.setServername("192.168.0.14");
			ds.setUsername("helmuth");
			ds.setPassword("helmuth");

			ConnectMysql cm= ConnectMysql.get(ds);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}