package servlet;

import model.Eintrag;
import rest.RestClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Servlet finds Primes using a Finder in a thread and generates the html code
 * Author: Ari Michael Ayvazyan
 * Date: 14.03.14
 */
public class Servlet extends HttpServlet {
    private Date startDate;
    private RestClient restClient;
    /**
     * @see javax.servlet.http.HttpServlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.sendRedirect("/search");
        /*response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("Do a Get request!");*/
        doGet(request, response);
    }

    /**
     * @see javax.servlet.http.HttpServlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.sendRedirect("/primes/searcher");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //This line is Important to generate a Valid HTML Form
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \n" +
                "  \"http://www.w3.org/TR/html4/loose.dtd\">");
        out.println("<html><head><title>Client</title></head><body>");
        //this is where the fun part starts
        //The title
        out.println("<div align=\"center\"><h1>Standalone REST and SOA Service</h1>");
        //The upper border
        out.println("<hr style=\"color:blue; background-color:blue; height:15px; width:80%;\">");
        ////////////////
        //ACTUAL CONTENT
        ////////////////
        //Form
        out.println("<form action=\".\">");
        out.println("<p>Action:<br><input name=\"action\" type=\"text\" size=\"30\" maxlength=\"30\"></p>");
        out.println("<p>Parameter:<br><input name=\"param\" type=\"text\" size=\"30\" maxlength=\"40\"></p>");
        out.println("<input type=\"submit\" value=\" Ausführen \">");
        out.println("</form>");

        //Results
        String reqAction= request.getParameter("action");
        String reqParam = request.getParameter("parameter");
        if (reqParam== null || reqAction== null){

        }else {
            out.println(getEintraegeAsHtml(request.getParameter("action"), request.getParameter("param")));
        }

        //The start date
        out.println("Started at " + this.startDate.toString() + "<br/>");
        //out.println("Found: "+this.finder.getCounter()+" Primes <br>");
        //Another Border
        out.println("<hr style=\"color:blue; background-color:blue; height:15px; width:80%;\">");
        //Closing open Tags and the center div
        out.println("</div></body></html>");
    }

    private String getEintraegeAsHtml(String action, String parameter) {
        String resultHtml = "";

        if (action.equalsIgnoreCase("delete")) {
            resultHtml += "//DELETE " + parameter;
        }
        if (action.equalsIgnoreCase("update")) {
            resultHtml += "//UPDATE " + parameter;
        }
        if (action.equalsIgnoreCase("search")) {
            List<Eintrag> eintragList = restClient.getByTag(parameter);
            resultHtml += "//SEARCH BY TAG " + parameter;
        }
        if (action.equalsIgnoreCase("insert")) {
            resultHtml += "//INSERT " + parameter;
        }
        if (action.equalsIgnoreCase("search")) {
            resultHtml += "//SOA Search" + parameter;
        }


        return resultHtml;
    }

    /**
     * This Function gets called when this servlet gets initialized.
     * Using Tomcat it gets called at the first request Sent to it.
     *
     * @see javax.servlet.http.HttpServlet
     */
    public void init() throws ServletException {
        this.startDate = new Date(System.currentTimeMillis());
        this.restClient=new RestClient();
        super.init();
    }

    /**
     * @see javax.servlet.http.HttpServlet
     */
    @Override
    public void destroy() {
        super.destroy();
    }
}
