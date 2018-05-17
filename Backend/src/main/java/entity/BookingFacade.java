/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Gruppe 3 - Gert, Lene & Mikkel
 */
public class BookingFacade {
    private EntityManager em;

    public BookingFacade() {
        this.em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    }
    
    public void createBooking(Booking booking, Customer customer){
       em.getTransaction().begin();
       customer.setBooking(booking);
       em.persist(booking);
       em.persist(customer);
       em.getTransaction().commit();
    }
    
}
