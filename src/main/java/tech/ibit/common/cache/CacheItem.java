package tech.ibit.common.cache;

import java.lang.ref.SoftReference;

/**
 * 缓存对象
 *
 * @author IBIT程序猿
 */
class CacheItem<T> extends SoftReference<T> {

    /**
     * 获取时间
     */
    private long expiredTime;

    /**
     * 缓存对象
     *
     * @param value       缓存值
     * @param expiredTime 过期时间
     */
    CacheItem(T value, long expiredTime) {
        super(value);
        this.expiredTime = expiredTime;
    }

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    long getExpiredTime() {
        return expiredTime;
    }
}
