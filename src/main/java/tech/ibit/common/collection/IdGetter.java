package tech.ibit.common.collection;

/**
 * ID获取
 *
 * @author IBIT TECH
 */
@FunctionalInterface
public interface IdGetter<T, V> {

    /**
     * 获取id
     *
     * @param o 对象
     * @return id
     */
    T get(V o);
}
