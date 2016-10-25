package com.yn.mango.cache;

/**
 * Created by yangnan on 16/10/25.
 */
public interface CacheLoader<K, V> {
    V load(K k);
}
