package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource {

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String user = securityContext.getUserPrincipal().getName();
        return "\"This message if from the server (requires the user role): Hello from USER: " + user + "\"";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String admin = securityContext.getUserPrincipal().getName();
        return "\"This message if from the server (requires the admin role):Hello from ADMIN: " + admin + "\"";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("people/{peopleid}")
    public String getFromAll(@PathParam("peopleid") Long id) throws MalformedURLException, IOException {
        URL url;
        String jsonStr = null;
            url = new URL("https://www.swapi.co/api/people/" + id + "/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            con.setRequestProperty("User-Agent", "CA3_server");
            Scanner scan = new Scanner(con.getInputStream());           
            if (scan.hasNext()) {
                jsonStr = scan.nextLine();
            }
            scan.close();
        return ""+jsonStr;
    }

}
