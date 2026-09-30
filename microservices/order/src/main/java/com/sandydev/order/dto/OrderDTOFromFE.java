package com.sandydev.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOFromFE {
    private List<FoodItem> foodItemList;
    private RestaurantDTO restaurantDTO;
    private Integer userId;

}
