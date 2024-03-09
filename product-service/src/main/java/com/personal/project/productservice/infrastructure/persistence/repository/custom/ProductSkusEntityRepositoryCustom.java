package com.personal.project.productservice.infrastructure.persistence.repository.custom;

import com.personal.project.productservice.infrastructure.persistence.entity.ProductSkusEntity;

import java.util.List;

public interface ProductSkusEntityRepositoryCustom {

    List<ProductSkusEntity> getByProductId(Long id);

}
