package com.personal.project.common.cache;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@AutoConfigureAfter(RedisCacheConfig.class)
public class CacheManagerConfiguration implements CachingConfigurer {

    private final RedisConnectionFactory redisConnectionFactory;
    private final RedisCacheConfiguration redisCacheConfiguration;

    public CacheManagerConfiguration(RedisConnectionFactory redisConnectionFactory,
                                     RedisCacheConfiguration redisCacheConfiguration) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.redisCacheConfiguration = redisCacheConfiguration;
    }

    /**
     *  Method cache manage configuration
     * @return
     */
    @Bean
    @Override
    public CacheManager cacheManager() {

        // Get Redis cache configuration
        RedisCacheConfiguration redisCacheConfiguration = this.redisCacheConfiguration;

        RedisCacheManager.RedisCacheManagerBuilder cacheManager = RedisCacheManager.RedisCacheManagerBuilder
                .fromCacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(this.redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration);

        return cacheManager.build();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new RedisCacheErrorHandler();
    }

}
