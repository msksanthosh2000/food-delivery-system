package com.sandydev.restaurantlisting.service;

import com.sandydev.restaurantlisting.dto.RestaurantDTO;
import com.sandydev.restaurantlisting.entity.Restaurant;
import com.sandydev.restaurantlisting.mapper.RestaurantMapper;
import com.sandydev.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> fetchAllRestaurant() {

        List<Restaurant> restaurantList = restaurantRepo.findAll();

        // map Restaurant to RestaurantDTO
        List<RestaurantDTO> restaurantDTOList = restaurantList.stream()
                .map(RestaurantMapper.INSTANCE::mapRestauranttoRestaurantDTO)
                .toList();

        return restaurantDTOList;
    }

    public RestaurantDTO saveRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant saved = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOtoRestaurant(restaurantDTO));

        return RestaurantMapper.INSTANCE.mapRestauranttoRestaurantDTO(saved);
    }

    public ResponseEntity<RestaurantDTO> fetchById(Integer id) {

        Optional<Restaurant> restaurantRepoById = restaurantRepo.findById(id);

        if(restaurantRepoById.isPresent()){
            RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.mapRestauranttoRestaurantDTO(restaurantRepoById.get());
            return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
    }
}
