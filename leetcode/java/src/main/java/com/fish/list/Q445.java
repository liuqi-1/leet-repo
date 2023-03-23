package com.fish.list;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/7/3
 * <p>
 * https://leetcode.cn/problems/add-two-numbers-ii/submissions/443780335/
 */
public class Q445 {

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

    /**
     * 解法1：反转链表，然后两数相加
     * 解法2：结合栈实现
     */
    class Solution {
        private ListNode addTwo(ListNode l1, ListNode l2) {
            ListNode result = l1;
            int sum = 0, cnt = 0;
            while (l2 != null) {
                sum = l1.val + l2.val + cnt;
                l1.val = sum % 10;
                cnt = sum / 10;
                if (l1.next == null && (l2.next != null || cnt != 0)) {
                    l1.next = new ListNode();
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            while (cnt != 0) {
                sum = l1.val + cnt;
                l1.val = sum % 10;
                cnt = sum / 10;
                if (l1.next == null && cnt != 0) {
                    l1.next = new ListNode();
                }
                l1 = l1.next;
            }
            return result;
        }

        private ListNode reverseNode(ListNode l) {
            if (l.next == null) {
                return l;
            }
            ListNode pre = l;
            ListNode now = l.next;
            ListNode post = l.next.next;
            pre.next = null;
            while (true) {
                if (post == null) {
                    now.next = pre;
                    return now;
                }
                now.next = pre;
                pre = now;
                now = post;
                post = post.next;
            }
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            l1 = reverseNode(l1);
            l2 = reverseNode(l2);
            return reverseNode(addTwo(l1, l2));
        }
    }

    @Test
    public void test() {
        new Solution().addTwoNumbers(new ListNode(4), new ListNode(6));
    }
}
