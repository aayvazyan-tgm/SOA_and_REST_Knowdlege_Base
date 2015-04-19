package soa;

import javax.xml.ws.Endpoint;

/**
 * Exposes the service
 */
public class SOAPublisher {
    public static void publish() {
        String publishToURL = "http://localhost:8082/soa/SOASearcher";

        Endpoint.publish(publishToURL, new SOASearcher());
        System.out.println("Published SOA at "+publishToURL);
    }
}