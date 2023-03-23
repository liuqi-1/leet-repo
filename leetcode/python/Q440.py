# https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/description/

"""
todo 需要回顾 20240221
使用字典树的思想，但是并没有实际创建字典树
假设存在字典树，然后按照先序遍历寻找第k小的数字。
先序遍历的时候，先考虑当前节点一共有多少数字，如果少于k个，则遍历下一个子树，如果多余k个，则遍历当前子树的下一层。
计算每个节点数字数量的时候，按照层序遍历的思想，（last-first+1），first为x000的形式，first为x9999的形式。
first_i表示第i层最左边的数字，那么first_i+1为first_i*10
相应的，last_i+1=min(n,last_i*10+9)，最右边的数字不能超过n
"""


class Solution:
    def cnt(self, cur, n):
        res, first, last = 0, cur, cur
        while first <= n:
            res += min(last, n) - first + 1
            first *= 10
            last = last * 10 + 9
        return res

    def findKthNumber(self, n: int, k: int) -> int:
        cur = 1
        k -= 1
        while k:
            c = self.cnt(cur, n)
            if k >= c:
                cur += 1
                k -= c
            else:
                cur = cur * 10
                k -= 1
        return cur
