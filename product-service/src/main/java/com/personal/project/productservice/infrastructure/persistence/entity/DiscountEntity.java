package com.personal.project.productservice.infrastructure.persistence.entity;

import com.personal.project.common.entity.AuditableEntity;
import com.personal.project.common.enums.Status;
import com.personal.project.common.validator.ValidateConstraint;
import jakarta.persistence.*;

@Entity
@Table(name = "discount")
public class DiscountEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private Long id;

    @Column(name = "code", nullable = false, length = ValidateConstraint.LENGTH.CODE_MAX_LENGTH)
    private String code;

    @Column(name = "product_skus_id", nullable = false, length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private Long productSkusId;

    @Column(name = "description", length = ValidateConstraint.LENGTH.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "discount_percent", nullable = false)
    private Float discountPercent;

    @Column(name = "status", nullable = false, length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
