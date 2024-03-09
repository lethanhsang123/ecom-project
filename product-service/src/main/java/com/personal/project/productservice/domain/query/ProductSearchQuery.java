package com.personal.project.productservice.domain.query;

import com.personal.project.common.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ProductSearchQuery extends PagingQuery{
    private String code;
    private String name;
    private Status status;
}
