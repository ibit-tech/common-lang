package tech.ibit.common.cache;

/**
 * FIFO缓存
 *
 * @author iBit程序猿
 */
public class FifoCache<K, V> extends MemCache<K, V> {

    /**
     * 构造FIFO缓存
     *
     * @param maxSize  缓存大小
     * @param lifeTime 缓存时间（单位：毫秒）
     */
    public FifoCache(int maxSize, int lifeTime) {
        super(maxSize, lifeTime, false);
    }

}
