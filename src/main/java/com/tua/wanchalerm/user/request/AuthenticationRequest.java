package com.tua.wanchalerm.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
