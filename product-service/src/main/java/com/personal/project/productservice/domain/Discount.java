package com.personal.project.productservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.project.common.domain.AuditableDomain;
import com.personal.project.common.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Discount extends AuditableDomain {

    private Long id;

    private String code;

    private Long productSkusId;

    private String description;

    private Float discountPercent;

    private Status status;
}
