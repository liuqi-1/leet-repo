# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    def swapPairs(self, head):
        if not head or not head.next:
            return head
        pre = head
        post = head.next
        preHead = ListNode(-1, head)
        newHead = head.next
        while pre and post:
            # 进行 swap
            pre.next = post.next
            post.next = pre
            preHead.next = post
            # 更新指针
            if not pre.next or not pre.next.next:
                return newHead
            preHead = pre
            pre = pre.next
            post = pre.next
        return newHead
