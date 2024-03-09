package com.personal.project.productservice.application.dto.request;

import com.personal.project.common.dto.request.Request;
import com.personal.project.common.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductCreateRequest extends Request {

    @NotBlank(message = "PRODUCT_CODE_REQUIRED")
    @NotNull(message = "PRODUCT_CODE_REQUIRED")
    private String code;

    private String description;

    private String name;

    private String summary;

    private String cover;

    private Status status;
}
