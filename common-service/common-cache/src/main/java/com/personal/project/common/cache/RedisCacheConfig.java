package com.personal.project.common.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.personal.project.common.cache.support.PageSerializer;
import com.personal.project.common.util.MapperFactoryUtil;
import io.lettuce.core.ClientOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.util.Objects;

@Slf4j
@EnableCaching
@Configuration
@AutoConfigureAfter({RedisAutoConfiguration.class})
@EnableConfigurationProperties({CacheProperties.class, RedisProperties.class})  // enable bean with ConfigurationProperties(application)
@ConditionalOnClass({CacheProperties.Redis.class})  // CacheProperties.Redis.class la condition cho RedisCacheConfig
public class RedisCacheConfig {

    private final CacheProperties cacheProperties;  // cache properties (application setting)
    private final RedisProperties redisProperties;  // redis properties (application setting)

    public RedisCacheConfig(CacheProperties cacheProperties, RedisProperties redisProperties) {
        this.cacheProperties = cacheProperties;
        this.redisProperties = redisProperties;
    }

    /**
     * Redis connection bean
     * @return RedisConnectionFactory
     */
    @Bean
    protected RedisConnectionFactory redisConnectionFactory() {

        // Redis configuration properties
        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .commandTimeout(redisProperties.getTimeout()) // Sets the command timeout for synchronous and reactive Redis commands.
//                .shutdownTimeout(redisProperties.getConnectTimeout()) // Sets the maximum time to wait for the client to close connections during shutdown.
                .clientOptions( //  Sets the behavior of the client when it is in a disconnected state
                        ClientOptions.builder().disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS).build()
                ).build();

        return new LettuceConnectionFactory(redisConfiguration, clientConfiguration);
    }

    /**
     * Redis template been
     * @return RedisTemplate
     */
    @Bean
    protected RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    /**
     * Redis Cache configuration bean
     * @return RedisCacheConfiguration
     */
    @Bean
    protected RedisCacheConfiguration redisCacheConfiguration() {
        // Redis cache
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        // Get Redis cache properties (application setting)
        CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
        redisCacheConfiguration = handleRedisCacheConfiguration(redisProperties, redisCacheConfiguration);

        return redisCacheConfiguration;
    }

    /**
     * Method setting jackson Serializer
     * @return Jackson2JsonRedisSerializer
     */
    private Jackson2JsonRedisSerializer<Object> getJackson2JsonRedisSerializer() {
        // get jackson mapper
        ObjectMapper objectMapper = MapperFactoryUtil.jacksonMapper();
        objectMapper.registerModules(new SimpleModule().addSerializer(PageImpl.class, new PageSerializer()), new Hibernate5Module());

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

        return jackson2JsonRedisSerializer;
    }

    /**
     * Method setting Redis cache configuration properties from application file
     * @param redisProperties redis properties in application
     * @param redisCacheConfiguration redis cache configuration
     * @return RedisCacheConfiguration
     */
    private RedisCacheConfiguration handleRedisCacheConfiguration(CacheProperties.Redis redisProperties, RedisCacheConfiguration redisCacheConfiguration) {
        if (Objects.isNull(redisProperties)) {
            return redisCacheConfiguration;
        }
        // redis caching configuration
        if (redisProperties.getTimeToLive() != null) {
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            redisCacheConfiguration = redisCacheConfiguration.computePrefixWith(cacheName -> cacheName + redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
        }
        // JackSon Serializer
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getJackson2JsonRedisSerializer();
        redisCacheConfiguration = redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
        return redisCacheConfiguration;
    }

}
