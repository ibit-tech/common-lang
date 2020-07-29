package tech.ibit.common.cache;

import org.junit.Assert;
import org.junit.Test;

/**
 * LruCacheTest
 *
 * @author IBIT程序猿
 */
public class LruCacheTest {


    @Test
    public void test() throws InterruptedException {
        LruCache<String, String> cache = new LruCache<>(2, 1000);
        cache.put("name", "IBIT");
        cache.put("name1", "IBIT1");
        Assert.assertEquals("IBIT", cache.get("name"));
        Assert.assertEquals("IBIT1", cache.get("name1"));

        // 再访问一下name，这样name就在name1之后
        cache.get("name");

        cache.put("name2", "IBIT2");

        Assert.assertEquals(2, cache.size());
        Assert.assertEquals("IBIT", cache.get("name"));
        Assert.assertNull(cache.get("name1"));
        Assert.assertEquals("IBIT2", cache.get("name2"));

        Thread.sleep(1000);
        Assert.assertEquals(2, cache.size());
        Assert.assertNull(cache.get("name"));
        Assert.assertNull(cache.get("name2"));

        cache.clear();
        Assert.assertEquals(0, cache.size());

    }

}