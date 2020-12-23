package tech.ibit.common.crypto;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Md5UtilsTest
 *
 * @author iBit程序猿
 */
public class Md5UtilsTest {

    @Test
    public void verify() throws NoSuchAlgorithmException {
        Assert.assertTrue(Md5Utils.verify("iBit程序猿", "085345A9475BD53D12AC3479E8033209"));
        Assert.assertTrue(Md5Utils.verify("iBit程序猿", "085345A9475BD53D12AC3479E8033209".toLowerCase()));
        Assert.assertFalse(Md5Utils.verify("iBit程序猿", "085345A9475BD53D12AC3479E80332091"));
    }
}