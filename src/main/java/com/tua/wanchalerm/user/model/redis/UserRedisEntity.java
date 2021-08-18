package com.tua.wanchalerm.user.model.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@RedisHash("user")
public class UserRedisEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String deviceId;
    private String clientId;
    @TimeToLive
    private Integer timeToLive;
}
