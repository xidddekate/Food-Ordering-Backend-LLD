package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class OrderDTO {
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("meals")
    private List<String> meals;
    @JsonProperty("distance")
    private double distance;
}
