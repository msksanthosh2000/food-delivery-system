package com.sandydev.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    private Integer id;
    private String itemName;
    private String itemDescription;
    private boolean veg;
    private Number price;
    private Integer restaurantId;

    private Integer quantity;
}
