/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
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

/**
 * REST Web Service
 *
 * @author lene_
 */
@Path("cars")
public class CarsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarsResource
     */
    public CarsResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CarsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{regno}")
    public String getCar(@PathParam("regno") String regno) throws MalformedURLException, IOException {
        URL url;
        String jsonStr = null;
        url = new URL("http://www.ramsbone.dk:8081/api/cars/" + regno);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

    public String getDataMethod(String urlStr, String location, String category, String fromDate, String toDate) throws MalformedURLException, IOException {
        if (location != null || category != null || fromDate != null || toDate != null) {
            urlStr += "?";
        }
        
        if(fromDate != null && toDate != null){
            fromDate = fromDate.replace("-", "/");
            toDate = toDate.replace("-", "/");

            urlStr += "start=" + fromDate + "&end=" + toDate;
            if (location != null) {
                location = location.replace(" ", "%20");
                urlStr += "&location=" + location;
            }
            if (category != null) {
                category = category.replace(" ", "%20");
                urlStr += "&category=" + category;
            }
        } else {
            if (location != null) {
                location = location.replace(" ", "%20");
                urlStr += "location=" + location;
            }
            if (category != null) {
                category = category.replace(" ", "%20");
                urlStr += "category=" + category;
            }
        }
        URL url;
        String jsonStr = null;
        url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");

        Scanner scan = new Scanner(con.getInputStream());
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCars(@QueryParam("location") String location, @QueryParam("category") String category,
            @QueryParam("start") String fromDate, @QueryParam("end") String toDate) throws MalformedURLException, ProtocolException, IOException {
        String urlStr1 = "http://www.ramsbone.dk:8081/api/cars";
        String urlStr2 = "https://stanitech.dk/carrentalapi/api/cars";

        
        String jsonStr = getDataMethod(urlStr1, location, category, fromDate, toDate);
       // jsonStr += getDataMethod(urlStr2, location, category, fromDate, toDate);
        
    //TODO    
    //Kan stadig bruges, men husk at bruge gsonFromjson til at konvertere til cars-objecter merge dem og så gsontojson.

        return jsonStr;
    }

    /**
     * PUT method for updating or creating an instance of CarsResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
