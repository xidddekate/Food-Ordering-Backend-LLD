package com.example.demo.controllers;

import com.example.demo.dto.OrderDTO;
import com.example.demo.service.DeliveryTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    DeliveryTimeService deliveryTimeService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<String> returnOrderTimings(@RequestBody List<OrderDTO> ordersDTO){
        return new ResponseEntity<>(this.deliveryTimeService.getDeliveryTimeForAllOrders(ordersDTO), HttpStatus.OK);
    }
}
