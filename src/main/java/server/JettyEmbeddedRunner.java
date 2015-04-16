package server;

import model.Author;
import model.Eintrag;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import persist.MySessionFactory;
import servlet.Servlet;

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
			server.start();

			MySessionFactory mySessionFactory= MySessionFactory.getInstance();

			Author a= new Author();
			a.setName("TestUser");
			a.setEmail("test@email.com");

			Eintrag e= new Eintrag();
			e.setAuthor(a);
			e.setDeleted(false);

			mySessionFactory.save(a);
			mySessionFactory.save(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}