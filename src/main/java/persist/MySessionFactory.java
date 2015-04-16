package persist;

import model.Author;
import model.Eintrag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Liefert eine SessionFactory
 *
 * Created by helmuthbrunner on 08/04/15.
 */
public class MySessionFactory {

    private static SessionFactory sessionFactory;
    private static MySessionFactory instance;

    private MySessionFactory() {

            Configuration config= new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://10.0.104.48/knowlegebase")
                    .setProperty("hibernate.connection.username", "helmuth")
                    .setProperty("hibernate.connection.password", "helmuth")
                    .setProperty("hibernate.hbm2ddl.auto", "create")
                    .setProperty("hibernate.show_sql", "true")

                    .addAnnotatedClass(Author.class)
                    .addAnnotatedClass(Eintrag.class);

            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties());
            sessionFactory= config.buildSessionFactory(ssrb.build());

    }

    public static MySessionFactory getInstance() {
        if(instance==null) {
            instance= new MySessionFactory();
        }
        return instance;
    }

    public void save(Object u) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.persist(u);
        transaction.commit();
        session.close();
    }

    public void remove(Object o) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(o);
        transaction.commit();
        session.close();
    }

    public void update(Object o) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.update(o);
        transaction.commit();
        session.close();
    }

    public List readWhere(String table, String field, String value, boolean isString) {
        Session session= sessionFactory.openSession();
        String s= "";
        if(isString)
            s= "from " + table + " where " + field + "='"+value+"'";
        else
            s= "from " + table + " where " + field + "=" + value;

        List l= session.createQuery(s).list();
        session.close();
        return l;
    }

    public List read(String table, String key) {
        Session session= sessionFactory.openSession();
        String s= "from " + table +  " where id=" + key;
        List l= session.createQuery(s).list();
        session.close();
        return l;
    }
}
