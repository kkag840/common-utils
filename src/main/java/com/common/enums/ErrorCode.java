package com.common.enums;


import com.common.exception.ErrorCodes;

public enum ErrorCode implements ErrorCodes {


    INVALID_DATA("COMMON_UTILS_001"),
    RESOURCE_NOT_FOUND("COMMON_UTILS_002"),
    OPERATION_FAILED("COMMON_UTILS_003"),
    TIMEOUT_OCCURRED("COMMON_UTILS_004"),
    CONFLICT_ERROR("COMMON_UTILS_005"),
    INTERNAL_SERVER_ERROR("COMMON_UTILS_006"),
    SERVICE_UNAVAILABLE("COMMON_UTILS_007"),
    CONNECTION_FAILED("COMMON_UTILS_008"),
    LANGUAGE_NOT_SUPPORT("COMMON_UTILS_009"),
    DATABASE_OPERATION_FAILED("COMMON_UTILS_010"),;

    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDefaultMessage() {
        return "";
    }

}
