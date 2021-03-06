package tech.ibit.common.crypto;

/**
 * 3-DES加密算法
 *
 * @author iBit程序猿
 */
public class TripleDesUtils {

    /**
     * 3-des
     */
    private static final String KEY_DES = "DESede";

    /**
     * key长度
     */
    private static final int KEY_SIZE = 192;

    /**
     * 工具类，私有构造方法
     */
    private TripleDesUtils() {

    }


    /**
     * 加密字符串
     *
     * @param plainText 明文
     * @param key       加密key
     * @return 密文
     */
    public static String encrypt(String plainText, String key) {
        return CipherUtils.encrypt(KEY_DES, plainText, KEY_SIZE, key);
    }

    /**
     * 解密字符串
     *
     * @param cipherText 密文
     * @param key        加密key
     * @return 明文
     */
    public static String decrypt(String cipherText, String key) {
        return CipherUtils.decrypt(KEY_DES, cipherText, KEY_SIZE, key);
    }

}
