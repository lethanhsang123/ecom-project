package com.personal.project.productservice.presentation.web.rest.impl;

import com.personal.project.productservice.application.dto.request.ProductCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSearchRequest;
import com.personal.project.productservice.application.dto.request.ProductUpdateRequest;
import com.personal.project.common.dto.response.PagingResponse;
import com.personal.project.common.dto.response.Response;
import com.personal.project.productservice.application.service.ProductService;
import com.personal.project.productservice.domain.Product;
import com.personal.project.productservice.presentation.web.rest.ProductResource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResourceImpl implements ProductResource {

    private final ProductService productService;

    public ProductResourceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Response<Product> create(ProductCreateRequest request) {
        return productService.create(request);
    }

    @Override
    public PagingResponse<Product> search(ProductSearchRequest request) {
        return productService.search(request);
    }

    @Override
    public Response<Product> update(Long id, ProductUpdateRequest request) {
        return productService.update(id, request);
    }

    @Override
    public Response<Product> active(Long id) {
        return this.productService.active(id);
    }

    @Override
    public Response<Product> inactive(Long id) {
        return this.productService.inActive(id);
    }

    @Override
    public Response<Product> getById(Long id) {
        return this.productService.getById(id);
    }
}
