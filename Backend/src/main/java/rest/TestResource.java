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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author lene_
 */
@Path("test")
public class TestResource {

    private Car c1 = new Car("https://www.ramsbone.dk/backend/CA3/schwertz-logo.jpg ", "Schwertz", "Mini", "https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/volkswagen-up.jpg", "VW", "Up", 2016, "LL12345", 5, 3, "manuel", true, "Cph Airport", 50);
    private Car c2 = new Car("https://www.ramsbone.dk/backend/CA3/schwertz-logo.jpg ", "Schwertz", "Economy", "https://s.aolcdn.com/commerce/autodata/images/USC70FOC221A021001.jpg", "Ford", "Fiesta", 2016, "AB89764", 5, 4, "automatic", true, "Cph Airport", 100);
    private Car c3 = new Car("https://www.ramsbone.dk/backend/CA3/schwertz-logo.jpg ", "Schwertz", "Economy", "https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/images/car-reviews/first-drives/legacy/peugeot_308_testdrive_012.jpg", "Peugeot", "308", 2017, "YC23456", 5, 5, "manuel", true, "Aarhus City", 100);
    private Car c4 = new Car("https://www.ramsbone.dk/backend/CA3/schwertz-logo.jpg ", "Schwertz", "Fullsize", "https://s-media-cache-ak0.pinimg.com/originals/03/e5/1a/03e51a1e6d49409afb170adcf1dd99ef.jpg", "Toyota", "Avensis stc", 2018, "AB23999", 5, 5, "automatic", true, "Aarhus City", 200);
    private Car c5 = new Car("https://www.ramsbone.dk/backend/CA3/schwertz-logo.jpg ", "Schwertz", "Fullsize", "https://i.ytimg.com/vi/1SxQj74M7n4/maxresdefault.jpg", "Citroen", "Berlingo", 2016, "AC12345", 7, 5, "manuel", true, "Naestved", 200);
    private Reservation r1 = new Reservation("SC", "test@testersen.dk", "01/05/2018", "04/05/2018");

    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{regno}")
    public String getCarByRegNo(@PathParam("regno") String regno){
        Car car;
        switch(regno){
            case "LL12345":
                car = c1;
                break;
            case "AB89764":
                car = c2;
                break;
            case "YC23456":
                car = c3;
                break;
            case "AB23999":
                car = c4;
                break;
            case "AC12345":
                car = c5;
                break;
            default:
                car = null;
        }
        return gson.toJson(car);
    }
    
    /**
     * Retrieves representation of an instance of rest.TestResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCars(@QueryParam("location") String location, @QueryParam("category") String category) {
        Cars cars = new Cars();
        if (location == null && category == null) {
            cars.add(c1);
            cars.add(c2);
            cars.add(c3);
            cars.add(c4);
            cars.add(c5);

            return gson.toJson(cars);
        }
        if (location != null) {
            switch (location) {
                case "Cph Airport":
                    cars.add(c1);
                    cars.add(c2);
                    break;
                case "Aarhus City":
                    cars.add(c3);
                    cars.add(c4);
                    break;
                case "Naestved":
                    cars.add(c5);
                    break;
            }
        }
        if(category != null){
            switch(category){
                case "Mini":
                    cars.add(c1);
                    break;
                case "Economy":
                    cars.add(c2);
                    cars.add(c3);
                    break;
                case "Fullsize":
                    cars.add(c4);
                    cars.add(c5);
            }
        }
            return gson.toJson(cars);
    }
}
