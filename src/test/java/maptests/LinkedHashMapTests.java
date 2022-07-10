package maptests;

import maps.LinkedHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.Objects;

public class LinkedHashMapTests {
    private final LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

    @Test
    public void addInitialElement() {
        map.put(1,100);
        var result = map.contains(1) && map.get(1) == 100 && !map.isEmpty() && map.size() == 1;
        Assertions.assertTrue(result);
    }
    @Test
    public void add100Elements() {
        for(int i = 0; i < 100; i++) {
            map.put(i, i * 10);
        }
        var result = map.size();
        Assertions.assertEquals(100, result);
    }
    @Test
    public void addNullElement() {
        var result = map.put(null, 100);
        Assertions.assertFalse(result);
    }
    @Test
    public void getNullElement() {
        var result = map.get(null);
        Assertions.assertNull(result);
    }
    @Test
    public void getElementNotPresent() {
        var result = map.get(1);
        Assertions.assertNull(result);
    }
    @Test
    public void removeElement() {
        map.put(1, 10);
        map.remove(1);
        var result = map.size() == 0 && map.isEmpty() && map.get(1) == null;
        Assertions.assertTrue(result);
    }
    @Test
    public void removeNullElement() {
        var result = map.remove(null);
        Assertions.assertFalse(result);
    }
    @Test
    public void removeElementNotPresent() {
        var result = map.remove(1);
        Assertions.assertFalse(result);
    }
    @Test
    public void containsOnNullElement() {
        var result = map.contains(null);
        Assertions.assertFalse(result);
    }
    @Test
    public void containsOnElementNotPresent() {
        var result = map.contains(1);
        Assertions.assertFalse(result);
    }
    @Test
    public void containsOn50ElementMap() {
        for(int i = 0; i < 50; i++) {
            map.put(i, i * 10);
        }
        var result = map.contains(25);
        Assertions.assertTrue(result);
    }
    @Test
    public void getInsertionOrderOfElements() {
        for(int i = 0; i < 25; i++) {
            map.put(i, i * 10);
        }
        Object[] order = map.getInsertionOrder();
        var result = true;
        for(int i = 0; i < 25; i++) {
            if(!Objects.equals(order[i], i)) {
                result = false;
                break;
            }
        }
        Assertions.assertTrue(result);
    }
}
