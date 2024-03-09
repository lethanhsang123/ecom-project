package com.personal.project.productservice.infrastructure.persistence.mapper;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.productservice.domain.Discount;
import com.personal.project.productservice.infrastructure.persistence.entity.DiscountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountEntityMapperEntity extends EntityMapper<Discount, DiscountEntity> {
}
