package com.personal.project.productservice.domain.command;

import com.personal.project.common.enums.Status;
import lombok.Data;

@Data
public class ProductSkusCreateCmd {
    private String code;

    private Long productId;

    private String description;

    private String name;

    private Double price;

    private Status status;
}
