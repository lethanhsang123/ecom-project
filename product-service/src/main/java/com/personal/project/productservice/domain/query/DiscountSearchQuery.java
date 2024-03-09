package com.personal.project.productservice.domain.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class DiscountSearchQuery extends PagingQuery{
}
