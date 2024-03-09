package com.personal.project.productservice.application.dto.request;

import com.personal.project.common.dto.request.Request;
import com.personal.project.common.enums.Status;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSkusCreateRequest extends Request {

    @NotEmpty
    private String code;

    @NotEmpty
    private Long productId;

    private String description;

    private String name;

    private Double price;

    private Status status;
}
