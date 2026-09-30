package com.sandydev.order.entity;

import com.sandydev.order.dto.FoodItem;
import com.sandydev.order.dto.RestaurantDTO;
import com.sandydev.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Order")
public class Order {

    private String orderId;
    private List<FoodItem> foodItemList;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}
