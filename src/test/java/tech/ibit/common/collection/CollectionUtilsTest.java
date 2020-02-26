package tech.ibit.common.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * CollectionUtils测试用例
 *
 * @author IBIT TECH
 */
public class CollectionUtilsTest {

    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;

    @Test
    public void toMap() {
        List<User> users = getUsers();
        Map<Integer, User> userMap = CollectionUtils.toMap(users, User::getUserId);
        assertEquals(3, userMap.size());
        assertTrue(userMap.keySet().containsAll(getUserIds()));
    }

    @Test
    public void toLinkedMap() {
        List<User> users = getUsers();
        Map<Integer, User> userMap = CollectionUtils.toLinkedMap(users, User::getUserId);
        assertTrue(userMap instanceof LinkedHashMap);
        assertEquals(3, userMap.size());
        assertTrue(userMap.keySet().containsAll(getUserIds()));
    }

    @Test
    public void toList() {
        Map<Integer, User> userMap = CollectionUtils.toLinkedMap(getUsers(), User::getUserId);
        List<User> users = CollectionUtils.toList(userMap, Arrays.asList(2, 1));
        assertEquals(2, users.size());
        assertEquals(Integer.valueOf(2), users.get(0).getUserId());


        users = CollectionUtils.toList(userMap, Arrays.asList(2, 1, 4, 5));
        assertEquals(2, users.size());
        assertEquals(Integer.valueOf(2), users.get(0).getUserId());
    }

    @Test
    public void grouping() {
        List<User> users = getUsers();
        Map<Integer, List<User>> usersByType = CollectionUtils.grouping(users, User::getType);
        assertEquals(2, usersByType.size());
        assertTrue(usersByType.keySet().containsAll(Arrays.asList(TYPE_1, TYPE_2)));

        List<User> type1Users = usersByType.get(TYPE_1);
        assertEquals(2, type1Users.size());
        assertEquals(Integer.valueOf(1), type1Users.get(0).getUserId());
        assertEquals(Integer.valueOf(3), type1Users.get(1).getUserId());

        List<User> type2Users = usersByType.get(TYPE_2);
        assertEquals(1, type2Users.size());
        assertEquals(Integer.valueOf(2), type2Users.get(0).getUserId());

    }

    @Test
    public void groupingToLinkedMap() {
        List<User> users = getUsers();
        Map<Integer, List<User>> usersByType = CollectionUtils.groupingToLinkedMap(users, User::getType);
        assertTrue(usersByType instanceof LinkedHashMap);

        List<Integer> types = new ArrayList<>(usersByType.keySet());
        assertEquals(Integer.valueOf(TYPE_1), types.get(0));
        assertEquals(Integer.valueOf(TYPE_2), types.get(1));
        assertEquals(2, usersByType.size());
        assertTrue(usersByType.keySet().containsAll(Arrays.asList(TYPE_1, TYPE_2)));

        List<User> type1Users = usersByType.get(TYPE_1);
        assertEquals(2, type1Users.size());
        assertEquals(Integer.valueOf(1), type1Users.get(0).getUserId());
        assertEquals(Integer.valueOf(3), type1Users.get(1).getUserId());

        List<User> type2Users = usersByType.get(TYPE_2);
        assertEquals(1, type2Users.size());
        assertEquals(Integer.valueOf(2), type2Users.get(0).getUserId());
    }

    @Test
    public void getSubMap() {
        Map<Integer, User> userMap = CollectionUtils.toMap(getUsers(), User::getUserId);
        Map<Integer, User> subMap = CollectionUtils.getSubMap(userMap, Arrays.asList(1, 3));
        assertEquals(2, subMap.size());
        assertTrue(subMap.keySet().containsAll(Arrays.asList(1, 3)));

        subMap = CollectionUtils.getSubMap(userMap, Arrays.asList(1, 3, 4, 5));
        assertEquals(2, subMap.size());
        assertTrue(subMap.keySet().containsAll(Arrays.asList(1, 3)));
    }

    @Test
    public void getSubLinkedMap() {
        Map<Integer, User> userMap = CollectionUtils.toMap(getUsers(), User::getUserId);
        Map<Integer, User> subMap = CollectionUtils.getSubLinkedMap(userMap, Arrays.asList(3, 1));
        assertTrue(subMap instanceof LinkedHashMap);
        assertEquals(2, subMap.size());

        List<Integer> UserIds = new ArrayList<>(subMap.keySet());
        assertEquals(Integer.valueOf(3), UserIds.get(0));
        assertEquals(Integer.valueOf(1), UserIds.get(1));

        subMap = CollectionUtils.getSubMap(userMap, Arrays.asList(1, 3, 4, 5));
        assertEquals(2, subMap.size());

        UserIds = new ArrayList<>(subMap.keySet());
        assertEquals(Integer.valueOf(1), UserIds.get(0));
        assertEquals(Integer.valueOf(3), UserIds.get(1));
    }

    @Test
    public void isEmpty() {
        assertTrue(CollectionUtils.isEmpty(Collections.emptyList()));
        assertTrue(CollectionUtils.isEmpty(Collections.emptySet()));
        assertTrue(CollectionUtils.isEmpty(Collections.emptyMap()));
        assertFalse(CollectionUtils.isEmpty(Collections.singletonList(1)));
        assertFalse(CollectionUtils.isEmpty(Collections.singleton(1)));
        assertFalse(CollectionUtils.isEmpty(Collections.singleton(1)));

    }

    @Test
    public void isNotEmpty() {
        assertFalse(CollectionUtils.isNotEmpty(Collections.emptyList()));
        assertFalse(CollectionUtils.isNotEmpty(Collections.emptySet()));
        assertFalse(CollectionUtils.isNotEmpty(Collections.emptyMap()));
        assertTrue(CollectionUtils.isNotEmpty(Collections.singletonList(1)));
        assertTrue(CollectionUtils.isNotEmpty(Collections.singleton(1)));
        assertTrue(CollectionUtils.isNotEmpty(Collections.singleton(1)));
    }

    private List<Integer> getUserIds() {
        return Arrays.asList(1, 2, 3);
    }

    private List<User> getUsers() {
        return Arrays.asList(
                new User(1, "user1", TYPE_1),
                new User(2, "user2", TYPE_2),
                new User(3, "user3", TYPE_1));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class User {
        private Integer userId;
        private String name;
        private int type;
    }
}