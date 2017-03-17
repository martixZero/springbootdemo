package com.springboot;

import com.springboot.ch3.entity.RedisUser;
import com.springboot.ch3.utils.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午5:23
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String,RedisUser> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,RedisUser> template=new RedisTemplate<>();
        //1.设置连接工厂
        template.setConnectionFactory(jedisConnectionFactory());
        //2.键字符串序列化
        template.setKeySerializer(new StringRedisSerializer());
        //3.值 对象序列化
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
