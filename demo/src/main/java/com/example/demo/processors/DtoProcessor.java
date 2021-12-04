package com.example.demo.processors;

import com.example.demo.db.models.Order;
import com.example.demo.db.models.Restaurant;

import java.util.List;

public interface DtoProcessor {
    public Restaurant processDTOtoEntity();
}
