package server;

import model.Author;
import model.Eintrag;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persist.MySessionFactory;
import rest.Rest;
import servlet.Servlet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



			//WTF?
			MySessionFactory mySessionFactory= MySessionFactory.getInstance();
			Author a= new Author();
			a.setName("TestUser");
			a.setEmail("test@email.com");

			Eintrag e= new Eintrag();
			e.setTitle("Titel");
			e.setAuthor(a);
			e.setDeleted(false);

			mySessionFactory.save(a);
			mySessionFactory.save(e);

			SessionFactory sessionFactory= mySessionFactory.getSessionFactory();
			Session session= sessionFactory.openSession();

			String[] selectiontags = {"tag_0", "tag_1"};

			Set<String> selectionTags= new HashSet<String>();
			selectionTags.add("tag_0");
			selectionTags.add("tag_1");

			//String dump= "select distinct a from Article a " +
			//		"join a.tags t " +
			//		"where t.name in (:tags)";
			//
			//String hql= "select e from Eintrag e " +
			//		"join e.tag t " +
			//		"where t.tag in (:inputtags)";

			String hqltest= "select distinct e.tag from Eintrag e " +
					"";

			//Query query= session.createQuery(hqltest);
			//query.setParameterList("inputtags", selectionTags);

			List<Eintrag> erg= session.createQuery(hqltest).list();


			for(Eintrag eintrag : erg) {
				System.out.println("In loop");
				System.out.println(eintrag.getTitle());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}