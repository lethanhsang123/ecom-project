package com.personal.project.productservice.infrastructure.persistence.entity;

import com.personal.project.common.entity.AuditableEntity;
import com.personal.project.common.enums.Status;
import com.personal.project.common.validator.ValidateConstraint;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product_skus")
@Table(name = "product_skus")
@Getter
@Setter
public class ProductSkusEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private Long id;

    @Column(name = "code", nullable = false, length = ValidateConstraint.LENGTH.CODE_MAX_LENGTH)
    private String code;

    @Column(name = "product_id", nullable = false, length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private Long productId;

    @Column(name = "description", length = ValidateConstraint.LENGTH.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "name", nullable = false, length = ValidateConstraint.LENGTH.NAME_MAX_LENGTH)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status", nullable = false, length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
