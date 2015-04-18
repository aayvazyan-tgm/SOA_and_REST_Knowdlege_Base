package rest;

/**
 * @author Ari Ayvazyan
 * @version 16.04.2015
 */
import model.Eintrag;

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

    @PersistenceContext(unitName="CustomerService",
            type=PersistenceContextType.TRANSACTION)
    EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void create(Eintrag eintrag) {
        entityManager.persist(eintrag);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{name}")
    public Eintrag read(@PathParam("name") String name) {
        return entityManager.find(Eintrag.class, name);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void update(Eintrag customer) {
        entityManager.merge(customer);
    }

    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name") String name) {
        Eintrag customer = read(name);
        if(null != customer) {
            entityManager.remove(customer);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("findThreadByTag/{tag}")
    public List<Eintrag> findThreadByTag(@PathParam("tag") String tag) {
        Query query = entityManager.createQuery("SELECT c FROM Eintrag c", Eintrag.class); //TODO YAY Helmuth machts
        query.setParameter("tag", tag);
        return query.getResultList();
    }

}
