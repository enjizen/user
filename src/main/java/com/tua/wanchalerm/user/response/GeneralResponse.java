package com.tua.wanchalerm.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralResponse<T> implements Serializable {

    @JsonProperty("code")
    private String code;

    @JsonProperty("data")
    private T data;

}
