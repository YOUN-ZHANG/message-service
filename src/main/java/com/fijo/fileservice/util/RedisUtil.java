package com.fijo.fileservice.util;

import com.fijo.fileservice.enums.SysEnum.StatueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
public class RedisUtil {
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private static RedisTemplate redisTemplate;

    public static String insertIntoRedis(String key,String value){
            String result = StatueEnum.RESULT_CODE_SUCCESS.getMsg();

            stringRedisTemplate.opsForValue().set(key,value);

            return result;
    }
}

