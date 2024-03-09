package com.personal.project.productservice.infrastructure.persistence.repository.custom.impl;

import com.personal.project.productservice.domain.query.ProductSearchQuery;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductEntity;
import com.personal.project.productservice.infrastructure.persistence.repository.custom.ProductEntityRepositoryCustom;
import com.personal.project.common.util.DataUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductEntityRepositoryCustomImpl implements ProductEntityRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductEntityRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ProductEntity> search(ProductSearchQuery query) {
        StringBuilder sql = new StringBuilder(" SELECT P FROM product P");
        Map<String, Object> params = new HashMap<>();
        sql.append(queryParamsBuilder(query, params));
        sql.append(sortParamsBuilder(query.getSortBy()));
        Query queryEx = entityManager.createQuery(sql.toString(), ProductEntity.class);
        params.forEach(queryEx::setParameter);
        queryEx.setFirstResult((query.getPageIndex() - 1) * query.getPageSize());
        queryEx.setMaxResults(query.getPageSize());
        return queryEx.getResultList();
    }

    @Override
    public ProductEntity findByProductSkusId(Long id) {
        StringBuilder sql = new StringBuilder(" SELECT P FROM product P JOIN product_skus S on P.id = S.product_id WHERE S.id = :id ");
        Query queryEx = entityManager.createQuery(sql.toString(), ProductEntity.class);
        queryEx.setParameter("id", id);
        return (ProductEntity) queryEx.getSingleResult();
    }

    private String queryParamsBuilder(ProductSearchQuery queryValue, Map<String, Object> params) {
        StringBuilder query = new StringBuilder(" WHERE 1 = 1 ");
        if (!DataUtils.isBlank(queryValue.getCode())) {
            query.append(" AND P.code = :code ");
            params.put("code", queryValue.getCode());
        }
        if (!DataUtils.isBlank(queryValue.getName())) {
            query.append(" AND P.name LIKE (%:name%) ");
            params.put("name", queryValue.getName());
        }
        if (!DataUtils.isBlank(queryValue.getStatus())
                && !DataUtils.isBlank(queryValue.getStatus().name())) {
            query.append(" AND P.status = :status ");
            params.put("status", queryValue.getStatus().ordinal());
        }
        return query.toString();
    }

    private String sortParamsBuilder(String sortValue) {
        StringBuilder sorting = new StringBuilder();
        if (!DataUtils.isBlank(sortValue)) {
            sorting.append(" ORDER BY P.").append(sortValue);
        }
        return sorting.toString();
    }
}
