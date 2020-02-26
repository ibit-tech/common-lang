package tech.ibit.common.collection;

import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * 集合工具类
 *
 * @author IBIT TECH
 */
@UtilityClass
public class CollectionUtils {

    /**
     * List转Map
     *
     * @param list     列表
     * @param idGetter id获取方法
     * @param <T>      键类型
     * @param <V>      值类型
     * @return Map
     */
    public <T, V> Map<T, V> toMap(List<V> list, IdGetter<T, V> idGetter) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<T, V> result = new HashMap<>(list.size());
        list.forEach(obj -> result.put(idGetter.get(obj), obj));
        return result;
    }

    /**
     * List转LinkedMap
     *
     * @param list     列表
     * @param idGetter id获取方法
     * @param <T>      键类型
     * @param <V>      值类型
     * @return LinkedMap
     */
    public <T, V> Map<T, V> toLinkedMap(List<V> list, IdGetter<T, V> idGetter) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<T, V> result = new LinkedHashMap<>(list.size());
        list.forEach(obj -> result.put(idGetter.get(obj), obj));
        return result;
    }

    /**
     * 通过键获取值
     *
     * @param map map
     * @param ids 键列表
     * @param <T> 键类型
     * @param <V> 值类型
     * @return 值列表
     */
    public <T, V> List<V> toList(Map<T, V> map, List<T> ids) {
        if (isEmpty(map) || isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<V> result = new ArrayList<>(ids.size());
        ids.forEach(id -> {
            V v = map.get(id);
            if (null != v) {
                result.add(v);
            }
        });
        return result;
    }

    /**
     * 按照键分组
     *
     * @param list     列表
     * @param idGetter id获取方法
     * @param <T>      键类型
     * @param <V>      值类型
     * @return 分组后Map
     */
    public <T, V> Map<T, List<V>> grouping(List<V> list, IdGetter<T, V> idGetter) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<T, List<V>> result = new HashMap<>(list.size());
        list.forEach(obj -> {
            T id = idGetter.get(obj);
            result.putIfAbsent(id, new ArrayList<>());
            result.get(id).add(obj);
        });
        return result;
    }

    /**
     * 按照键分组
     *
     * @param list     列表
     * @param idGetter id获取方法
     * @param <T>      键类型
     * @param <V>      值类型
     * @return 分组后LinkedMap
     */
    public <T, V> Map<T, List<V>> groupingToLinkedMap(List<V> list, IdGetter<T, V> idGetter) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<T, List<V>> result = new LinkedHashMap<>();
        list.forEach(obj -> {
            T id = idGetter.get(obj);
            result.putIfAbsent(id, new ArrayList<>());
            result.get(id).add(obj);
        });
        return result;
    }

    /**
     * 获取子Map
     *
     * @param map map
     * @param ids 键列表
     * @param <T> 键类型
     * @param <V> 值类型
     * @return 子Map
     */
    public <T, V> Map<T, V> getSubMap(Map<T, V> map, List<T> ids) {

        if (isEmpty(map) || isEmpty(ids)) {
            return Collections.emptyMap();
        }
        Map<T, V> result = new HashMap<>(ids.size());
        ids.forEach(id -> {
            V v = map.get(id);
            if (null != v) {
                result.put(id, v);
            }
        });
        return result;
    }

    /**
     * 获取子LinkedMap
     *
     * @param map map
     * @param ids 键列表
     * @param <T> 键类型
     * @param <V> 值类型
     * @return 子LinkedMap
     */
    public <T, V> Map<T, V> getSubLinkedMap(Map<T, V> map, List<T> ids) {
        if (isEmpty(map) || isEmpty(ids)) {
            return Collections.emptyMap();
        }
        Map<T, V> result = new LinkedHashMap<>();
        ids.forEach(id -> {
            V v = map.get(id);
            if (null != v) {
                result.put(id, v);
            }
        });
        return result;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return 判断结果
     */
    public boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断集合是否为非空
     *
     * @param collection 集合
     * @return 判断结果
     */
    public boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     *
     * @param map Map
     * @return 判断结果
     */
    public boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    /**
     * 判断map是否为非空
     *
     * @param map Map
     * @return 判断结果
     */
    public boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

}
