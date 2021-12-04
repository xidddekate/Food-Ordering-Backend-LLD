package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.processors.OrderDtoProcessor;
import com.example.demo.strategy.DefaultStrategy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class DeliveryTimeService {

    @Autowired
    DefaultStrategy defaultStrategy;

    public String getDeliveryTimeForAllOrders(List<OrderDTO> orderDTOS){
        OrderDtoProcessor orderDtoProcessor = new OrderDtoProcessor(orderDTOS);
        String response;
        try {
            response = this.defaultStrategy.getOrderDeliverTime(orderDtoProcessor.processDTOtoEntity());
        }catch (Exception e){
            response = "Exception Occured, please provide proper input";
        }
        return response;
    }
}
