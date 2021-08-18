package com.tua.wanchalerm.user.controller;

import com.tua.wanchalerm.user.constant.Response;
import com.tua.wanchalerm.user.factory.ResponseFactory;
import com.tua.wanchalerm.user.model.db.UserEntity;
import com.tua.wanchalerm.user.request.AuthenticationRequest;
import com.tua.wanchalerm.user.response.GeneralResponse;
import com.tua.wanchalerm.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping("/v1/authentication")
    public ResponseEntity<GeneralResponse<Object>> authentication(@RequestBody AuthenticationRequest request) {
        val userEntity = userService.getUserByEmail(request.getEmail());
        if ( userEntity == null ||
                !userEntity.getPassword().equals(request.getPassword())) {
            return responseFactory.error(HttpStatus.UNAUTHORIZED, Response.UNAUTHORIZED);
        }
        return responseFactory.success();
    }

}
