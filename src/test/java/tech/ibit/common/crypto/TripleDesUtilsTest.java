package tech.ibit.common.crypto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 3-DES加密算法测试
 *
 * @author iBit程序猿
 */
public class TripleDesUtilsTest {

    private String key;

    @Before
    public void setKey() {
        key = "ibit-common";
    }

    @Test
    public void encrypt() {
        Assert.assertEquals("HWGX/cuYcIHswqIeVD7FUw==", TripleDesUtils.encrypt("iBit程序猿", key));
    }

    @Test
    public void decrypt() {
        Assert.assertEquals("iBit程序猿", TripleDesUtils.decrypt("HWGX/cuYcIHswqIeVD7FUw==", key));
    }
}