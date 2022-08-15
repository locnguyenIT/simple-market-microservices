package com.ntloc.orders;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    OrdersEntity toEntity(OrdersDTO dto);

    List<OrdersEntity> toListEntity(List<OrdersDTO> listDTO);

    OrdersDTO toDTO(OrdersEntity entity);

    List<OrdersDTO> toListDTO(List<OrdersEntity> listEntity);
}
