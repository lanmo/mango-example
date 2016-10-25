package com.yn.mango.cache;

/**
 * Created by yangnan on 16/10/25.
 */
public interface LoadingCache<K, V> {
    V get(K k);
}
