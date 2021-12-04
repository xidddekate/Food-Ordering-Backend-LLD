package com.example.demo.processors;

import com.example.demo.db.dao.RestaurantDao;
import com.example.demo.db.models.*;
import com.example.demo.db.models.pairs.MealCookingSlotsTimePair;
import com.example.demo.dto.OrderDTO;
import com.example.demo.exceptions.NullMealsException;
import com.example.demo.exceptions.NullOrderIdException;
import com.example.demo.service.DeliveryBoyService;
import com.example.demo.service.MealService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class OrderDtoProcessor implements DtoProcessor {
    private List<OrderDTO>OrderDtos;
    public Restaurant processDTOtoEntity(){
        List<Order> orders = new ArrayList<>();
        HashMap<String, MealCookingSlotsTimePair> mealSlots = new MealService().getMealCookingSlots();
        for(OrderDTO orderDTO : this.OrderDtos) {
            if (orderDTO.getOrderId() == null) {
                throw new NullOrderIdException();
            } else if (orderDTO.getMeals() == null) {
                throw new NullMealsException();
            }
            orders.add(
                    new Order(
                            orderDTO.getOrderId(),
                            orderDTO.getMeals().stream().map((mealName) ->
                                    new Meal(
                                            mealName,
                                            mealSlots.get(mealName).mealCookingSlots,
                                            mealSlots.get(mealName).mealCookingTimeInMinutes
                                    )
                            ).collect(Collectors.toList()),
                            new CustomerLocation(orderDTO.getDistance()),
                            new DeliveryBoyService().getDeliveryBoy()
                    )
            );
        }
        return new RestaurantDao().getRestaurant(orders);
    }
}
