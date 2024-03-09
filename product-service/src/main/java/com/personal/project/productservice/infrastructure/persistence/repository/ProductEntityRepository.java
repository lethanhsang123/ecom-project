package com.personal.project.productservice.infrastructure.persistence.repository;

import com.personal.project.productservice.infrastructure.persistence.entity.ProductEntity;
import com.personal.project.productservice.infrastructure.persistence.repository.custom.ProductEntityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntityRepository extends ProductEntityRepositoryCustom, JpaRepository<ProductEntity, Long> {
}
