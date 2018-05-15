/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lene_
 */
public class CarsFacadeTest {
    
    public CarsFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of mergeCars method, of class CarsFacade.
     */
    @Test
    public void testMergeCars() {
        System.out.println("mergeCars");
        Car car1 = new Car("", "", "mini", "", "vw", "up", 2010, "", 4, 3, "manual", false, "Odense", 50);
        Car car2 = new Car("", "", "mini", "", "vw", "polo", 2011, "", 4, 3, "manual", false, "Odense", 50);
                
        Cars cars1 = new Cars();
        Cars cars2 = new Cars();
        Cars cars3 = new Cars();
        cars1.add(car1);
        cars2.add(car2);
        cars3.add(car1);
        cars3.add(car2);
        
        CarsFacade instance = new CarsFacade();
        Cars expResult = cars3;
        Cars result = instance.mergeCars(cars1, cars2);
        assertEquals(expResult, result);
    }
    
}
