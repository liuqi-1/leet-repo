package com.fish.tree;

/**
 * 题目描述：
 * 设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组 words 中的一个字符串。
 * 例如，words = ["abc", "xyz"] 且字符流中逐个依次加入 4 个字符 'a'、'x'、'y' 和 'z' ，
 * 你所设计的算法应当可以检测到 "axyz" 的后缀 "xyz" 与 words 中的字符串 "xyz" 匹配。
 * 按下述要求实现 StreamChecker 类：
 *     StreamChecker(String[] words) ：构造函数，用字符串数组 words 初始化数据结构。
 *     boolean query(char letter)：从字符流中接收一个新字符，如果字符流中的任一非空后缀能匹配 words 中的某一字符串，返回 true ；否则，返回 false。
 * 方法：前缀树 +暴力解法
 * @author liuqi
 * @date 2023/3/24
 */
public class Q1032 {
    /*暴力方法*/
    class StreamChecker1 {
        String[] words;
        int maxLen = 0;
        StringBuilder suffix = new StringBuilder();

        public StreamChecker1(String[] words) {
            this.words = words;
            for (String word : words) {
                maxLen = Math.max(word.length(),maxLen);
            }
        }

        public boolean query(char letter) {
            suffix.append(letter);
            if(suffix.length()>maxLen){
                suffix.deleteCharAt(0);
            }
            for (String word : words) {
                if(suffix.toString().endsWith(word)){
                    return true;
                }
            }
            return false;
        }
    }

    /*前缀树*/
    class Trie {
        Trie[] children = new Trie[26];
        boolean isEnd = false;

        public void insert(String w) {
            Trie node = this;
            for (int i = w.length() - 1; i >= 0; --i) {
                int idx = w.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean query(StringBuilder s) {
            Trie node = this;
            for (int i = s.length() - 1, j = 0; i >= 0 && j < 201; --i, ++j) {
                int idx = s.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    return false;
                }
                node = node.children[idx];
                if (node.isEnd) {
                    return true;
                }
            }
            return false;
        }
    }

    class StreamChecker {
        private StringBuilder sb = new StringBuilder();
        private Trie trie = new Trie();

        public StreamChecker(String[] words) {
            for (String w : words) {
                trie.insert(w);
            }
        }

        public boolean query(char letter) {
            sb.append(letter);
            return trie.query(sb);
        }
    }
}
