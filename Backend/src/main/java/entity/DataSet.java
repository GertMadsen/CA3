/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Gruppe 3 - Gert, Lene & Mikkel
 */
public class DataSet {
    
    private Car car;
    private Booking booking;
    private Customer customer;

    public DataSet(Car car, Booking booking, Customer customer) {
        this.car = car;
        this.booking = booking;
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public Booking getBooking() {
        return booking;
    }

    public Customer getCustomer() {
        return customer;
    }
    
}
