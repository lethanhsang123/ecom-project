package com.personal.project.common.util;

public final class AppConstants {

    public static final String API_URL = "/api";
    public static final String VERSION_ONE = "/v1";

    public interface ProductService {
        String PRODUCT_SKUS_URI = "/product-skus";
        String PRODUCT_URI = "/products";
    }

    private AppConstants() {
        throw new AssertionError("Instantiation not allowed for this utility class.");
    }

}
