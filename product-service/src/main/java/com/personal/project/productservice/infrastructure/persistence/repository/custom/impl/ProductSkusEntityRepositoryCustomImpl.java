package com.personal.project.productservice.infrastructure.persistence.repository.custom.impl;

import com.personal.project.productservice.infrastructure.persistence.entity.ProductSkusEntity;
import com.personal.project.productservice.infrastructure.persistence.repository.custom.ProductSkusEntityRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class ProductSkusEntityRepositoryCustomImpl implements ProductSkusEntityRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductSkusEntityRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ProductSkusEntity> getByProductId(Long id) {
        Query queryEx = entityManager.createQuery(" SELECT P FROM product_skus P WHERE P.productId = :pid", ProductSkusEntity.class);
        queryEx.setParameter("pid", id);
        return queryEx.getResultList();

    }

}
