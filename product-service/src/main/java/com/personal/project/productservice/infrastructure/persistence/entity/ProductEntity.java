package com.personal.project.productservice.infrastructure.persistence.entity;

import com.personal.project.common.entity.AuditableEntity;
import com.personal.project.common.enums.Status;
import com.personal.project.common.validator.ValidateConstraint;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private Long id;

    @Column(name = "code", nullable = false, length = ValidateConstraint.LENGTH.CODE_MAX_LENGTH)
    private String code;

    @Column(name = "description", length = ValidateConstraint.LENGTH.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "name", nullable = false, length = ValidateConstraint.LENGTH.NAME_MAX_LENGTH)
    private String name;

    @Column(name = "summary", length = ValidateConstraint.LENGTH.DESCRIPTION_MAX_LENGTH)
    private String summary;

    @Column(name = "cover", length = ValidateConstraint.LENGTH.DESCRIPTION_MAX_LENGTH)
    private String cover;

    @Column(name = "status", nullable = false, length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
