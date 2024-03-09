package com.personal.project.productservice.application.service;

import com.personal.project.productservice.application.dto.request.ProductSkusCreateRequest;
import com.personal.project.common.dto.response.Response;
import com.personal.project.productservice.domain.ProductSkus;

public interface ProductSkusService {
    Response<ProductSkus> create(ProductSkusCreateRequest createCmd);

    Response<ProductSkus> getById(Long id);
}
