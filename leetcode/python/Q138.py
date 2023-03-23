# Definition for a Node.
class Node:
    def __init__(self, x, next=None, random=None):
        self.val = int(x)
        self.next = next
        self.random = random


class Solution(object):
    def copyRandomList(self, head):
        """
        :type head: Node
        :rtype: Node
        """
        if not head:
            return None
        currO = head
        preN = None
        headN = None
        oList = []
        # 先构造新的链表，并将原链表每个节点的next指针，指向新构造的对应节点
        while currO:
            # 构造新节点，将原节点的next指针，指向新构造的节点
            currN = Node(currO.val)
            temp = currO.next
            currO.next = currN
            # 将新节点和新链表连接起来
            if not preN:
                preN = currN
                headN = currN
            else:
                preN.next = currN
                preN = currN
            # 更新循环变量
            oList.append(currO)
            currO = temp
        # 设置新构造链表的random指针
        currN = headN
        index = 0
        while currN:
            # 设置新构造节点的random指针
            currO = oList[index]
            if currO.random:
                currN.random = currO.random.next
            # 更新缓存变量
            index += 1
            currN = currN.next
        # 恢复原链表
        index = 0
        while index < len(oList) - 1:
            oList[index].next = oList[index + 1]
            index += 1
        oList[len(oList) - 1].next = None
        return headN
