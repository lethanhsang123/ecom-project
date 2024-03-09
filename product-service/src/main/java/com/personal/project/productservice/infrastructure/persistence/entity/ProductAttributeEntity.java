package com.personal.project.productservice.infrastructure.persistence.entity;

import com.personal.project.common.entity.AuditableEntity;
import com.personal.project.productservice.infrastructure.support.enums.AttributeType;
import com.personal.project.common.enums.Status;
import com.personal.project.common.validator.ValidateConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Table(name = "product_attribute")
@Entity
public class ProductAttributeEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private Long id;

    @NotNull
    @Column(name = "product_skus_id", nullable = false, length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private Long productSkusId;

    @NotNull
    @Column(name = "code", nullable = false, length = ValidateConstraint.LENGTH.CODE_MAX_LENGTH)
    private String code;

    @Column(name = "description", length = ValidateConstraint.LENGTH.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "type", length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private AttributeType type;

    @Column(name = "name", length = ValidateConstraint.LENGTH.NAME_MAX_LENGTH)
    private String name;
    @Column(name = "value", length = ValidateConstraint.LENGTH.TITLE_MAX_LENGTH)
    private String value;

    @NotNull
    @Column(name = "status", nullable = false, length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
