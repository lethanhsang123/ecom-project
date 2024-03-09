package com.personal.project.productservice.presentation.web.rest;

import com.personal.project.productservice.application.dto.request.ProductSkusCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSkusSearchRequest;
import com.personal.project.productservice.application.dto.request.ProductSkusUpdateRequest;
import com.personal.project.common.dto.response.PagingResponse;
import com.personal.project.common.dto.response.Response;
import com.personal.project.productservice.domain.ProductSkus;
import com.personal.project.common.util.AppConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AppConstants.API_URL + AppConstants.VERSION_ONE + AppConstants.ProductService.PRODUCT_SKUS_URI)
@Validated
public interface ProductSkusResource {
    @PostMapping
    Response<ProductSkus> create(ProductSkusCreateRequest request);

    @GetMapping
    PagingResponse<ProductSkus> search(ProductSkusSearchRequest request);

    @PutMapping("/{id}/update")
    Response<ProductSkus> update(@PathVariable Long id, ProductSkusUpdateRequest request);

    @GetMapping("/{id}/active")
    Response<ProductSkus> active(@PathVariable Long id);

    @GetMapping("/{id}/inactive")
    Response<ProductSkus> inactive(@PathVariable Long id);

    @GetMapping("/{id}/detail")
    Response<ProductSkus> getById(@PathVariable Long id);

    @GetMapping("/product/{id}")
    PagingResponse<ProductSkus> getByProductId(@PathVariable Long id);
}
