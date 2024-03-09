package com.personal.project.productservice.infrastructure.persistence.repository.redis;

public interface RedisRepository<K, V> {

    V get(K key);

    void put(K key, V value);

    void evict(K key);

}
