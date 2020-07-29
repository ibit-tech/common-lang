package tech.ibit.common.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于 LinkedHashMap 实现的缓存
 *
 * @param <K> 键模板类型
 * @param <V> 值模板类型
 * @author IBIT程序猿
 */
@SuppressWarnings({"unchecked", "rawtypes"})
class LinkedHashCache<K, V> extends LinkedHashMap<K, CacheItem<V>> {

    /**
     * 缓存大小
     */
    private final int maxSize;

    /**
     * 构造函数
     *
     * @param maxSize     缓存大小
     * @param accessOrder 缓存方式，true：最后淘汰（LRU），false：先进先出（FIFO)
     */
    LinkedHashCache(int maxSize, boolean accessOrder) {
        // 限制容量的1/3作为初始容量, 让hash表最多只扩充两次，减少空间浪费
        super(calcInitialCapacity(maxSize), 0.75f, accessOrder);
        this.maxSize = maxSize;
    }

    /**
     * 初始化容器
     *
     * @param maxSize 最大长度
     * @return 出事化容量大小
     */
    private static int calcInitialCapacity(int maxSize) {
        return Math.min((maxSize + 2) / 3, 16);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, CacheItem<V>> eldest) {
        // shrink the map if size reach max
        if (super.size() > maxSize) {
            return true;
        }

        CacheItem<V> value = eldest.getValue();

        if (value != null) {
            // 过期需要清理
            if (value.getExpiredTime() > 0 && System.currentTimeMillis() >= value.getExpiredTime()) {
                value.clear();
                return true;
            }
        }
        return false;
    }

    @Override
    public CacheItem<V> get(Object key) {

        CacheItem<V> value = super.get(key);

        if (value == null) {
            return null;
        } else {
            if (value.getExpiredTime() > 0 && System.currentTimeMillis() >= value.getExpiredTime()) {
                value.clear();
                return null;
            }
            return value;
        }
    }
}
