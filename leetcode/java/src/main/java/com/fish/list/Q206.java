package com.fish.list;

/**
 * @author liuqi
 * @date 2023/8/30
 * <p>
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class Q206 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode now = head;
            ListNode after = now.next;
            now.next = null;
            while (after != null) {
                ListNode temp = after.next;
                after.next = now;
                now = after;
                after = temp;
            }
            return now;
        }
    }
}
