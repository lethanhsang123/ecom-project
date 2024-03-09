package com.personal.project.productservice.presentation.web.rest;

import com.personal.project.productservice.application.dto.request.ProductCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSearchRequest;
import com.personal.project.productservice.application.dto.request.ProductUpdateRequest;
import com.personal.project.common.dto.response.PagingResponse;
import com.personal.project.common.dto.response.Response;
import com.personal.project.productservice.domain.Product;
import com.personal.project.common.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AppConstants.API_URL + AppConstants.VERSION_ONE + AppConstants.ProductService.PRODUCT_URI)
@Validated
public interface ProductResource {

    @PostMapping
    Response<Product> create(@RequestBody @Valid ProductCreateRequest request);

    @GetMapping
    PagingResponse<Product> search(ProductSearchRequest request);

    @PutMapping("/{id}/update")
    Response<Product> update(@PathVariable Long id, ProductUpdateRequest request);

    @GetMapping("/{id}/active")
    Response<Product> active(@PathVariable Long id);

    @GetMapping("/{id}/inactive")
    Response<Product> inactive(@PathVariable Long id);

    @GetMapping("/{id}/detail")
    Response<Product> getById(@PathVariable Long id);

}
