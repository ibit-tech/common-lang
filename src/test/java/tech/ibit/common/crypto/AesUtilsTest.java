package tech.ibit.common.crypto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * AES加密算法测试
 *
 * @author iBit程序猿
 */
public class AesUtilsTest {


    private String key;

    @Before
    public void setKey() {
        key = "ibit-common";
    }

    @Test
    public void encrypt() {
        Assert.assertEquals("XgGt5gkI9ThW94t5yy/xrA==", AesUtils.encrypt("iBit程序猿", key));
        Assert.assertNull(AesUtils.encrypt(null, key));
        Assert.assertEquals("aifD55niwNwARozBn2Xg4Q==", AesUtils.encrypt("", key));
    }

    @Test
    public void decrypt() {
        Assert.assertEquals("iBit程序猿", AesUtils.decrypt("XgGt5gkI9ThW94t5yy/xrA==", key));
        Assert.assertEquals("", AesUtils.decrypt("aifD55niwNwARozBn2Xg4Q==", key));
    }
}