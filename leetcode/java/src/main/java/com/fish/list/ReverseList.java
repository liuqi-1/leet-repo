package com.fish.list;

/**
 * @author liuqi
 * @date 2023/11/1
 */
public class ReverseList {

    class Solution {

        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode first = head;
            ListNode second = head.next;
            first.next = null;
            while (true) {
                ListNode temp = second.next;
                second.next = first;
                first = second;
                second = temp;
                if (second == null) {
                    return first;
                }
            }
        }
    }
}
