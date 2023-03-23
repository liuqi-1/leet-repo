package com.fish.list;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/11/20
 */
public class Q25 {
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode first = head;
            ListNode last = head;
            ListNode pre = new ListNode();
            ListNode newHead = null;
            pre.next = head;
            while (true) {
                // 往后找k个
                for (int i = 1; i < k; i++) {
                    if (last == null) {
                        break;
                    }
                    last = last.next;
                }
                if (last != null && newHead == null) {
                    newHead = last;
                }
                if (last == null) {
                    return newHead == null ? head : newHead;
                }
                // 循环k-1次
                ListNode oldF = first;
                for (int i = 1; i < k; i++) {
                    ListNode temp = first.next;
                    first.next = last.next;
                    last.next = first;
                    first = temp;
                }
                pre.next = last;
                pre = oldF;
                first = last = oldF.next;
            }
        }
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode t = head;
        for (int i = 0; i < 4; i++) {
            t.next = new ListNode(i + 2);
            t = t.next;
        }
        new Solution().reverseKGroup(head, 2);
    }
}
