package com.personal.project.common.error;

public enum AuthenticationError implements ResponseError {
    UNKNOWN(40100001, "UNKNOWN"),
    UNAUTHORISED(40100002, "Unauthorised"),
    ;

    private final Integer code;
    private final String message;

    AuthenticationError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public Integer getStatus() {
        return 401;
    }
}
