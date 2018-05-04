/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Booking;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author lene_
 */
public class SetUpTestBookings {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
        em.getTransaction().begin();
        User user1 = new User("test@gmail.com", "Alice", "Hansen", 25);
        Booking booking1 = new Booking("LL12345", "04/05/2018", "07/05/2018");
        user1.addBooking(booking1);
        User user2 = new User("another@gmail.com", "Bob", "Jensen", 42);
        Booking booking2 = new Booking("AB23999", "05/05/2018", "06/05/2018");
        Booking booking3 = new Booking("AB23999", "10/05/2018", "15/05/2018");
        user2.addBooking(booking2);
        user1.addBooking(booking3);
        em.persist(booking1);
        em.persist(booking2);
        em.persist(booking3);
        em.persist(user1);
        em.persist(user2);
        em.getTransaction().commit();
        
        System.out.println("Testusers and bookings created");
                
        
        
    }
}
