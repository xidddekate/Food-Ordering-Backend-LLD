# Food-Ordering-Backend-LLD
Low level backend design of Food Ordering app 

## First part<br>
### Rules followed : 
- If an order is going to take more than 2 hour 30 minutes to get delivered, it gets denied.
- Every order has a 2 digit unique id.
- You cannot change the order of entries in the queue.
- If any order in itself cannot be prepared at once it will be denied. (Look at order 14 in the sample output)
- A new order can start only if it has the required free slots available to accommodate it.
- If a new order comes and does not have enough free slots, it has to wait till any of the previous order gets completed. An order can only be marked as completed if it gets delivered and the next order can start cooking only then.
- No orders can be cooked in parts.
- All orders come in at the same time.<br>


### Please refer screenshots for better understanding <br>
![alt text](https://github.com/xidddekate/Food-Ordering-Backend-LLD/blob/main/Screenshot%202021-12-04%20at%2011.36.21%20AM.png)

## Second part - Design


Following tables we could have for extension of this problem using any RDBMS databases<br>
<li>Meals</li>
<li>Delivery Boy</li>
<li>Restaurant</li>
<li>Customer</li>
<li>Order</li>
<li>Payment</li>
<li> CustomerOrder - To maintain mapping between customers and their favorite orders</li>
<li> OrderMeals - To maintain mapping between meals and order</li>
<li> RestaurantMeals - To maintain mapping between meals and Restaurant</li><br>

### Please refer screenshots for better understanding<br>
![alt text](https://github.com/xidddekate/Food-Ordering-Backend-LLD/blob/main/Screenshot%202021-12-05%20at%2010.34.33%20PM.png)
### CRUD FLOW

Lets start from beginning
<li> Customer comes and registers himself so entry is created in customer table</li>
<li> Now customer wants to order the food. So he can either visit restaurant pages which would be rendered on frontend on the basis of restaurant table or can search via food name or restaurant name.
Indexing concept could be used here for faster search. Primary indexing can be done on MealName attribute in RestaurantMeals table when searched via food name. 
Secondary indexes could be maintained on RestaurantName attribute in RestaurantMeals table when searched via restaurant. </li>
<li> Now, on the frontend results could be displayed and customer could be accordingly taken to the restaurant page (as restaurant ID is maintained in RestaurantMeals table) which was obtained through searching in previous step.</li>
<li> Now customer could select his favorite meals and their quantity which would be rendered on the basis Meals table.</li>
<li> Once customer is ready to order all his meals, delivery guy could be allocated to this order using some algorithm for location for that particular restaurant from where order is placed. So the entry would be created for this order in Orders table, OrderMeals table (OrderMeals table represents meals that a order has) and CustomerOrder table.</li>
<li>Once delivery boy is allocated, relation between customer and his orders can be shown to him from CustomerOrder table using OrderMeals table for that particular delivery boy.</li>
<li> After successful delivery, ratings for this particular order, customer, restaurant, meal and deliveryBoy could be updated using Order, OrderMeals, CustomerOrder tables </li>

### Extension
<li>For Custom meal, a boolean attribute is maintained in Restaurant table called CustomMealSupported which tells if that is supported. If that is supported customer could write the recipe and give it to restaurant. This new meal could be added to CustomerMealRestaurantCustom Table and Meals table which is controlled by isPrivate attribute in Meals. </li>
<li> For recurring orders, an attribute called isRecurring is maintained in Order Table which tells if that is recurring and recurDays could be maintained for which advanced payments can be taken. Also for prior ordering isScheduled attribute is maintained. So now for handling such case, events from SQL can be used wherein recurring and one time event (scheduled orders) could be handled. The events can be used to insert row into orders table and now this newly inserted row could be handled by application to process it.  </li>
<li> Another extension for this problem could be the offers table wherein it could take FK from restaurant table or Meals table and render it on the frontend and then process accordingly rest of the process.</li>
