package clients;

import soa.Searchable;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SOAClient {
    private Searchable searcher;

    public SOAClient(String host, int port, String path) {
        // URL to the wsdl File
        URL url = null;
        try {
            // url = new URL("http://localhost:9999/soa/hello?wsdl");
            url = new URL("http://" + host + ":" + port + "" + path + "?wsdl");
        } catch (MalformedURLException e) {
        }

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soa/", "SOA");

        // Get Service
        Service service = Service.create(url, qname);

        // Get Searchable Object
        searcher = service.getPort(Searchable.class);

        this.search();
    }

    /**
     * Loop the client for requests
     */
    public void search() {
        // read line
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        System.out.println("To end the Client, write stop or end...\n");
        System.out.println("Please put in the search question... \n  ");

        //
        while (!line.equalsIgnoreCase("stop") || !line.equalsIgnoreCase("end")) {
            try {
                line = buffer.readLine();
            } catch (IOException e) {
            }
            if (!line.equalsIgnoreCase("")) {
                System.out.println("\n" + searcher.search(line));
                System.out.println("Please put in a new search question... \n  ");

            }
        }
    }


}
