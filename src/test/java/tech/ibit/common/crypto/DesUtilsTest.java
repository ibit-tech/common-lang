package tech.ibit.common.crypto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DES加密算法测试
 *
 * @author iBit程序猿
 */
public class DesUtilsTest {


    private String key;

    @Before
    public void setKey() {
        key = "ibit-common";
    }



    @Test
    public void encrypt() {
        Assert.assertEquals("YGHihhIWKmYkA54+bP/+eg==", DesUtils.encrypt("iBit程序猿", key));
    }

    @Test
    public void decrypt() {
        Assert.assertEquals("iBit程序猿", DesUtils.decrypt("YGHihhIWKmYkA54+bP/+eg==", key));
    }
}