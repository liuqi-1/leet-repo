package com.fish.math;

/**
 * @author liuqi
 * @date 2023/6/25
 * <p>
 * https://leetcode.cn/problems/circle-and-rectangle-overlapping/
 */
public class Q1401 {
    class Solution {
        private boolean check(int x, int y, int r, int cX, int cY) {
            return Math.pow(x - cX, 2) + Math.pow(y - cY, 2) <= r * r;
        }

        public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
            // 矩形完全包裹圆（直接判断圆心是否在矩形内部）
            if (xCenter >= x1 && xCenter <= x2 && yCenter >= y1 && yCenter <= y2) {
                return true;
            }
            // 圆形完全包裹矩形
            if (check(xCenter, yCenter, radius, x1, y1) || check(xCenter, yCenter, radius, x1, y2) || check(xCenter, yCenter, radius, x2, y1) || check(xCenter, yCenter, radius, x2, y2)) {
                return true;
            }
            // 有交点
            for (int x_ = x1; x_ <= x2; x_++) {
                for (int y_ = y1; y_ <= y2; y_++) {
                    if (check(xCenter, yCenter, radius, x_, y_)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class Solution1 {
        public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
            /* 圆心在矩形内部 */
            if (x1 <= xCenter && xCenter <= x2 && y1 <= yCenter && yCenter <= y2) {
                return true;
            }
            /* 圆心在矩形上部 */
            if (x1 <= xCenter && xCenter <= x2 && y2 <= yCenter && yCenter <= y2 + radius) {
                return true;
            }
            /* 圆心在矩形下部 */
            if (x1 <= xCenter && xCenter <= x2 && y1 - radius <= yCenter && yCenter <= y1) {
                return true;
            }
            /* 圆心在矩形左部 */
            if (x1 - radius <= xCenter && xCenter <= x1 && y1 <= yCenter && yCenter <= y2) {
                return true;
            }
            /* 圆心在矩形右部 */
            if (x2 <= xCenter && xCenter <= x2 + radius && y1 <= yCenter && yCenter <= y2) {
                return true;
            }
            /* 矩形左上角 */
            if (distance(xCenter, yCenter, x1, y2) <= radius * radius) {
                return true;
            }
            /* 矩形左下角 */
            if (distance(xCenter, yCenter, x1, y1) <= radius * radius) {
                return true;
            }
            /* 矩形右上角 */
            if (distance(xCenter, yCenter, x2, y2) <= radius * radius) {
                return true;
            }
            /* 矩形右下角 */
            if (distance(xCenter, yCenter, x1, y2) <= radius * radius) {
                return true;
            }
            /* 无交点 */
            return false;
        }

        public long distance(int ux, int uy, int vx, int vy) {
            return (long) Math.pow(ux - vx, 2) + (long) Math.pow(uy - vy, 2);
        }
    }

    // 作者：力扣官方题解
    // 链接：https://leetcode.cn/problems/circle-and-rectangle-overlapping/solutions/2293443/yuan-he-ju-xing-shi-fou-you-zhong-die-by-zlbk/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}

