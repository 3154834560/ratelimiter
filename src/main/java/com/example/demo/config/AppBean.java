package com.example.demo.config;

import com.example.demo.infrastructure.tool.JsonTool;
import com.example.demo.infrastructure.util.IocUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2023-03-21 17:32
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class AppBean {
    @Bean
    @Lazy(value = false)
    @ConditionalOnMissingBean
    IocUtil iocUtil() {
        return IocUtil.getInstance();
    }

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializerByType(LocalDateTime.class, JsonTool.localDateTimeJsonSerializer())
                .deserializerByType(LocalDateTime.class, JsonTool.localDateTimeJsonDeserializer());
    }

    @Bean
    RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(JsonTool.getObjectMapper());
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }
}
