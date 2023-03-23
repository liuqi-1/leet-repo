package com.fish.list;

/**
 * @author liuqi
 * @date 2023/5/29
 * <p>
 * 有一个单链表的head，我们想删除它其中的一个节点node。
 * <p>
 * 给你一个需要删除的节点node。你将无法访问第一个节点head。
 * <p>
 * 链表的所有值都是 唯一的，并且保证给定的节点node不是链表中的最后一个节点。
 * <p>
 * 删除给定的节点。注意，删除节点并不是指从内存中删除它。这里的意思是：
 * <p>
 * 给定节点的值不应该存在于链表中。
 * 链表中的节点数应该减少 1。
 * node前面的所有值顺序相同。
 * node后面的所有值顺序相同。
 * <p>
 * 作者：LeetCode
 * 链接：https://leetcode.cn/leetbook/read/tencent/x5ns1j/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class DeleteGivenNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public void deleteNode(ListNode node) {
            while (node.next.next != null) {
                node.val = node.next.val;
                node = node.next;
            }
            node.val = node.next.val;
            node.next = null;
        }
    }
}
