package com.personal.project.productservice.domain.command;

import com.personal.project.common.enums.Status;
import lombok.Data;

@Data
public class ProductUpdateCmd {

    private String description;

    private String name;

    private String summary;

    private String cover;

    private Status status;
}
