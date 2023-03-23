package com.fish.string;

/**
 * @author liuqi
 * @date 2023/8/21
 * <p>
 * https://leetcode.cn/problems/move-pieces-to-obtain-a-string/description/
 */
public class Q2337 {
    class Solution {
        public boolean canChange(String start, String target) {
            if (start.length() != target.length()) {
                return false;
            }
            int idxS = 0;
            int idxT = 0;
            while (idxS < start.length()) {
                if (start.charAt(idxS) == '_') {
                    idxS++;
                    continue;
                }
                while (idxT < target.length() && target.charAt(idxT) == '_') {
                    idxT++;
                }
                if (idxT == target.length()) {
                    return false;
                }
                if (start.charAt(idxS) != target.charAt(idxT)) {
                    return false;
                }
                if (start.charAt(idxS) == 'R') {
                    if (idxS > idxT) {
                        return false;
                    }
                } else if (start.charAt(idxS) == 'L') {
                    if (idxS < idxT) {
                        return false;
                    }
                }
                idxS++;
                idxT++;
            }
            while (idxT < target.length()) {
                if (target.charAt(idxT) != '_') {
                    return false;
                }
                idxT++;
            }
            return true;
        }
    }
}
