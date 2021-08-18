package com.tua.wanchalerm.user.factory;

import com.tua.wanchalerm.user.constant.Response;
import com.tua.wanchalerm.user.response.GeneralResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.tua.wanchalerm.user.constant.Response.SUCCESS;


@Component
public class ResponseFactory {

    public ResponseEntity<GeneralResponse<Object>> success() {
        val responseObject = new GeneralResponse<>();
        responseObject.setCode(SUCCESS.getCode());
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity<GeneralResponse<Object>> success(Object data, Class clazz) {
        val responseObject = new GeneralResponse<>();
        responseObject.setCode(SUCCESS.getCode());
        responseObject.setData(clazz.cast(data));
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity<GeneralResponse<Object>> error(HttpStatus httpStatus, Response errorCode) {
        val responseObject = new GeneralResponse<>();
        responseObject.setCode(errorCode.getCode());
        return new ResponseEntity(responseObject, httpStatus);
    }

    public ResponseEntity<GeneralResponse<Object>> error(HttpStatus httpStatus, Response errorCode, Object data, Class clazz) {
        val responseObject = new GeneralResponse<>();
        responseObject.setCode(errorCode.getCode());
        responseObject.setData(clazz.cast(data));
        return new ResponseEntity(responseObject, httpStatus);
    }

}
