package com.personal.project.productservice.application.service;

import com.personal.project.productservice.application.dto.request.ProductCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSearchRequest;
import com.personal.project.productservice.application.dto.request.ProductUpdateRequest;
import com.personal.project.common.dto.response.PagingResponse;
import com.personal.project.common.dto.response.Response;
import com.personal.project.productservice.domain.Product;

public interface ProductService {
    Response<Product> create(ProductCreateRequest request);

    PagingResponse<Product> search(ProductSearchRequest request);

    Response<Product> update(Long id, ProductUpdateRequest request);

    Response<Product> active(Long id);

    Response<Product> inActive(Long id);

    Response<Product> getById(Long id);
}
