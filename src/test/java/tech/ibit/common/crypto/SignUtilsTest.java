package tech.ibit.common.crypto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * SignUtils测试用例
 *
 * @author iBit程序猿
 */
public class SignUtilsTest {

    private Map<String, String> params;
    private final String secret = "i-monster";



    @Before
    public void init() {
        long timestamp = System.currentTimeMillis();

        params = new HashMap<>();
        params.put("app", "monster");
        params.put("timestamp", String.valueOf(timestamp));
        params.put("version", "1.0");
        params.put("method", "boomshakalaka");
        String sign = SignUtils.sign(params, secret);
        params.put("sign", sign);
    }

    @Test
    public void verify() {
        Assert.assertTrue(SignUtils.verify(params, secret));

        Map<String, String> params = new HashMap<>(this.params);
        params.put("version", "2.0");
        Assert.assertFalse(SignUtils.verify(params, secret));
    }
}