package com.example.demo.processors;

import com.example.demo.db.models.Restaurant;
import com.example.demo.db.models.pairs.OrderTimeTakenPair;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DefaultStrategyOutputProcessor implements StrategyOutputProcessor{
    public List<OrderTimeTakenPair> orderTimeTakenPairs;
    public String process(Restaurant restaurant){
        StringBuilder res = new StringBuilder();
        for(OrderTimeTakenPair orderTimeTakenPair : this.orderTimeTakenPairs){
            if (orderTimeTakenPair.timeTaken == -1) {
                res.append("Order ").append(orderTimeTakenPair.orderId).append(" is denied because the restaurant cannot accommodate it\n");
            }
            else if(orderTimeTakenPair.timeTaken > restaurant.getMaxDeliveryTimeInMinutes()){
                res.append("Order ").append(orderTimeTakenPair.orderId).append(" is denied because it is going to take more than 2 hour 30 minutes to get delivered\n");
            }
            else {
                res.append("Order ").append(orderTimeTakenPair.orderId).append(" will get delivered in ").append(orderTimeTakenPair.timeTaken).append(" minutes\n");
            }
        }
        return res.toString();
    }
}
