package com.fish.fall2023.bilibili;

import java.util.Scanner;

/**
 * @author liuqi
 * @date 2023/8/29
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t1 = 0;
        int t2 = 0;
        for (int i = 0; i < n; i++) {
            int ai = in.nextInt();
            if (t1 <= t2) {
                t1 += ai;
            } else {
                t2 += ai;
            }
        }
        System.out.print(Math.max(t1, t2));
    }
}