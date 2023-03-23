package com.fish.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/8/15
 * <p>
 * https://leetcode.cn/problems/find-and-replace-in-string/
 */
public class Q833 {
    /**
     * 还得是官方啊
     */
    class Solution {
        public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
            int n = s.length(), m = indices.length;

            List<Integer> ops = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                ops.add(i);
            }
            ops.sort((i, j) -> indices[i] - indices[j]);

            StringBuilder ans = new StringBuilder();
            int pt = 0;
            for (int i = 0; i < n;) {
                while (pt < m && indices[ops.get(pt)] < i) {
                    pt++;
                }
                boolean succeed = false;
                while (pt < m && indices[ops.get(pt)] == i) {
                    if (s.substring(i, Math.min(i + sources[ops.get(pt)].length(), n)).equals(sources[ops.get(pt)])) {
                        succeed = true;
                        break;
                    }
                    pt++;
                }
                if (succeed) {
                    ans.append(targets[ops.get(pt)]);
                    i += sources[ops.get(pt)].length();
                } else {
                    ans.append(s.charAt(i));
                    i++;
                }
            }
            return ans.toString();
        }
    }

    class Solution1 {
        public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
            List<Integer> replacer = new ArrayList<>();
            for (int i = 0; i < indices.length; i++) {
                replacer.add(i);
            }
            Collections.sort(replacer, Comparator.comparingInt(i -> indices[i]));
            int offset = 0;
            StringBuilder result = new StringBuilder(s);
            for (int k = 0; k < replacer.size(); k++) {
                int idx = indices[replacer.get(k)] + offset;
                String source = sources[replacer.get(k)];
                String target = targets[replacer.get(k)];
                boolean contains = true;
                for (int i = 0; i < source.length(); i++) {
                    if (idx + i >= result.length() || result.charAt(idx + i) != source.charAt(i)) {
                        contains = false;
                        break;
                    }
                }
                if (contains) {
                    result.delete(idx, idx + source.length());
                    result.insert(idx, target);
                    offset += target.length() - source.length();
                }
            }
            return result.toString();
        }
    }
}
