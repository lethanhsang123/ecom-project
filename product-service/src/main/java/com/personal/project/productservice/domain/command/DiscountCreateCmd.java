package com.personal.project.productservice.domain.command;

import com.personal.project.common.enums.Status;
import lombok.Data;

@Data
public class DiscountCreateCmd {
    private String code;

    private Long productSkusId;

    private String description;

    private Float discountPercent;

    private Status status;
}
