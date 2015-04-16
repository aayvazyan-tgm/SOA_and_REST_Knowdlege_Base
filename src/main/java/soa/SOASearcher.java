package soa;

import model.Eintrag;
import model.Tag;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import persist.MySessionFactory;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "soa.Searchable")
public class SOASearcher implements Searchable {
    private SessionFactory sf;

    public SOASearcher(SessionFactory sf) {
        this.sf = sf;
    }

    public SOASearcher() {
        sf = MySessionFactory.getInstance().getSessionFactory();
    }

    /**
     * The method search is returning the results of a search.
     *
     * @param searchFor
     * @return String results
     */
    @Override
    public String search(String searchFor) {
        StringBuilder sb = new StringBuilder();

        // open Session
        Session session = sf.openSession();

        // create query
        Query q = session.getNamedQuery("searchForTag");

        // setting parameters
        q.setParameter("searchFor", searchFor);

        // save starting time
        long startTime = System.currentTimeMillis();

        // run query and fetch reslut
        List<Tag> res = q.list();

        // add to result string
        if (res.size() >= 1) {
            List<Eintrag> eintraege = res.get(0).getEintraege();
            for(Eintrag e : eintraege) {
                sb.append("Knowledge: " + e.toString());
            }
        }

        String s = sb.toString();
        String newString = "";
        long estimatedTime = System.currentTimeMillis() - startTime;
        newString += "Searching took " + estimatedTime / 1000 + " seconds\n";
        newString += s;

        // flush
        session.flush();
        session.close();

        return newString;
    }
}