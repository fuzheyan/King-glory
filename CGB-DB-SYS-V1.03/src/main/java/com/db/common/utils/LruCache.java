package com.db.common.utils;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class LruCache<K, V> implements Serializable {

    private static final long serialVersionUID = 7524171103260592813L;
    private int cap;
    private LinkedHashMap<K, V> cache;

    @SuppressWarnings("unchecked")
    public LruCache(int cap) {
        this.cap = cap;
        int maxCap = (int) Math.ceil(cap / 0.75f) + 1;
        cache = new LinkedHashMap(maxCap, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
                return size() > cap;
            }
        };
    }

    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }

    public synchronized V get(K key) {
        return cache.get(key);
    }

    @Override
    public String toString() {
        return "LruCache [cache=" + cache + "]";
    }

    public static void main(String[] args) {
        LruCache<String, Integer> lruCache =
                new LruCache<>(3);
        lruCache.put("A", 100);
        lruCache.put("B", 200);
        lruCache.put("C", 300);
        lruCache.get("A");
        lruCache.put("D", 400);
        System.out.println(lruCache);
    }
}
