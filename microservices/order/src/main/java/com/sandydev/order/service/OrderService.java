package com.sandydev.order.service;

import com.sandydev.order.dto.OrderDTO;
import com.sandydev.order.dto.OrderDTOFromFE;
import com.sandydev.order.dto.UserDTO;
import com.sandydev.order.entity.Order;
import com.sandydev.order.mapper.OrderMapper;
import com.sandydev.order.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrder(OrderDTOFromFE orderDTOFromFE) {

//        int orderId = sequenceService.generateNextOrderId();
        String orderId = UUID.randomUUID().toString();

        Integer userId = orderDTOFromFE.getUserId();
        UserDTO userDetails = getUserDetails(userId);

        Order order = new Order(orderId, orderDTOFromFE.getFoodItemList(),
                orderDTOFromFE.getRestaurantDTO(), userDetails);
        Order savedOrder = orderRepository.save(order);

        return OrderMapper.INSTANCE.mapOrderTOOrderDTO(savedOrder);
    }

    private UserDTO getUserDetails(Integer userId) {
        UserDTO userDTO = restTemplate
                .getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
        return userDTO;
    }
}
