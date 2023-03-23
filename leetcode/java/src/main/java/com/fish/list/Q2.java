package com.fish.list;

/**
 * @author liuqi
 * @date 2023/7/2
 * <p>
 * https://leetcode.cn/problems/add-two-numbers/
 */
public class Q2 {
    class ListNode {
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = l1;
            int r = 0;// 进位
            int sum = 0;
            while (l2 != null) {
                sum = l1.val + l2.val + r;
                l1.val = sum % 10;
                r = sum / 10;
                if (l1.next == null && (l2.next != null || r != 0)) {
                    l1.next = new ListNode(0);
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            while (r != 0) {
                sum = l1.val + r;
                l1.val = sum % 10;
                r = sum / 10;
                if (l1.next == null && r != 0) {
                    l1.next = new ListNode();
                }
                l1 = l1.next;
            }
            return result;
        }
    }
}
