# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    def reverseList(self, head, k):
        if not head:
            return head, None
        # 往后遍历，判断是否足够len个节点
        cnt = 0
        temp = head
        while cnt < k:
            cnt += 1
            if not temp:
                return head, None
            temp = temp.next
        # 开始进行翻转
        pre = head
        curr = head.next
        pre.next = None
        while curr != temp:
            tt = curr.next
            curr.next = pre
            pre = curr
            curr = tt
        return pre, temp

    def reverseKGroup(self, head, k):
        now = head
        newHead = None
        preLast = ListNode(0, None)
        while now:
            # 尝试翻转后面的连续k个节点，返回翻转后的头节点
            preLast.next, nextNow = self.reverseList(now, k)
            if not newHead:
                newHead = preLast.next
            # 如果翻转完毕，则直接返回结果
            if not nextNow:
                return newHead
            # 更新指针
            preLast = now
            now = nextNow
        return newHead
