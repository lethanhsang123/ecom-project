package com.personal.project.productservice.infrastructure.domainrepository;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.common.web.support.AbstractDomainRepository;
import com.personal.project.productservice.domain.ProductSkus;
import com.personal.project.productservice.domain.repository.ProductSkusDomainRepository;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductSkusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductSkusDomainRepositoryImpl extends AbstractDomainRepository<ProductSkus, ProductSkusEntity, Long>
        implements ProductSkusDomainRepository {
    protected ProductSkusDomainRepositoryImpl(JpaRepository<ProductSkusEntity, Long> jpaRepository,
                                              EntityMapper<ProductSkus, ProductSkusEntity> entityMapper) {
        super(jpaRepository, entityMapper);
    }
}
