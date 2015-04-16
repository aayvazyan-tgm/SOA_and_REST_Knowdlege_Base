import clients.SOAClient;
import server.JettyEmbeddedRunner;
import soa.SOAPublisher;

import java.io.IOException;

//SOA Source:
//http://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example/
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            new JettyEmbeddedRunner().startServer(8081);
            System.out.println("Started Servlet/s");
            SOAPublisher.publish();
        } else {
            new SOAClient("localhost", 8082, "SOA");
        }
    }
}