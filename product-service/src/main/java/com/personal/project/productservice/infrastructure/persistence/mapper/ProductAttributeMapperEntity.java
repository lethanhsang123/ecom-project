package com.personal.project.productservice.infrastructure.persistence.mapper;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.productservice.domain.ProductAttribute;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductAttributeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapperEntity extends EntityMapper<ProductAttribute, ProductAttributeEntity> {
}
