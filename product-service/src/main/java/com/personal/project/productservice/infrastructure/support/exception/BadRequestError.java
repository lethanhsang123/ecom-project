package com.personal.project.productservice.infrastructure.support.exception;

import com.personal.project.common.error.ResponseError;
import org.springframework.http.HttpStatus;

public enum BadRequestError implements ResponseError {
    PRODUCT_NOT_FOUND(40000005, "Product not found"),
    PRODUCT_SKUS_NOT_FOUND(40000006, "ProductSkus not found")
    ;

    private final Integer code;
    private final String message;

    BadRequestError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public Integer getStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
