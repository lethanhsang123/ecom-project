package com.personal.project.productservice.application.dto.request;

import com.personal.project.common.dto.request.PagingRequest;
import com.personal.project.common.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSearchRequest extends PagingRequest {
    private String code;
    private String name;
    private Status status;
}
