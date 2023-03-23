package com.fish.fall2023.weipei;

/**
 * @author liuqi
 * @date 2023/8/30
 */
public class Q2 {
    public String[] result = new String[]{"invalid", "low", "medium", "high", "perfect"};

    public String solution(String pwd) {
        if (pwd.length() < 8 || pwd.length() > 24) {
            return result[0];
        }
        int[] tag = new int[4];
        for (char c : pwd.toCharArray()) {
            if (c >= '0' && c <= '9') {
                tag[0] = 1;
            } else if (c >= 'a' && c <= 'z') {
                tag[1] = 1;
            } else if (c >= 'A' && c <= 'Z') {
                tag[2] = 1;
            } else {
                tag[3] = 1;
            }
        }
        int cnt = 0;
        for (int t : tag) {
            if (t == 1) {
                cnt++;
            }
        }
        return result[cnt];
    }

    public static void main(String[] args) {
        System.out.println(new Q2().solution("Lqwhiahisd12"));
    }
}
