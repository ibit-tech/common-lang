package tech.ibit.common.lang;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * StringUtils测试用例
 *
 * @author IBIT TECH
 */
public class StringUtilsTest {

    @Test
    public void toIntList() {
        String str = "1; 3; 4; 5";
        List<Integer> list = StringUtils.toIntList(str, ";");
        assertEquals(4, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
        assertEquals(Integer.valueOf(4), list.get(2));
        assertEquals(Integer.valueOf(5), list.get(3));


        str = "1, 3, 4, 5";
        list = StringUtils.toIntList(str);
        assertEquals(4, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
        assertEquals(Integer.valueOf(4), list.get(2));
        assertEquals(Integer.valueOf(5), list.get(3));
    }
}