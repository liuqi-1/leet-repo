from typing import List


# https://leetcode.cn/problems/escape-the-ghosts/description/
class Solution:
    def escapeGhosts(self, ghosts: List[List[int]], target: List[int]) -> bool:
        def m_dis(point1, point2):
            return abs(point1[0] - point2[0]) + abs(point1[1] - point2[1])

        min = abs(target[0]) + abs(target[1])
        return all(m_dis(target, ghost) > min for ghost in ghosts)
