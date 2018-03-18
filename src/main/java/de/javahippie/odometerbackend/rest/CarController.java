/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javahippie.odometerbackend.rest;

import de.javahippie.odometerbackend.model.Car;
import de.javahippie.odometerbackend.web3.Web3Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tim Zöller
 */
@RestController
public class CarController {
    
    private final Web3Listener listener;
    
    @Autowired
    public CarController(Web3Listener listener) {
        this.listener = listener;
    }
    
    @RequestMapping("/car")
    public Car fetchCar(@RequestParam("vin") String vin) throws Exception{
        return listener.getCarByVin(vin);
    }
    
}
