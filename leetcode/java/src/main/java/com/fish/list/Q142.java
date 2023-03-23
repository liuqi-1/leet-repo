package com.fish.list;

/**
 * @author: liuqi
 * @date: 2024/3/6
 */
public class Q142 {
    /**
     * 这种题先画图，理解数学原理
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    while (slow != head) {
                        slow = slow.next;
                        head = head.next;
                    }
                    return head;
                }
            }
            return null;
        }
    }
}
