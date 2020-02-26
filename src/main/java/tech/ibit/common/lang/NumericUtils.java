package tech.ibit.common.lang;

import lombok.experimental.UtilityClass;

/**
 * 数字工具类
 *
 * @author IBIT TECH
 */
@UtilityClass
public class NumericUtils {

    /**
     * 判断数字是否为空
     *
     * @param value 值
     * @return 是否为空
     */
    public boolean isEmpty(Integer value) {
        return null == value || 0 >= value;
    }

    /**
     * 判断数字是否为非空
     *
     * @param value 值
     * @return 是否为非空
     */
    public boolean isNotEmpty(Integer value) {
        return null != value && value > 0;
    }

    /**
     * 判断数字是否为空
     *
     * @param value 值
     * @return 是否为空
     */
    public boolean isEmpty(Long value) {
        return null == value || 0 >= value;
    }

    /**
     * 判断数字是否为非空
     *
     * @param value 值
     * @return 是否为非空
     */
    public boolean isNotEmpty(Long value) {
        return null != value && value > 0;
    }

    /**
     * 判断数字是否为空
     *
     * @param value 值
     * @return 是否为空
     */
    public boolean isEmpty(Double value) {
        return null == value || 0 >= value;
    }

    /**
     * 判断数字是否为非空
     *
     * @param value 值
     * @return 是否为非空
     */
    public boolean isNotEmpty(Double value) {
        return null != value && value > 0;
    }


}
