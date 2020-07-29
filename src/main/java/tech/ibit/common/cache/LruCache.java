package tech.ibit.common.cache;

/**
 * LRU缓存
 *
 * @author IBIT程序猿
 */
public class LruCache<K, V> extends MemCache<K, V> {

    /**
     * 构造LRU缓存
     */
    public LruCache(int maxSize, int lifeTime) {
        super(maxSize, lifeTime, true);
    }

}