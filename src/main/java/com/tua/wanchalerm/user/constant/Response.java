package com.tua.wanchalerm.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Response {
    SUCCESS("success"),
    INVALID_REQUEST("invalid request"),
    DATA_NOT_FOUND("Data not found"),
    DUPLICATE("Duplicate"),
    UNAUTHORIZED("Unauthorized");

    @Getter
    private final String code;
}
