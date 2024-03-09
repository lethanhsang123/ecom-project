package com.personal.project.productservice.application.mapper;

import com.personal.project.productservice.application.dto.request.ProductSearchRequest;
import com.personal.project.productservice.application.dto.request.ProductSkusSearchRequest;
import com.personal.project.productservice.domain.query.ProductSearchQuery;
import com.personal.project.productservice.domain.query.ProductSkusSearchQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutoMapperQuery {
    ProductSearchQuery toQuery(ProductSearchRequest request);

    ProductSkusSearchQuery toQuery(ProductSkusSearchRequest request);
}
