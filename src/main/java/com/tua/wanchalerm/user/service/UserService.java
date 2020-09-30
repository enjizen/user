package com.tua.wanchalerm.user.service;

import com.tua.wanchalerm.user.model.db.UserEntity;
import com.tua.wanchalerm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
