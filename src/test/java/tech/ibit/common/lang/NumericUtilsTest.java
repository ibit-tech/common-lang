package tech.ibit.common.lang;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * NumericUtils工具类
 *
 * @author IBIT程序猿
 */
public class NumericUtilsTest {

    @Test
    public void isEmpty() {
        assertTrue(NumericUtils.isEmpty(-1L));
        assertTrue(NumericUtils.isEmpty(-1));
        assertTrue(NumericUtils.isEmpty(-1.0));
        assertTrue(NumericUtils.isEmpty(0L));
        assertTrue(NumericUtils.isEmpty(0));
        assertTrue(NumericUtils.isEmpty(0.0));
    }

    @Test
    public void isNotEmpty() {
        assertTrue(NumericUtils.isNotEmpty(1L));
        assertTrue(NumericUtils.isNotEmpty(1));
        assertTrue(NumericUtils.isNotEmpty(1.0));
    }
}