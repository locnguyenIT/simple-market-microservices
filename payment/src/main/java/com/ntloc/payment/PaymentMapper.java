package com.ntloc.payment;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentEntity toEntity(PaymentDTO dto);

    List<PaymentEntity> toListEntity(List<PaymentDTO> listDTO);

    PaymentDTO toDTO(PaymentEntity entity);

    List<PaymentDTO> toListDTO(List<PaymentEntity> listEntity);
}
