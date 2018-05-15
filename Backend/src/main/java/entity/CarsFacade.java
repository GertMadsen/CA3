/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author lene_
 */
public class CarsFacade {
    public Cars mergeCars(Cars cars1, Cars cars2){
        cars1.merge(cars2);
        return cars1;
    }
}
