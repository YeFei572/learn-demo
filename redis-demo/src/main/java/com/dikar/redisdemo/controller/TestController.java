package com.dikar.redisdemo.controller;

import com.dikar.redisdemo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final RedisTemplate<Object, Object> redisTemplate;

    private final static String CACHE_KEY = "user_entity:";
    @GetMapping(value = "/test")
    public void testIncrement() {
        UserEntity userEntity = new UserEntity(2L, 10, "展飞");
        redisTemplate.opsForHash().put(CACHE_KEY, userEntity.getId(), userEntity);
        redisTemplate.expire(CACHE_KEY + userEntity.getId(), 10, TimeUnit.MINUTES);

        UserEntity exist = (UserEntity) redisTemplate.opsForHash().get(CACHE_KEY, userEntity.getId());
        log.info("-----------------------> {}", exist.toString());
        redisTemplate.opsForHash().increment(CACHE_KEY + userEntity.getId(), "age", 22);
        UserEntity incrementResult = (UserEntity) redisTemplate.opsForHash().get(CACHE_KEY, userEntity.getId());
        log.info("---------------------->{}", incrementResult.toString());
    }

}
