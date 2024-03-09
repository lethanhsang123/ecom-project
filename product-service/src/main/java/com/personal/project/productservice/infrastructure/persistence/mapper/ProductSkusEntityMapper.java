package com.personal.project.productservice.infrastructure.persistence.mapper;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.productservice.domain.ProductSkus;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductSkusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductSkusEntityMapper extends EntityMapper<ProductSkus, ProductSkusEntity> {
}
