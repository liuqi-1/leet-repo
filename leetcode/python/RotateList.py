"""
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
from Tencent 链表突击
"""
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        if not head or k == 0 or not head.next:
            return head
        temp = head
        len = 1
        while temp.next:
            temp = temp.next
            len += 1
        newHead = oldHead = head
        beforeNewHead = None
        for i in range(0, (k - 1) % len):
            head = head.next if head.next else oldHead
        while head.next:
            beforeNewHead = newHead
            head = head.next
            newHead = newHead.next
        if newHead != oldHead:
            beforeNewHead.next = None
            head.next = oldHead
        return newHead


head = ListNode()
head.val = 1

head.next = ListNode()
head.next.val = 2

ret = Solution().rotateRight(head, 2)
while ret is not None:
    print(ret.val)
    ret = ret.next
