package tech.ibit.common.cache;

/**
 * 内存缓存，包装 LinkedHashCache
 *
 * @author IBIT程序猿
 */
public class MemCache<K, V> {

    /**
     * 被包装的缓存对象
     */
    private LinkedHashCache<K, V> cache;

    /**
     * 缓存时间（毫秒），0或者-1长期有效
     */
    private final int lifeTime;

    /**
     * 构造函数
     *
     * @param maxSize     缓存大小
     * @param lifeTime    缓存时间（单位：毫秒）
     * @param accessOrder 缓存方式，true：最后淘汰（LRU），false：先进先出（FIFO)
     */
    public MemCache(int maxSize, int lifeTime, boolean accessOrder) {
        this.lifeTime = lifeTime;
        cache = new LinkedHashCache<>(maxSize, accessOrder);
    }

    /**
     * 获取对象
     *
     * @param key 缓存key
     * @return 缓存对象
     */
    public synchronized V get(K key) {
        CacheItem<V> value = cache.get(key);
        return getValue(value);
    }


    /**
     * 设置缓存对象
     *
     * @param key   缓存key
     * @param value 缓存对象
     * @return 缓存对象
     */
    public synchronized V put(K key, V value) {
        long expiredTime = lifeTime > 0 ? (System.currentTimeMillis() + lifeTime) : 0;
        CacheItem<V> old = cache.put(key, new CacheItem<>(value, expiredTime));
        return getValue(old);
    }

    /**
     * 移除缓存
     *
     * @param key 缓存key
     * @return 移除的缓存值
     */
    public synchronized V remove(K key) {
        CacheItem<V> old = cache.remove(key);
        return getValue(old);
    }

    /**
     * 获取缓存值
     *
     * @param value 缓存对象
     * @return 缓存值
     */
    private V getValue(CacheItem<V> value) {
        return null == value ? null : value.get();
    }

    /**
     * 清空缓存
     */
    public synchronized void clear() {
        cache.clear();
    }

    /**
     * 获取缓存大小
     *
     * @return 缓存大小
     */
    public synchronized int size() {
        return cache.size();
    }


}
