package com.personal.project.productservice.domain.command;

import com.personal.project.common.enums.Status;
import lombok.Data;

@Data
public class ProductCreateCmd {
    private String code;

    private String description;

    private String name;

    private String summary;

    private String cover;

    private Status status;
}
