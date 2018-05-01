/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author lene_
 */
public class Car {
    private String logo;
    private String company;
    private String category;
    private String picture;
    private String make;
    private String model;
    private int year;
    private String regno;
    private int seats;
    private int doors;
    private String gear;
    private boolean aircondition;
    private String location;
    private int pricePerDay;
    private ArrayList<Reservation> reservations;

    public Car(String logo, String company, String category, String picture, String make, String model, int year, String regno, int seats, int doors, String gear, boolean aircondition, String location, int pricePerDay) {
        this.logo = logo;
        this.company = company;
        this.category = category;
        this.picture = picture;
        this.make = make;
        this.model = model;
        this.year = year;
        this.regno = regno;
        this.seats = seats;
        this.doors = doors;
        this.gear = gear;
        this.aircondition = aircondition;
        this.location = location;
        this.pricePerDay = pricePerDay;
        this.reservations = new ArrayList<>();
    }

    public String getLogo() {
        return logo;
    }

    public String getCompany() {
        return company;
    }

    public String getCategory() {
        return category;
    }

    public String getPicture() {
        return picture;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getRegno() {
        return regno;
    }

    public int getSeats() {
        return seats;
    }

    public int getDoors() {
        return doors;
    }

    public String getGear() {
        return gear;
    }

    public boolean isAircondition() {
        return aircondition;
    }

    public String getLocation() {
        return location;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public void setAircondition(boolean aircondition) {
        this.aircondition = aircondition;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
    
    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }    
}
