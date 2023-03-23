package com.fish.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/8/3
 *
 * https://leetcode.cn/problems/remove-comments/submissions/452853648/
 */
public class Q722 {
    class Solution {
        boolean multiLine = false;
        private String BLANK_STRING = "";

        private String removeComment(String line) {
            if (multiLine == true) {
                if (!line.contains("*/")) {
                    return BLANK_STRING;
                } else {
                    int index = line.indexOf("*/");
                    String newLine = line.substring(index + 2, line.length());
                    System.out.println(newLine);
                    multiLine = false;
                    String result = removeComment(newLine);
                    return result;
                }
            }
            int indexSingle = line.indexOf("//");
            int indexMulti = line.indexOf("/*");
            if (indexSingle != -1 && (indexMulti == -1 || indexSingle < indexMulti)) {
                int index = line.indexOf("//");
                line = line.substring(0, index);
                return line;
            }
            if (indexMulti != -1 && (indexSingle == -1 || indexMulti < indexSingle)) {
                multiLine = true;
                int index = line.indexOf("/*");
                String remainsBefore = line.substring(0, index);
                String remainsAfter = removeComment(line.substring(index + 2, line.length()));
                return remainsBefore + remainsAfter;
            }
            return line;
        }

        public List<String> removeComments(String[] source) {
            List<String> result = new ArrayList<>();
            int idx = 0;
            while (idx < source.length) {
                String line = removeComment(source[idx++]);
                while (multiLine) {
                    line += removeComment(source[idx++]);
                }
                if (!line.equals(BLANK_STRING)) {
                    result.add(line);
                }
            }
            return result;
        }
    }
}
