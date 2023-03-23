class DLNode:
    def __init__(self, key=0, value=0):
        self.key = key
        self.value = value
        self.pre = None
        self.next = None


class LRUCache(object):
    def __init__(self, capacity):
        self.capacity = capacity
        self.head = DLNode()
        self.tail = DLNode()
        self.table = {}
        self.head.next = self.tail
        self.tail.pre = self.head

    def move_to_head(self, node):
        # 从原来的位置删除
        node.pre.next = node.next
        node.next.pre = node.pre
        # 插入新的位置
        self.add_to_head(node)

    def add_to_head(self, node):
        node.next = self.head.next
        node.pre = self.head
        node.next.pre = node
        self.head.next = node

    def remove_tail(self):
        tailNode = self.tail.pre
        tailNode.pre.next = self.tail
        self.tail.pre = tailNode.pre
        return tailNode

    def get(self, key):
        if key not in self.table:
            return -1
        node = self.table[key]
        self.move_to_head(node)
        return node.value

    def put(self, key, value):
        # 如果已经存在这样的key，将更新value，并将其挪到头部
        if key in self.table:
            node = self.table[key]
            node.value = value
            self.move_to_head(node)
            return
        # 创建新节点，并插入到链表头部
        node = DLNode(key, value)
        self.add_to_head(node)
        self.table[key] = node
        # 如果超出容量，删除尾部节点
        if len(self.table) > self.capacity:
            tailNode = self.remove_tail()
            del self.table[tailNode.key]


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
