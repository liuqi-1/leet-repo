package com.fish.simulate;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author liuqi
 * @date 2023/7/7
 * https://leetcode.cn/problems/time-to-cross-a-bridge/
 */
public class Q2532 {
    class Solution {
        PriorityQueue<Worker> left = new PriorityQueue<>();
        PriorityQueue<Worker> right = new PriorityQueue<>();
        PriorityQueue<Worker> pick = new PriorityQueue<>((w1, w2) -> w1.getEndPickTime() - w2.getEndPickTime());
        PriorityQueue<Worker> put = new PriorityQueue<>((w1, w2) -> w1.getEndPutTime() - w2.getEndPutTime());
        int timeNow = 0;

        public int findCrossingTime(int n, int k, int[][] time) {
            for (int i = 0; i < k; i++) {
                left.add(new Worker(i, time[i]));
            }
            while (true) {
                while (!put.isEmpty() && put.peek().getEndPutTime() <= timeNow) {
                    Worker w = put.poll();
                    w.startTime = timeNow;
                    left.add(w);
                }
                while (!pick.isEmpty() && pick.peek().getEndPickTime() <= timeNow) {
                    Worker w = pick.poll();
                    w.startTime = timeNow;
                    right.add(w);
                }
                if (!right.isEmpty()) {
                    Worker w = right.poll();
                    timeNow += w.time[2];
                    w.startTime = timeNow;
                    put.add(w);
                    if (pick.isEmpty() && right.isEmpty() && n == 0) {
                        return timeNow;
                    }
                } else if (n > 0) {
                    if (left.isEmpty()) {
                        Worker w1 = pick.peek();
                        Worker w2 = put.peek();
                        if (w1 == null || (w2 != null && w1.getEndPickTime() > w2.getEndPutTime())) {
                            timeNow = w2.getEndPutTime();
                            w2.startTime = timeNow;
                            put.poll();
                            left.add(w2);
                        } else {
                            timeNow = w1.getEndPickTime();
                            w1.startTime = timeNow;
                            pick.poll();
                            right.add(w1);
                        }
                    } else {
                        Worker w = left.poll();
                        timeNow += w.time[0];
                        w.startTime = timeNow;
                        pick.add(w);
                        n--;
                    }
                } else {
                    Worker w = pick.poll();
                    timeNow = w.getEndPickTime();
                    w.startTime = timeNow;
                    right.add(w);
                }
            }
        }

        class Worker implements Comparable {
            public int idx;// worker的单位
            public int[] time;// worker的四个时间单位
            public int effective = 0;
            public int startTime = 0;//开始干活的时间

            public Worker(int idx, int[] time) {
                this.idx = idx;
                this.time = time;
                effective = time[0] + time[2];
            }

            public int getEndPutTime() {
                return startTime + time[3];
            }

            public int getEndPickTime() {
                return startTime + time[1];
            }

            // 比谁效率高，效率高的在最小优先队列中优先级低
            @Override
            public int compareTo(Object o) {
                Worker worker = (Worker) o;
                if (effective != worker.effective) {
                    return worker.effective - effective;
                } else {
                    return worker.idx - idx;
                }
            }
        }
    }

    @Test
    public void test() {
        System.out.println(new Solution().findCrossingTime(3, 2, new int[][]{{1, 9, 1, 8}, {10, 10, 10, 10}}));
    }
}
