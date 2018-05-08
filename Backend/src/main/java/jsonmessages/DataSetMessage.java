/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmessages;

import entity.*;

/**
 *
 * @author Gert Lehmann Madsen
 */
public class DataSetMessage implements JSONMessage<DataSet> {
    
    public Car car;
    public Booking booking;
    public Customer customer;

    @Override
    public DataSet toInternal() {
       return new DataSet(car, booking, customer);     
    }
    
}
