/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Booking;
import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author lene_
 */
public class SetupTestBookings {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
        em.getTransaction().begin();
        Customer cus1 = new Customer("test@gmail.com", "Alice", "Hansen");
        Booking booking1 = new Booking("LL12345", "04/05/2018", "07/05/2018","Carmondo");
        cus1.setBooking(booking1);
        Customer cus2 = new Customer("another@gmail.com", "Bob", "Jensen");
        Booking booking2 = new Booking("AB23999", "05/05/2018", "06/05/2018","Carmondo");
        cus2.setBooking(booking2);
        em.persist(booking1);
        em.persist(booking2);
        em.persist(cus1);
        em.persist(cus2);
        em.getTransaction().commit();
        
        System.out.println("Testusers and bookings created");
                
        
        
    }
}
