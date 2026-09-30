package com.sandydev.order.mapper;

import com.sandydev.order.dto.OrderDTO;
import com.sandydev.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO mapOrderTOOrderDTO(Order order);

    Order mapOrderDTOToOrder(OrderDTO orderDTO);
}
