package com.personal.project.productservice.domain;

import com.personal.project.common.domain.AuditableDomain;
import com.personal.project.common.enums.Status;
import com.personal.project.productservice.domain.command.ProductCreateCmd;
import com.personal.project.productservice.domain.command.ProductUpdateCmd;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder
public class Product extends AuditableDomain {

    private Long id;

    private String code;

    private String description;

    private String name;

    private String summary;

    private String cover;

    private Status status;

    private List<ProductSkus> productSkuses;

    public Product(ProductCreateCmd createCmd) {
        this.code = createCmd.getCode();
        this.description = (createCmd.getDescription());
        this.name = (createCmd.getName());
        this.summary = (createCmd.getSummary());
        this.cover = (createCmd.getCover());
        this.status = createCmd.getStatus() != null ? createCmd.getStatus() : Status.ACTIVE;
    }

    public void update(ProductUpdateCmd updateCmd) {
        this.description = (updateCmd.getDescription());
        this.name = (updateCmd.getName());
        this.summary = (updateCmd.getSummary());
        this.cover = (updateCmd.getCover());
            this.status = updateCmd.getStatus();
    }

    public void active() {
        this.status = Status.ACTIVE;
    }

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    public void assignProductSkus(List<ProductSkus> productSkuses) {
        if (this.productSkuses == null) this.productSkuses = new ArrayList<>();
        this.productSkuses.addAll(productSkuses);
    }
}
