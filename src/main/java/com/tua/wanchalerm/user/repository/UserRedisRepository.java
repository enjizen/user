package com.tua.wanchalerm.user.repository;

import com.tua.wanchalerm.user.model.redis.UserRedisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
public interface UserRedisRepository extends JpaRepository<UserRedisEntity, String> {

}
