package com.personal.project.productservice.infrastructure.domainrepository;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.common.web.support.AbstractDomainRepository;
import com.personal.project.productservice.domain.ProductAttribute;
import com.personal.project.productservice.domain.repository.ProductAttributeDomainRepository;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public class ProductAttributeDomainRepositoryImpl extends AbstractDomainRepository<ProductAttribute, ProductAttributeEntity, Long>
        implements ProductAttributeDomainRepository {
    protected ProductAttributeDomainRepositoryImpl(JpaRepository<ProductAttributeEntity, Long> jpaRepository,
                                                   EntityMapper<ProductAttribute, ProductAttributeEntity> entityMapper) {
        super(jpaRepository, entityMapper);
    }
}
