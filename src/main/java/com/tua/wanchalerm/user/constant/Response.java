package com.tua.wanchalerm.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Response {
    SUCCESS("success"),
    INVALID_REQUEST("invalid_request");

    @Getter
    private final String code;
}
