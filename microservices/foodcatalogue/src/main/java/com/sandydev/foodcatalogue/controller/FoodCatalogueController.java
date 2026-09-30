package com.sandydev.foodcatalogue.controller;


import com.sandydev.foodcatalogue.dto.FoodCataloguePage;
import com.sandydev.foodcatalogue.dto.FoodItemDTO;
import com.sandydev.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin(origins = "*")
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {

        FoodItemDTO saveFoodItem = foodCatalogueService.saveFoodItem(foodItemDTO);

        return new ResponseEntity<>(saveFoodItem, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemById/{id}")
    public ResponseEntity<FoodCataloguePage>   fetchRestaurantDetailsWithFoodItems(@PathVariable Integer id){

        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(id);

        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
