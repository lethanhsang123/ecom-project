package com.personal.project.productservice.infrastructure.persistence.repository.custom;

import com.personal.project.productservice.domain.query.ProductSearchQuery;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductEntityRepositoryCustom {

    List<ProductEntity> search(ProductSearchQuery query);

    ProductEntity findByProductSkusId(Long id);

}
