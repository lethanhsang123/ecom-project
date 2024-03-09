package com.personal.project.common.error;

public interface ResponseError {

    String getName();

    String getMessage();

    Integer getCode();

    default Integer getStatus() { return 0; }

}
