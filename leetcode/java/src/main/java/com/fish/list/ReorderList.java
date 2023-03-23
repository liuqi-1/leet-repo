package com.fish.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/7/31
 * <p>
 * https://leetcode.cn/problems/reorder-list/
 */
public class ReorderList {
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
     * 递归解法
     */
    class Solution {
        public ListNode left;

        public void help(ListNode right) {
            if (right.next == null) {
                if (left == right || left.next == right) {
                    right.next = null;
                    left = null;
                } else {
                    right.next = left.next;
                    left.next = right;
                    left = right.next;
                }
                return;
            }
            help(right.next);
            if (left == null) {
                return;
            }
            if (left == right || left.next == right) {
                right.next = null;
                left = null;
                return;
            }
            right.next = left.next;
            left.next = right;
            left = right.next;
        }

        public void reorderList(ListNode head) {
            left = head;
            help(head);
        }
    }

    /**
     * 常规数组解法
     */
    class Solution1 {
        public void reorderList(ListNode head) {
            List<ListNode> list = new ArrayList<>();
            ListNode temp = head;
            while (temp != null) {
                ListNode t = temp.next;
                list.add(temp);
                temp.next = null;
                temp = t;
            }
            int left = 0;
            int right = list.size() - 1;
            while (left < right) {
                list.get(left++).next = list.get(right);
                if (right == left) {
                    return;
                }
                list.get(right--).next = list.get(left);
            }
        }
    }
}
