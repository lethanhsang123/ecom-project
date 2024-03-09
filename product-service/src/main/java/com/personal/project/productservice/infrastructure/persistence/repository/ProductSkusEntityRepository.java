package com.personal.project.productservice.infrastructure.persistence.repository;

import com.personal.project.productservice.infrastructure.persistence.entity.ProductSkusEntity;
import com.personal.project.productservice.infrastructure.persistence.repository.custom.ProductSkusEntityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkusEntityRepository extends ProductSkusEntityRepositoryCustom, JpaRepository<ProductSkusEntity, Long> {
}
