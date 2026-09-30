package com.sandydev.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private String orderId;
    private List<FoodItem> foodItemList;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}
