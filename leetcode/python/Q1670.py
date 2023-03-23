from collections import deque


class FrontMiddleBackQueue:
    __slots__ = 'left', 'right'

    def __init__(self):
        self.left = deque()
        self.right = deque()

    # 调整长度，保证 0 <= len(right) - len(left) <= 1
    # 从而保证可以在正中间插入删除元素
    def balance(self):
        if len(self.left) > len(self.right):
            self.right.appendleft(self.left.pop())
        elif len(self.right) > len(self.left) + 1:
            self.left.append(self.right.popleft())

    def pushFront(self, val: int) -> None:
        self.left.appendleft(val)
        self.balance()

    def pushMiddle(self, val: int) -> None:
        if len(self.left) < len(self.right):
            self.left.append(val)
        else:
            self.right.appendleft(val)

    def pushBack(self, val: int) -> None:
        self.right.append(val)
        self.balance()

    def popFront(self) -> int:
        if not self.right:  # 整个队列为空
            return -1
        val = self.left.popleft() if self.left else self.right.popleft()
        self.balance()
        return val

    def popMiddle(self) -> int:
        if not self.right:  # 整个队列为空
            return -1
        if len(self.left) == len(self.right):
            return self.left.pop()
        return self.right.popleft()

    def popBack(self) -> int:
        if not self.right:  # 整个队列为空
            return -1
        val = self.right.pop()
        self.balance()
        return val


class FrontMiddleBackQueue1:
    def __init__(self):
        self.qpre = deque()
        self.qpst = deque()

    def cntMid(self):
        if len(self.qpre) > len(self.qpst):
            self.qpst.appendleft(self.qpre.pop())
        elif len(self.qpst) > len(self.qpre) + 1:
            self.qpre.append(self.qpst.popleft())

    def pushFront(self, val: int) -> None:
        self.qpre.appendleft(val)
        self.cntMid()

    def popFront(self) -> int:
        if not self.qpre:
            return self.popMiddle()
        ret = self.qpre.popleft()
        self.cntMid()
        return ret

    def pushMiddle(self, val: int) -> None:
        if len(self.qpre) == len(self.qpst):
            self.qpst.appendleft(val)
        else:
            self.qpre.append(val)

    def popBack(self) -> int:
        if not self.qpst:
            return -1
        ret = self.qpst.pop()
        self.cntMid()
        return ret

    def pushBack(self, val: int) -> None:
        self.qpst.append(val)
        self.cntMid()

    def popMiddle(self) -> int:
        if not self.qpst:
            return -1
        if len(self.qpre) == len(self.qpst):
            return self.qpre.pop()
        return self.qpst.popleft()


obj = FrontMiddleBackQueue()
obj.pushFront(1)
obj.pushBack(2)
obj.pushMiddle(3)
obj.pushMiddle(4)

print(obj.popFront())
print(obj.popMiddle())
print(obj.popMiddle())
print(obj.popBack())
print(obj.popFront())
