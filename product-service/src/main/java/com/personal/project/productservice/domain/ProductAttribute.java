package com.personal.project.productservice.domain;

import com.personal.project.common.domain.AuditableDomain;
import com.personal.project.productservice.infrastructure.support.enums.AttributeType;
import com.personal.project.common.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder
public class ProductAttribute extends AuditableDomain {

    private Long id;

    private Long productSkusId;

    private String code;

    private String description;

    private AttributeType type;

    private String name;

    private String value;

    private Status status;

    private ProductSkus productSkus;
}
