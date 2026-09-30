package com.sandydev.foodcatalogue.dto;

import com.sandydev.foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {

     private List<FoodItem> foodItems;
     private Restaurant restaurant;
}
