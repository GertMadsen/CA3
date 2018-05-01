/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author lene_
 */
@Path("test")
public class TestResource {
    
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     * Retrieves representation of an instance of rest.TestResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getAllCars() {
        Car c1 = new Car("logo.jpg", "somecompany", "mini", "pic.jpg", "vw", "up", 2016, "LL12345", 5, 3, "manuel", true, "Cph Airport", 50);
        Car c2 = new Car("logo.jpg", "somecompany", "business", "pic.jpg", "Ford", "Fiesta", 2016, "AB89764", 5, 4, "automatic", true, "Cph Airport", 100);
        Reservation r1 = new Reservation("somecompany", "test@testersen.dk", "01/05/2018", "04/05/2018");
        c1.addReservation(r1);
        
        Cars cars = new Cars();
        cars.add(c1);
        cars.add(c2);
        
        return gson.toJson(cars);
    }

  
}
