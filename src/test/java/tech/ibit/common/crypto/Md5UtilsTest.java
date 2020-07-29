package tech.ibit.common.crypto;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * Md5Utils 测试用例
 *
 * @author IBIT程序猿
 */
public class Md5UtilsTest {

    private String text = "12345678";
    private String cypherText = "25D55AD283AA400AF464C76D713C07AD";

    @Test
    public void encrypt() throws NoSuchAlgorithmException {
        assertEquals(cypherText, Md5Utils.encrypt(text));
    }

    @Test
    public void encryptLowerCase() throws NoSuchAlgorithmException {
        assertEquals(cypherText.toLowerCase(), Md5Utils.encryptLowerCase(text));
    }

    @Test
    public void verify() throws NoSuchAlgorithmException {
        assertTrue(Md5Utils.verify(text, cypherText));
        assertTrue(Md5Utils.verify(text, cypherText.toLowerCase()));
        assertFalse(Md5Utils.verify(text + 1, cypherText));
    }
}