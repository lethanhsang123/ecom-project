package com.personal.project.productservice.domain.command;

import com.personal.project.productservice.domain.Product;
import com.personal.project.common.enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class ProductSkusUpdateCmd {

    private String code;

    private Long productId;

    private String description;

    private String name;

    private Double price;

    private Status status;

    private Product product;

    private List<ProductAttributeUpdateCmd> productAttributeUpdateCmds;
}
