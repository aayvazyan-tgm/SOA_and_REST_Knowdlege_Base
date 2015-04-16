package soa;

import javax.xml.ws.Endpoint;

/**
 * Exposes the service
 */
public class SOAPublisher {
    public static void publish() {
        Endpoint.publish("http://localhost:8082/soa/SOASearcher", new SOASearcher());
        System.out.println("Published");
    }

}