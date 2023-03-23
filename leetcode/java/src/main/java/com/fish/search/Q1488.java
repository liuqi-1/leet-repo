package com.fish.search;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/10/13
 * https://leetcode.cn/problems/avoid-flood-in-the-city/?envType=daily-question&envId=2023-10-13
 */
public class Q1488 {

    class Solution1 {
        public int[] avoidFlood(int[] rains) {
            int[] ans = new int[rains.length];
            Arrays.fill(ans, 1);
            TreeSet<Integer> st = new TreeSet<Integer>();
            Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
            for (int i = 0; i < rains.length; ++i) {
                if (rains[i] == 0) {
                    st.add(i);
                } else {
                    ans[i] = -1;
                    if (mp.containsKey(rains[i])) {
                        Integer it = st.ceiling(mp.get(rains[i]));
                        if (it == null) {
                            return new int[0];
                        }
                        ans[it] = rains[i];
                        st.remove(it);
                    }
                    mp.put(rains[i], i);
                }
            }
            return ans;
        }
    }


    class Solution {
        public int[] avoidFlood(int[] rains) {
            Map<Integer, Integer> rainedSeas = new HashMap<>();
            ArrayList<int[]> list = new ArrayList<>();
            for (int i = 0; i < rains.length; i++) {
                if (rains[i] == 0) {
                    continue;
                }
                if (rainedSeas.containsKey(rains[i])) {
                    list.add(new int[]{rainedSeas.get(rains[i]), i});
                    rainedSeas.remove(rains[i]);
                } else {
                    rainedSeas.put(rains[i], i);
                }
            }
            Collections.sort(list, Comparator.comparingInt(l -> l[1]));
            Set<Integer> rained = new HashSet<>();
            int[] ans = new int[rains.length];
            Arrays.fill(ans, 1);
            int index = -1;
            for (int idx = 0; idx < rains.length; idx++) {
                if (rains[idx] != 0) {
                    ans[idx] = -1;
                    if (rained.contains(rains[idx])) {
                        return new int[0];
                    }
                    rained.add(rains[idx]);
                } else {
                    int oldIndex = index;
                    while (++index < list.size()) {
                        if (list.get(index)[1] <= idx) {
                            oldIndex = idx;
                            continue;
                        }
                        if (!rained.contains(rains[list.get(index)[1]])) {
                            continue;
                        }
                        ans[idx] = rains[list.get(index)[1]];
                        rained.remove(rains[list.get(index)[1]]);
                        break;
                    }
                    index = oldIndex;
                }
            }
            return ans;
        }
    }
}
