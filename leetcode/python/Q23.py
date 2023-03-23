# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


# 两两合并
class Solution1(object):
    def mergedList(self, head1, head2):
        dummyHead = ListNode()
        curr = dummyHead
        while head1 and head2:
            if head1.val <= head2.val:
                curr.next = head1
                head1 = head1.next
            else:
                curr.next = head2
                head2 = head2.next
            curr = curr.next
        curr.next = head1 if head1 else head2
        return dummyHead.next

    def mergeKLists(self, lists):
        """
        :type lists: List[Optional[ListNode]]
        :rtype: Optional[ListNode]
        """
        step = 1
        size = len(lists)
        while step < size:
            # 链表两两合并
            curr = 0
            while curr < size:
                # 合并两个链表
                head1 = lists[curr]
                if curr + step >= size:
                    break
                head2 = lists[curr + step]
                newHead = self.mergedList(head1, head2)
                lists[curr] = newHead
                # 更新curr
                curr += 2 * step
            step *= 2
        return lists[0] if size > 0 else None


import heapq
from itertools import count


# 小顶堆
class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[Optional[ListNode]]
        :rtype: Optional[ListNode]
        """
        heap = []
        dummyHead = ListNode()
        counter = count()
        curr = dummyHead
        for head in lists:
            if head:
                heapq.heappush(heap, (head.val, next(counter), head))
        while len(heap) > 0:
            _, _, head = heapq.heappop(heap)
            nextNode = head.next
            head.next = None
            curr.next = head
            curr = curr.next
            if nextNode:
                heapq.heappush(heap, (nextNode.val, next(counter), nextNode))
        return dummyHead.next
