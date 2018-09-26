/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javahippie.odometerbackend.rest;

import de.javahippie.odometerbackend.model.Car;
import de.javahippie.odometerbackend.web3.Odometer;
import de.javahippie.odometerbackend.web3.Web3Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Tim Zöller
 */
@RestController
public class CarController {

    private final Web3Listener listener;
    private final Odometer odometer;
    private final Map<String, TransactionReceipt> transactions;

    @Autowired
    public CarController(Web3Listener listener, Odometer odometer) {
        this.listener = listener;
        this.odometer = odometer;
        this.transactions = new HashMap<>();
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public ResponseEntity<Car> fetchCar(@RequestParam("vin") String vin) throws Exception {
        return ResponseEntity.ok(listener.getCarByVin(vin));
    }

    @RequestMapping(value = "/tx", method = RequestMethod.GET)
    public ResponseEntity<TransactionReceipt> getTransactionReceipt(@RequestParam("uuid") String uuid) {
        if(transactions.containsKey(uuid)) {
            return ResponseEntity.ok(transactions.get(uuid));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<String> createCar(@RequestParam("vin") String vin) throws Exception {
        String uuid = generateUUID();
        odometer.createCar(vin)
                .sendAsync()
                .whenComplete((transactionReceipt, throwable) -> transactions.put(uuid, transactionReceipt));
        return ResponseEntity.accepted().body(uuid);
    }

    @RequestMapping(value = "/car/mileage", method = RequestMethod.POST)
    public ResponseEntity<String> updateKilometers(@RequestParam("vin") String vin, @RequestParam("km") Integer km) {
        String uuid = generateUUID();
        odometer.updateKilometers(vin, BigInteger.valueOf(km))
                .sendAsync()
                .whenComplete((transactionReceipt, throwable) -> transactions.put(uuid, transactionReceipt));
        return ResponseEntity.accepted().body(uuid);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
