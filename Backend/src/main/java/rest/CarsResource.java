/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.BookingFacade;
import entity.DataSet;
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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import jsonmessages.DataSetMessage;
import jsonmessages.MessageFacade;

/**
 * REST Web Service
 *
 * @author lene_
 */
@Path("cars")
public class CarsResource {
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarsResource
     */
    public CarsResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CarsResource
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
        if(scan.hasNext()){
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCars(@QueryParam("location") String location, @QueryParam("category") String category,
            @QueryParam("start") String fromDate, @QueryParam("end") String toDate) throws MalformedURLException, ProtocolException, IOException{
        String urlStr = "http://www.ramsbone.dk:8081/api/cars";
        
        if (location != null || category != null || fromDate != null || toDate != null) {
            urlStr += "?";
        }
        if(fromDate != null && toDate != null){
            fromDate = fromDate.replace("-", "/");
            toDate = toDate.replace("-", "/");
            urlStr += "start=" + fromDate + "&end=" + toDate;
            if(location != null){
               location = location.replace(" ", "%20");
                urlStr += "&location=" + location;
            }
            if(category != null){
                category = category.replace(" ", "%20");
                urlStr += "&category=" + category;
            }
        }else{
            if(location != null){
                location = location.replace(" ", "%20");
                urlStr += "location=" + location;
            }
            if(category != null){
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
        if(scan.hasNext()){
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

    /**
     * PUT method for updating or creating an instance of CarsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
     @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String testDataSet(String json) {
        
        //TODO kalde api-put med bilens regno.
        
        //Henter DataSet-Objekt
        DataSet ds = MessageFacade.fromJson(json, DataSetMessage.class);
        BookingFacade bf = new BookingFacade();
        //Lægger bookingen og kunden ned i databasen 
        bf.createBooking(ds.getBooking(), ds.getCustomer());
        //returnerer bil objekt som json.
        return gson.toJson(ds.getCar());
    }
}
