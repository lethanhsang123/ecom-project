package com.personal.project.productservice.domain;

import com.personal.project.common.domain.AuditableDomain;
import com.personal.project.common.enums.Status;
import com.personal.project.productservice.domain.command.ProductSkusCreateCmd;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder
public class ProductSkus extends AuditableDomain {
    private Long id;
    private String code;

    private Long productId;

    private String description;

    private String name;

    private Double price;

    private Status status;

    private List<ProductAttribute> productAttributes;

    private Product product;

    private Discount discount;

    public ProductSkus(ProductSkusCreateCmd createCmd){
        this.code = (createCmd.getCode());
        this.productId = createCmd.getProductId();
        this.description = (createCmd.getDescription());
        this.name = (createCmd.getName());
        this.price = createCmd.getPrice();
        this.status = createCmd.getStatus() != null ? createCmd.getStatus() : Status.ACTIVE;
    }

    public void enrichProduct(Product product, Discount discount) {
        if (product != null) {
            this.product = product;
        }
        if (discount != null) {
            this.discount = discount;
        }
    }
}
