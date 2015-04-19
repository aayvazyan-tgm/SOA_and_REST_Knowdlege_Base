package rest;

/**
 * @author Ari Ayvazyan
 * @version 16.04.2015
 */
import model.Author;
import model.Eintrag;
import mysql.ConnectMysql;
import mysql.DataSource;
import org.hibernate.Session;
import persist.MySessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("/customers")
public class Rest {

    private Session session;

    @PersistenceContext(unitName="CustomerService",
            type=PersistenceContextType.TRANSACTION)

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void create(Eintrag eintrag) {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{name}")
    public Eintrag read(@PathParam("name") String name) {
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void update(Eintrag customer) {
    }

    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name") String name) {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("findThreadByTag/{tag}")
    public List<Eintrag> findThreadByTag(@PathParam("tag") String tag) {
        ConnectMysql connectMysql= ConnectMysql.get(DataSource.get());

        ResultSet rs= connectMysql.executeQuery("select * from Eintrag e " +
                "inner join Tag_Eintrag te on te.id_eintrag = e.id" +
                "inner join Tag t on t.id = te.id_tag" +
                "where t.tag = '" + tag +  "';");


        List<Eintrag> l= new ArrayList<Eintrag>();

        try {
            while(rs.next()) {
                Eintrag e= new Eintrag();
                Author a= new Author();

                ResultSet rs1= connectMysql.executeQuery("select * from Author where email='" + rs.getString("email") + "'");
                while(rs1.next()) {
                    a.setName(rs1.getString("name"));
                    a.setEmail(rs.getString("email"));
                }

                e.setId(rs.getInt("id"));
                e.setTitle(rs.getString("title"));
                e.setAuthor(a);

                l.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }

}
