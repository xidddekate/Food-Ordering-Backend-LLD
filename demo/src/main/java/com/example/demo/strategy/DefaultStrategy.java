package com.example.demo.strategy;

import com.example.demo.db.models.Meal;
import com.example.demo.db.models.Order;
import com.example.demo.db.models.Restaurant;
import com.example.demo.db.models.pairs.MealCookingSlotsTimePair;
import com.example.demo.db.models.pairs.OrderTimeTakenPair;
import com.example.demo.exceptions.NullCookingSlotsOrTimeException;
import com.example.demo.processors.DefaultStrategyOutputProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Component
public class DefaultStrategy implements StrategyInterface {

    static class MealCookingSlotsTimePairComparator implements Comparator<MealCookingSlotsTimePair> {
        public int compare(MealCookingSlotsTimePair s1, MealCookingSlotsTimePair s2) {
            if (s1.mealCookingTimeInMinutes > s2.mealCookingTimeInMinutes)
                return 1;
            else if (s1.mealCookingTimeInMinutes < s2.mealCookingTimeInMinutes)
                return -1;
            return 0;
        }
    }

    public String getOrderDeliverTime(Restaurant restaurant){
        PriorityQueue<MealCookingSlotsTimePair> slotsTimePairQueue = new PriorityQueue<>(5, new MealCookingSlotsTimePairComparator());
        List<OrderTimeTakenPair> orderTimeTakenPairs = new ArrayList<>();
        // iterate through all orders recieved
        for(Order order : restaurant.getOrders()){
            double maxOrderTime=0,timeTaken; int cookingSlotsRequired=0;
            // iterate through meals to find total cooking slots required and maximum time that order can take to get prepared
            for(Meal meal : order.getMeals()){
                maxOrderTime = Math.max(maxOrderTime,meal.getMealCookingTimeInMinutes());
                cookingSlotsRequired = cookingSlotsRequired + meal.getMealCookingSlots();
            }
            // order cannot be served if cookingSlotsRequired is more than a restaurant has
            if(cookingSlotsRequired > restaurant.getMaxCookingSlots()){
                timeTaken = -1;
            }
            // try to serve this order by completing the existing order in queue that takes minimum amount of time if currently no slots available
            else if(restaurant.getCurrentCookingSlots()+cookingSlotsRequired > restaurant.getMaxCookingSlots()){
                MealCookingSlotsTimePair mealCookingSlotsTimePair = new MealCookingSlotsTimePair(0,0);
                // iterate till slots required to serve this order is not free
                while (restaurant.getCurrentCookingSlots()+cookingSlotsRequired > restaurant.getMaxCookingSlots()){
                    mealCookingSlotsTimePair = slotsTimePairQueue.peek();
                    slotsTimePairQueue.poll();
                    if(mealCookingSlotsTimePair==null){
                        throw new NullCookingSlotsOrTimeException();
                    }
                    restaurant.setCurrentCookingSlots(restaurant.getMaxCookingSlots()-mealCookingSlotsTimePair.mealCookingSlots);
                }
                // calculate time required and append to queue
                timeTaken = order.getDeliveryBoy().getAverageTimeInMinutesToCoverOneKm() * order.getCustomerLocation().getDistance() + maxOrderTime + mealCookingSlotsTimePair.mealCookingTimeInMinutes;
                mealCookingSlotsTimePair.mealCookingTimeInMinutes = timeTaken;
                mealCookingSlotsTimePair.mealCookingSlots += cookingSlotsRequired;
                slotsTimePairQueue.add(mealCookingSlotsTimePair);
            }
            // simply prepare order if cooking slots are available
            else{
                // calculate time required and append to queue
                timeTaken = order.getDeliveryBoy().getAverageTimeInMinutesToCoverOneKm() * order.getCustomerLocation().getDistance() + maxOrderTime;
                slotsTimePairQueue.add(new MealCookingSlotsTimePair(cookingSlotsRequired,timeTaken));
                restaurant.setCurrentCookingSlots(restaurant.getCurrentCookingSlots()+cookingSlotsRequired);
            }
            orderTimeTakenPairs.add(new OrderTimeTakenPair(order.getOrderId(),timeTaken));
        }
        return new DefaultStrategyOutputProcessor(orderTimeTakenPairs).process(restaurant);
    }
}
