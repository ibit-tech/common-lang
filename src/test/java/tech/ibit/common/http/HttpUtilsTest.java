package tech.ibit.common.http;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * HttpUtils工具类
 *
 * @author IBIT TECH
 */
public class HttpUtilsTest {

    @Test
    public void getParams() {
        String queryStr = "userId=1&name=IBIT+TECH&password=123";
        Map<String, String> params = HttpUtils.getParams(queryStr);
        assertEquals("1", params.get("userId"));
        assertEquals("IBIT TECH", params.get("name"));
        assertEquals("123", params.get("password"));
    }

    @Test
    public void getQueryString() {
        Map<String, String> params = new LinkedHashMap<String, String>() {{
            put("userId", "1");
            put("name", "IBIT TECH");
            put("password", "123");
        }};
        assertEquals("userId=1&name=IBIT+TECH&password=123", HttpUtils.getQueryString(params, null));
        assertEquals("userId=1&name=IBIT+TECH&password=**", HttpUtils.getQueryString(params, Collections.singletonList("password")));
    }
}