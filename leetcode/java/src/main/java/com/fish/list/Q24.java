package com.fish.list;

/**
 * @author: liuqi
 * @date: 2024/3/6
 */
public class Q24 {
    /**
     * 递归解法
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = head.next;
            head.next = swapPairs(newHead.next);
            newHead.next = head;
            return newHead;
        }
    }


    /**
     * 迭代解法
     */
    class Solution1 {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode preHead = new ListNode(-1, head);
            ListNode ret = head.next;
            while (head != null && head.next != null) {
                ListNode temp = head.next;
                head.next = temp.next;
                temp.next = head;
                preHead.next = temp;
                preHead = head;
                head = head.next;
            }
            return ret;
        }
    }
}
