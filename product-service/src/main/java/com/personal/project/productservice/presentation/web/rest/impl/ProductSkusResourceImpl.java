package com.personal.project.productservice.presentation.web.rest.impl;

import com.personal.project.productservice.application.dto.request.ProductSkusCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSkusSearchRequest;
import com.personal.project.productservice.application.dto.request.ProductSkusUpdateRequest;
import com.personal.project.common.dto.response.PagingResponse;
import com.personal.project.common.dto.response.Response;
import com.personal.project.productservice.application.service.ProductSkusService;
import com.personal.project.productservice.domain.ProductSkus;
import com.personal.project.productservice.presentation.web.rest.ProductSkusResource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductSkusResourceImpl implements ProductSkusResource {

    private final ProductSkusService productSkusService;

    public ProductSkusResourceImpl(ProductSkusService productSkusService) {
        this.productSkusService = productSkusService;
    }

    @Override
    public Response<ProductSkus> create(ProductSkusCreateRequest request) {
        return this.productSkusService.create(request);
    }

    @Override
    public PagingResponse<ProductSkus> search(ProductSkusSearchRequest request) {
        return null;
    }

    @Override
    public Response<ProductSkus> update(Long id, ProductSkusUpdateRequest request) {
        return null;
    }

    @Override
    public Response<ProductSkus> active(Long id) {
        return null;
    }

    @Override
    public Response<ProductSkus> inactive(Long id) {
        return null;
    }

    @Override
    public Response<ProductSkus> getById(Long id) {
        return this.productSkusService.getById(id);
    }

    @Override
    public PagingResponse<ProductSkus> getByProductId(Long id) {
        return null;
    }
}
