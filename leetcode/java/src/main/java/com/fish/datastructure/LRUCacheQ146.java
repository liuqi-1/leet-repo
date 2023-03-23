package com.fish.datastructure;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author liuqi
 * @date 2023/8/30
 * https://leetcode.cn/problems/lru-cache/
 */
public class LRUCacheQ146 {
    class LRUCache {
        class Node {
            int key;
            int value;
            Node pre;
            Node next;
        }

        Map<Integer, Node> cache;
        Node head;
        Node tail;
        int capacity;

        public LRUCache(int capacity) {
            cache = new HashMap<>(capacity);
            this.capacity = capacity;
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                moveToTail(cache.get(key));
                return cache.get(key).value;
            }
            return -1;
        }

        public void moveToTail(Node node) {
            if (node == head) {
                head = node.next;
                node.next = null;
                tail.next = node;
                node.pre = tail;
                tail = node;
                if (head == null) { // 考虑只有一个元素的极端情况
                    head = tail;
                }
            } else if (node != tail) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                node.next = null;
                node.pre = tail;
                tail.next = node;
                tail = node;
            }
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                moveToTail(cache.get(key));
                cache.get(key).value = value;
                return;
            }
            Node node = new Node();
            node.key = key;
            node.value = value;
            if (head == tail && head == null) {
                head = node;
                tail = node;
            } else {
                node.pre = tail;
                tail.next = node;
                tail = node;
            }
            if (cache.size() >= capacity) {
                cache.remove(head.key);
                Node temp = head;
                head = head.next;
                temp.next = null;
            }
            cache.put(key, tail);
        }
    }

    class LRUCache1 {

        Map<Integer, Integer> cache;
        Map<Integer, Integer> timeCntMap;
        Deque<Integer> deque;
        int capacity = 0;

        public LRUCache1(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>(capacity);
            timeCntMap = new HashMap<>(capacity);
            deque = new LinkedList<>();
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                deque.addLast(key);
                timeCntMap.put(key, timeCntMap.get(key) + 1);
                return cache.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                timeCntMap.put(key, timeCntMap.get(key) + 1);
                deque.addLast(key);
                cache.put(key, value);
                return;
            }
            if (cache.size() < capacity) {
                cache.put(key, value);
                timeCntMap.put(key, 1);
                deque.addLast(key);
                return;
            }
            while (!deque.isEmpty()) {
                int oldKey = deque.pollFirst();
                if (timeCntMap.get(oldKey) == 1) {
                    timeCntMap.remove(oldKey);
                    cache.remove(oldKey);
                    cache.put(key, value);
                    timeCntMap.put(key, 1);
                    deque.addLast(key);
                    break;
                } else {
                    timeCntMap.put(oldKey, timeCntMap.get(oldKey) - 1);
                }
            }
        }
    }

    @Test
    public void test() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.get(2);
        lRUCache.put(2, 6);
        lRUCache.get(1);
        lRUCache.put(1, 5);
        lRUCache.put(1, 2);
        lRUCache.get(1);
        lRUCache.get(2);
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
