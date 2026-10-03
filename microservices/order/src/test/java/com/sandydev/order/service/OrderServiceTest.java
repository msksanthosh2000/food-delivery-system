package com.sandydev.order.service;

import com.sandydev.order.dto.OrderDTO;
import com.sandydev.order.dto.OrderDTOFromFE;
import com.sandydev.order.dto.UserDTO;
import com.sandydev.order.entity.Order;
import com.sandydev.order.mapper.OrderMapper;
import com.sandydev.order.repo.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrderInDb_shouldSaveOrderAndReturnOrderDTO() {
        // Arrange
        OrderDTOFromFE orderDetails = new OrderDTOFromFE();
        String newOrderId = "1";
        UserDTO userDTO = new UserDTO();
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemList(), orderDetails.getRestaurantDTO(), userDTO);
        OrderDTO orderDTOExpected = OrderMapper.INSTANCE.mapOrderTOOrderDTO(orderToBeSaved);

        when(restTemplate.getForObject(anyString(), eq(UserDTO.class))).thenReturn(userDTO);
        when(orderRepo.save(orderToBeSaved)).thenReturn(orderToBeSaved);

        // Act
        OrderDTO orderDTOActual = orderService.saveOrder(orderDetails);

        // Assert
        assertDoesNotThrow(() -> orderService.saveOrder(orderDetails));
    }
}
