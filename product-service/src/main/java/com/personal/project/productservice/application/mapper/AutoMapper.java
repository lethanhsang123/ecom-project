package com.personal.project.productservice.application.mapper;

import com.personal.project.productservice.application.dto.request.ProductCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSkusCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSkusUpdateRequest;
import com.personal.project.productservice.application.dto.request.ProductUpdateRequest;
import com.personal.project.productservice.domain.command.ProductCreateCmd;
import com.personal.project.productservice.domain.command.ProductSkusCreateCmd;
import com.personal.project.productservice.domain.command.ProductSkusUpdateCmd;
import com.personal.project.productservice.domain.command.ProductUpdateCmd;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutoMapper {
    ProductCreateCmd from(ProductCreateRequest request);

    ProductUpdateCmd from(ProductUpdateRequest request);

    ProductSkusCreateCmd from(ProductSkusCreateRequest request);
    ProductSkusUpdateCmd from(ProductSkusUpdateRequest request);

}
