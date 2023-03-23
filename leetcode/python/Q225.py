import collections
import queue
from queue import Queue

"""
queue.Queue
collections.deque: 双端队列

"""


class MyStack:

    def __init__(self):
        self.q1 = collections.deque()  # 逆序
        self.q2 = collections.deque()  # 一直为空

    def push(self, x: int) -> None:
        self.q2.append(x)
        while self.q1:
            self.q2.append(self.q1.popleft())
        self.q1, self.q2 = self.q2, self.q1

    def pop(self) -> int:
        return self.q1.popleft()

    def top(self) -> int:
        return self.q1[0]

    def empty(self) -> bool:
        return not self.q1


# Your MyStack object will be instantiated and called as such:
# obj = MyStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.empty()

class MyStack1:

    def __init__(self):
        self.q1 = Queue()  # 保证为空
        self.q2 = Queue()  # 保证不为空

    def push(self, x: int) -> None:
        self.q2.put(x)

    def pop(self) -> int:
        while not self.q2.empty():
            x = self.q2.get()
            if self.q2.empty():
                self.q1, self.q2 = self.q2, self.q1
                return x
            self.q1.put(x)

    def top(self) -> int:
        while not self.q2.empty():
            x = self.q2.get()
            if self.q2.empty():
                self.q1.put(x)
                self.q1, self.q2 = self.q2, self.q1
                return x
            self.q1.put(x)

    def empty(self) -> bool:
        return self.q1.empty() and self.q2.empty()

# Your MyStack object will be instantiated and called as such:
# obj = MyStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.empty()
