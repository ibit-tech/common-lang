package tech.ibit.common.cache;

/**
 * FIFO缓存
 *
 * @author IBIT程序猿
 */
public class FifoCache<K, V> extends MemCache<K, V> {

    /**
     * 构造FIFO缓存
     */
    public FifoCache(int maxSize, int lifeTime) {
        super(maxSize, lifeTime, false);
    }

}
