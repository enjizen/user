package com.tua.wanchalerm.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCreateConfirmRequest {
    @JsonProperty("key_confirm")
    private String keyConfirm;
}
