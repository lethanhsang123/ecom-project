package com.personal.project.common.util;

public class DataUtils {

    private DataUtils(){}

    public static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean isBlank(Object object) {
        return object == null;
    }

    public static String safeToString(String value) {
        return value != null ? value.trim() : "";
    }

}
