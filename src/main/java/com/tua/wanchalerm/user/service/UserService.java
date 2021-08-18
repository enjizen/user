package com.tua.wanchalerm.user.service;

import com.tua.wanchalerm.user.model.User;
import com.tua.wanchalerm.user.model.db.UserEntity;
import com.tua.wanchalerm.user.model.redis.UserRedisEntity;
import com.tua.wanchalerm.user.repository.UserRedisRepository;
import com.tua.wanchalerm.user.repository.UserRepository;
import com.tua.wanchalerm.user.request.UserRequest;
import com.tua.wanchalerm.user.util.GenerateUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedisRepository userRedisRepository;

    public UserEntity getUserByEmail(String email) {
        log.info("Get user by email");
        return userRepository.findByEmail(email);
    }

    public UserRedisEntity createUserOnRedis(User user) {
        log.info("Create user on redis");
        val userRedisEntity = new UserRedisEntity();
        userRedisEntity.setId(GenerateUtil.generateKey16());
        userRedisEntity.setFirstName(user.getFirstName());
        userRedisEntity.setLastName(user.getLastName());
        userRedisEntity.setEmail(user.getEmail());
        userRedisEntity.setMobileNumber(user.getMobileNumber());
        userRedisEntity.setTimeToLive(10000);

       return userRedisRepository.save(userRedisEntity);
    }

    public UserRedisEntity getUserOnRedis(String id) {
        log.info("Get user from redis [{}] with id", id);
        return userRedisRepository.getOne(id);
    }

    public List<UserEntity> getUsers() {
        log.info("Get all user");
        return userRepository.findAll();
    }

    public UserEntity addUser(UserRequest request) throws SQLException {
        val userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setMobileNumber(request.getMobileNumber());
        userEntity.setPassword(request.getPassword());
        userEntity.setActive(true);

        return userRepository.save(userEntity);
    }
}
