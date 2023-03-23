package com.fish.list;

/**
 * @author liuqi
 * @date 2023/5/29
 * <p>
 * https://leetcode.cn/leetbook/read/tencent/x52ycv/
 */
public class FindIntersectionNode {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        private int getLen(ListNode head) {
            int cnt = 0;
            while (head != null) {
                cnt++;
                head = head.next;
            }
            return cnt;
        }

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            int cnt1 = getLen(headA);
            int cnt2 = getLen(headB);
            int diff = Math.abs(cnt1 - cnt2);
            ListNode head1 = cnt1 < cnt2 ? headB : headA;
            ListNode head2 = cnt1 < cnt2 ? headA : headB;
            for (int i = 0; i < diff; i++) {
                head1 = head1.next;
            }
            while (head1 != null) {
                if (head1 == head2) {
                    return head1;
                }
                head1 = head1.next;
                head2 = head2.next;
            }
            return null;
        }
    }
}
