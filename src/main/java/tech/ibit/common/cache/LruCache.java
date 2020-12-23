package tech.ibit.common.cache;

/**
 * LRU缓存
 *
 * @author iBit程序猿
 */
public class LruCache<K, V> extends MemCache<K, V> {


    /**
     * 构造LRU缓存
     *
     * @param maxSize  缓存大小
     * @param lifeTime 缓存时间（单位：毫秒）
     */
    public LruCache(int maxSize, int lifeTime) {
        super(maxSize, lifeTime, true);
    }

}