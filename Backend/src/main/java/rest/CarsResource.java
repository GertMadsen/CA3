/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Car;
import entity.Cars;
import entity.CarsFacade;
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
    private static Gson gson = new Gson();
    
    private String schwertzUrl = "http://www.ramsbone.dk:8081/api/cars";
    private String biglersUrl = "https://stanitech.dk/carrentalapi/api/cars";

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
        char regStart = regno.charAt(0);
        String companyUrl = "";
        
        switch(regStart){
            case 'B': 
                companyUrl = biglersUrl;
                break;
            case 'L': 
                companyUrl = schwertzUrl;
                break;
            default:
                companyUrl = null;
        }
        
        URL url;
        String jsonStr = null;
        url = new URL(companyUrl + "/" + regno);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        
        if(regStart == 'B') {
            Cars BiglerCars = gson.fromJson(jsonStr, Cars.class);
            Car BiglerCar = BiglerCars.getCars().get(0);
            jsonStr = gson.toJson(BiglerCar);           
        }
    
        return jsonStr;
    }

    public String getDataMethod(String urlStr, String location, String category, String fromDate, String toDate) throws MalformedURLException, IOException {
        String newUrl = urlStr;
        if (location != null || category != null || fromDate != null || toDate != null) {
            newUrl += "?";
        }
        
        if(fromDate != null && toDate != null){
            fromDate = fromDate.replace("-", "/");
            toDate = toDate.replace("-", "/");

            newUrl += "start=" + fromDate + "&end=" + toDate;
            if (location != null) {
                location = location.replace(" ", "%20");
                newUrl += "&location=" + location;
            }
            if (category != null) {
                category = category.replace(" ", "%20");
                newUrl += "&category=" + category;
            }
        } else {
            if (location != null) {
                location = location.replace(" ", "%20");
                newUrl += "location=" + location;
            }
            if (category != null) {
                category = category.replace(" ", "%20");
                newUrl += "category=" + category;
            }
        }
        URL url;
        String jsonStr = null;
        url = new URL(newUrl);
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
        

        String jsonSchwertz = getDataMethod(schwertzUrl, location, category, fromDate, toDate);
        String jsonBiglers = getDataMethod(biglersUrl, location, category, fromDate, toDate);
        
        CarsFacade cf = new CarsFacade();
        
        Cars schwertzList = gson.fromJson(jsonSchwertz, Cars.class);
        Cars biglerList = gson.fromJson(jsonBiglers, Cars.class);
        Cars merged = cf.mergeCars(schwertzList, biglerList);
        
        String resultStr =gson.toJson(merged);
        return resultStr;
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
