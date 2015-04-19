package rest;

import model.Eintrag;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Ari Michael Ayvazyan
 * @version 20.04.2015
 */
public class RestClient {
    private final WebTarget target;
    private final Client client;

    public RestClient() {
        client = ClientBuilder.newClient();
        //query params: ?q=Turku&cnt=10&mode=json&units=metric
        target = client.target("localhost:8081")
                .queryParam("cnt", "10")
                .queryParam("mode", "xml")
                .queryParam("units", "metric");
    }

    public List<Eintrag> getByTag(String tag) {
        return target.queryParam("", tag)
                .path("findThreadByTag/")
                .request(MediaType.APPLICATION_XML)
                .get(List.class);
    }
}
