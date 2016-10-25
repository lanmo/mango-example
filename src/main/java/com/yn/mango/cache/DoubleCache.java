package com.yn.mango.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangnan on 16/10/25.
 */
public class DoubleCache<K, V> implements LoadingCache<K, V> {

    private final CacheLoader<K, V> loader;
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<K, V>();
    private final ConcurrentHashMap<K, Object> locks = new ConcurrentHashMap<K, Object>();


    public DoubleCache(CacheLoader<K, V> loader) {
        this.loader = loader;
    }

    public V get(K k) {

        V value = cache.get(k);
        if (value == null) {
            synchronized (getLock(k)) {
                if (value == null) {
                    value = loader.load(k);
                    if (value != null) {
                        cache.put(k, value);
                    }
                }
            }
        }

        return value;
    }

    public Object getLock(K k) {
        Object lock = locks.get(k);
        if (lock == null) {
            lock = new Object();
            Object old = locks.putIfAbsent(k, lock);
            if (old != null) {
                //已经存在lock
                lock = old;
            }
        }
        return lock;
    }
}
