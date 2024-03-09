package com.personal.project.productservice.infrastructure.domainrepository;

import com.personal.project.common.mapper.EntityMapper;
import com.personal.project.common.web.support.AbstractDomainRepository;
import com.personal.project.productservice.domain.Discount;
import com.personal.project.productservice.domain.repository.DiscountDomainRepository;
import com.personal.project.productservice.infrastructure.persistence.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public class DiscountDomainRepositoryImpl extends AbstractDomainRepository<Discount, DiscountEntity, Long>
        implements DiscountDomainRepository {
    protected DiscountDomainRepositoryImpl(JpaRepository<DiscountEntity, Long> jpaRepository,
                                           EntityMapper<Discount, DiscountEntity> entityMapper) {
        super(jpaRepository, entityMapper);
    }
}
