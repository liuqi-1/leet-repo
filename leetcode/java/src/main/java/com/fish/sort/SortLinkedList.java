package com.fish.sort;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/6/6
 */
public class SortLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode nextW) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 自底向上的归并排序，实现O(1)的空间复杂度
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null) {
                return null;
            }

            int length = 0;
            ListNode node = head;
            while (node != null) {
                length++;
                node = node.next;
            }

            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;

            for (int subLen = 1; subLen < length; subLen <<= 1) {
                ListNode prev = dummyHead;
                ListNode curr = dummyHead.next;

                while (curr != null) {
                    // 3.1 拆分subLen长度的链表1
                    ListNode head_1 = curr;
                    for (int i = 1; i < subLen && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode head_2 = curr.next;
                    curr.next = null;
                    curr = head_2;
                    for (int i = 1; i < subLen && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode next = null;
                    if (curr != null) {
                        next = curr.next;
                        curr.next = null;
                    }
                    ListNode merged = mergeTwoLists(head_1, head_2);
                    prev.next = merged;
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    curr = next;
                }
            }
            // 返回新排好序的链
            return dummyHead.next;
        }


        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                } else {
                    curr.next = l2;
                    l2 = l2.next;
                }
                curr = curr.next;
            }
            if (l1 == null) {
                curr.next = l2;
            }
            if (l2 == null) {
                curr.next = l1;
            }
            return dummy.next;
        }
    }

    class Solution1 {
        public ListNode sort(ListNode node, int len) {
            if (node == null || len == 1) {
                return node;
            }
            ListNode node1 = node;
            for (int i = 0; i < len / 2 - 1; i++) {
                node = node.next;
            }
            ListNode node2 = node.next;
            node.next = null;
            node1 = sort(node1, len / 2);
            node2 = sort(node2, len - len / 2);
            return merge(node1, node2);
        }

        private ListNode merge(ListNode node1, ListNode node2) {
            ListNode res = new ListNode();
            ListNode cnt = res;
            while (node1 != null && node2 != null) {
                if (node1.val > node2.val) {
                    cnt.next = node2;
                    cnt = cnt.next;
                    node2 = node2.next;
                } else {
                    cnt.next = node1;
                    cnt = cnt.next;
                    node1 = node1.next;
                }
            }
            if (node1 != null) {
                cnt.next = node1;
            }
            if (node2 != null) {
                cnt.next = node2;
            }
            return res.next;
        }

        public ListNode sortList(ListNode head) {
            if (head == null) {
                return null;
            }
            int len = 1;
            ListNode backup = head;
            while (head.next != null) {
                head = head.next;
                len++;
            }
            return sort(backup, len);
        }
    }

    @Test
    public void test() {
        ListNode node = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null))));
        new Solution().sortList(node);
    }
}
