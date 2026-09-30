package com.sandydev.foodcatalogue.service;

import com.sandydev.foodcatalogue.dto.FoodCataloguePage;
import com.sandydev.foodcatalogue.dto.FoodItemDTO;
import com.sandydev.foodcatalogue.dto.Restaurant;
import com.sandydev.foodcatalogue.entity.FoodItem;
import com.sandydev.foodcatalogue.mapper.FoodItemMapper;
import com.sandydev.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO saveFoodItem(FoodItemDTO foodItemDTO) {
        // convert foodItemDTO ->  FoodItem
        FoodItem foodItem = FoodItemMapper.INSTANCE.MapFoodItemDTOtoFoodItem(foodItemDTO);

        FoodItem saved = foodItemRepo.save(foodItem);
        //convert foodItem ->  foodItemDTO

        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(saved);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer id) {
        List<FoodItem> foodItems = fetchFoodItemList(id);
        Restaurant restaurant = fetchRestaurantDetailsByRestaurantMS(id);

        return createFoodCataloguePage(foodItems, restaurant);
    }

    private List<FoodItem> fetchFoodItemList(Integer id) {
        return foodItemRepo.findByRestaurantId(id);
    }

    private Restaurant fetchRestaurantDetailsByRestaurantMS(Integer id) {

        return restTemplate
                .getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/" + id, Restaurant.class);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItems, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItems(foodItems);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }
}
