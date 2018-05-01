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
public class Cars {
    private ArrayList<Car> cars;

    public Cars() {
        this.cars = new ArrayList<>();
    }
    
    public void add(Car car){
        this.cars.add(car);
    }
    
    public void merge(Cars carList){
        this.cars.addAll(carList.getCars());
    }

    public ArrayList<Car> getCars() {
        return cars;
    }   
}
