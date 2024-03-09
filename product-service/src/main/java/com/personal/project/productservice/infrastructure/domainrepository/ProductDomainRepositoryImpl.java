package com.personal.project.productservice.infrastructure.domainrepository;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.common.web.support.AbstractDomainRepository;
import com.personal.project.productservice.domain.Product;
import com.personal.project.productservice.domain.repository.ProductDomainRepository;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDomainRepositoryImpl extends AbstractDomainRepository<Product, ProductEntity, Long> implements ProductDomainRepository {

    protected ProductDomainRepositoryImpl(
            JpaRepository<ProductEntity, Long> jpaRepository,
            EntityMapper<Product, ProductEntity> entityMapper) {
        super(jpaRepository, entityMapper);
    }
}
