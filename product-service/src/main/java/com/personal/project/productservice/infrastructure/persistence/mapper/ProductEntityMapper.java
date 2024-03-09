package com.personal.project.productservice.infrastructure.persistence.mapper;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.productservice.domain.Product;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper extends EntityMapper<Product, ProductEntity> {
}
