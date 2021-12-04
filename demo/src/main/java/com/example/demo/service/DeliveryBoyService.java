package com.example.demo.service;

import com.example.demo.db.dao.DeliveryBoyDao;
import com.example.demo.db.models.DeliveryBoy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeliveryBoyService {
    public DeliveryBoy getDeliveryBoy(){
        return new DeliveryBoyDao().getDeliveryBoy();
    }
}
