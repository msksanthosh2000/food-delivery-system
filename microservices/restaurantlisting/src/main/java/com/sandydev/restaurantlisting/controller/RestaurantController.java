package com.sandydev.restaurantlisting.controller;

import com.sandydev.restaurantlisting.dto.RestaurantDTO;
import com.sandydev.restaurantlisting.entity.Restaurant;
import com.sandydev.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurant(){
        List<RestaurantDTO> allRestaurant = restaurantService.fetchAllRestaurant();
        return new ResponseEntity<>(allRestaurant, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO saveRestaurant = restaurantService.saveRestaurant(restaurantDTO);
        return new ResponseEntity<>(saveRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchById(@PathVariable Integer id){
        return restaurantService.fetchById(id);
    }
}
