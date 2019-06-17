package com.deloitte.services.fssc.common.service;

import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class FsscRedisService {


    @Autowired
    private RedisTemplate redisTemplate;


    public String getString(String key){
        Object o = redisTemplate.opsForValue().get(key);
        String s = StringUtil.objectToString(o);
        return s;
    }

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

}
