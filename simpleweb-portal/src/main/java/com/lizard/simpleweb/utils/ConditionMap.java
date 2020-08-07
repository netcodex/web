package com.lizard.simpleweb.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述：SQL查询条件
 *
 * @author x
 * @since 2020-08-07 22:42
 */
public class ConditionMap<K, V> extends LinkedHashMap<K, V> {
    public ConditionMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    public ConditionMap(K key, V value) {
        super();
        super.put(key, value);
    }

    public ConditionMap<K, V> add(K key, V value) {
        super.put(key, value);
        return this;
    }

    public ConditionMap<K, V> addAll(Map<K, V> map) {
        super.putAll(map);
        return this;
    }
}
