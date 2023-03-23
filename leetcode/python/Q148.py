# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


# 自顶向下排序，时间复杂度O(nlgn)，空间复杂度O(lgn)
class Solution1(object):
    # 进行二分归并排序
    def sort(self, head):
        # 至少有两个节点才需要进行排序
        if not head or not head.next:
            return head
        # 找到链表的切分点
        preSlow = None
        fast = head
        slow = head
        while fast and fast.next:
            preSlow = slow
            slow = slow.next
            fast = fast.next.next
        # 递归进行排序
        preSlow.next = None
        left = self.sort(head)
        right = self.sort(slow)
        return self.merge(left, right)

    # 合并有序链表
    def merge(self, head1, head2):
        dummyHead = ListNode()
        curr = dummyHead
        while head1 and head2:
            if head2.val <= head1.val:
                curr.next = head2
                head2 = head2.next
            else:
                curr.next = head1
                head1 = head1.next
            curr = curr.next
        curr.next = head1 if head1 else head2
        return dummyHead.next

    def sortList(self, head):
        """
        :type head: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
        return self.sort(head)


# 自底向上，时间复杂度O(nlgn)，空间复杂度O(1)
class Solution(object):
    def findNextHead(self, head, sublen):
        temp = head
        for i in range(sublen - 1):
            if not temp:
                break
            temp = temp.next
        if not temp:
            newHead = None
        else:
            newHead = temp.next
            temp.next = None
        return newHead

    def merge(self, head1, head2):
        dummyHead = ListNode()
        curr = dummyHead
        lastNode = None
        while head1 or head2:
            if not head1 or (head2 and head2.val <= head1.val):
                curr.next = head2
                curr = curr.next
                lastNode = head2
                head2 = head2.next
            else:
                curr.next = head1
                curr = curr.next
                lastNode = head1
                head1 = head1.next
        return dummyHead.next, lastNode

    def sortList(self, head):
        """
        :type head: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
        # 如果链表为空，则直接返回
        if not head:
            return None
        # 计算链表的长度
        len = 0
        temp = head
        while temp:
            len += 1
            temp = temp.next
        # 进行归并排序
        subLen = 1
        dummyHead = ListNode(0, head)
        while subLen < len:
            # 哨兵节点
            head = dummyHead.next
            dummyHead.next = None
            curr = dummyHead
            while head:
                # 第一个子链表
                head1 = head
                # 第二个子链表
                head2 = self.findNextHead(head1, subLen)
                if not head2:
                    curr.next = head1
                    break
                # 遍历找到新的head
                head = self.findNextHead(head2, subLen)
                # 合并有序链表
                mergedHead, LastNode = self.merge(head1, head2)
                # Append到已排序的子链表中
                curr.next = mergedHead
                curr = LastNode
            # 更新head
            subLen *= 2
        return dummyHead.next
