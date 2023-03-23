package com.fish.string;

/**
 * @author liuqi
 * @date 2023/11/23
 * <p>
 * https://leetcode.cn/problems/html-entity-parser/?envType=daily-question&envId=2023-11-23
 */
public class Q1410 {
    class Solution {
        public String entityParser(String text) {
            text = text.replace("&quot;", "\"");
            text = text.replace("&apos;", "\'");
            text = text.replace("&gt;", ">");
            text = text.replace("&lt;", "<");
            text = text.replace("&frasl;", "/");
            text = text.replace("&amp;", "&");
            return text;
        }
    }
}
