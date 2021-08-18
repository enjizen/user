package com.tua.wanchalerm.user.controller;

import com.tua.wanchalerm.user.constant.Response;
import com.tua.wanchalerm.user.factory.ResponseFactory;
import com.tua.wanchalerm.user.model.User;
import com.tua.wanchalerm.user.model.db.UserEntity;
import com.tua.wanchalerm.user.request.UserCreateConfirmRequest;
import com.tua.wanchalerm.user.request.UserCreateRequest;
import com.tua.wanchalerm.user.request.UserRequest;
import com.tua.wanchalerm.user.response.GeneralErrorResponse;
import com.tua.wanchalerm.user.response.UserCreateResponse;
import com.tua.wanchalerm.user.response.UserResponse;
import com.tua.wanchalerm.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping("/v1/create")
    public ResponseEntity createUser(@RequestBody UserCreateRequest request) {
        log.info("==== Start Create user =====");

        val user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .build();

        val userEntity = userService.createUserOnRedis(user);
        log.info("==== End Create user =====");
        return responseFactory.success(new UserCreateResponse(userEntity.getId()), UserCreateResponse.class);
    }

    @PostMapping("/v1/create/confirm")
    public ResponseEntity confirmCreateUser(@RequestBody UserCreateConfirmRequest request) {
        val userRedisEntity = userService.getUserOnRedis(request.getKeyConfirm());

        if (userRedisEntity == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, Response.INVALID_REQUEST);
        }

        return responseFactory.success();
    }

    @GetMapping("/v1/users")
    public ResponseEntity getUser() {
        log.info("Get users");

        val userEntityList = userService.getUsers();

        val userResponseList = userEntityList.stream()
                .map(it -> UserResponse.builder()
                        .id(it.getId())
                        .email(it.getEmail())
                        .firstName(it.getFirstName())
                        .lastName(it.getLastName())
                        .mobileNumber(it.getMobileNumber())
                        .build()).collect(Collectors.toList());

        return responseFactory.success(userResponseList, List.class);

    }

    @PostMapping("/v1/users")
    public ResponseEntity addUser(@RequestBody UserRequest request) {
        try {
            UserEntity userEntity  = userService.addUser(request);
            val userResponse = UserResponse.builder()
                    .id(userEntity.getId())
                    .email(userEntity.getEmail())
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .mobileNumber(userEntity.getMobileNumber())
                    .build();
            return responseFactory.success(userResponse, UserResponse.class);
        } catch (SQLException e) {
           log.error("Insert user", e);
           if (e.getMessage().contains(request.getEmail())) {
               return responseFactory.error(HttpStatus.BAD_REQUEST, Response.DUPLICATE, new GeneralErrorResponse("Email Duplicate"), String.class);
           } else {
               return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, Response.DATA_NOT_FOUND);
           }
        }
    }

}
