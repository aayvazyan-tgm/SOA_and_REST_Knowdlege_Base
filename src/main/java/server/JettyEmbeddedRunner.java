package server;

import model.Author;
import model.Eintrag;
import model.Tag;
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
			mySessionFactory.saveOrUpdate(a);

			Eintrag e= new Eintrag();
			e.setTitle("Titel");
			e.setAuthor(a);
			e.setDeleted(false);

			mySessionFactory.saveOrUpdate(e);

			// write to manytomany
			SessionFactory sessionFactory= mySessionFactory.getSessionFactory();
			Session session= sessionFactory.openSession();

			session.beginTransaction();

			Set<String> tags= new HashSet<String>();
			tags.add("tag_0");
			tags.add("tag_1");
			tags.add("tag_2");

			Tag t0= new Tag();
			t0.setTags(tags);

			tags.clear();
			tags.add("tag_3");
			tags.add("tag_4");
			tags.add("tag_5");

			Tag t1= new Tag();
			t1.setTags(tags);

			Set<Tag> setTags= new HashSet<Tag>();
			setTags.add(t0);
			setTags.add(t1);

			e.setTags(setTags);

			session.saveOrUpdate(e);
			session.getTransaction().commit();

			// working ---- ^^

			// select from Eintrag

			String hqltest= "from Eintrag";

			session.beginTransaction();

			List<Eintrag> erg= session.createQuery(hqltest).list();

			for(Eintrag eintrag : erg) {
				System.out.println(eintrag.getTitle());
			}

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}