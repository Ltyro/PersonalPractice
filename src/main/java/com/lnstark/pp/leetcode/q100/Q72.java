package com.lnstark.pp.leetcode.q100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/edit-distance
 *
 * @author Zaoji_Lai
 * @date 2026年3月8日
 * @since 1.0
 */
public class Q72 {

    public static void main(String[] args) {
        int d = new Q72().minDistance("", "a");
        System.out.println(d);
    }

    /**
     *
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                boolean eq = word1.charAt(i - 1) == word2.charAt(j - 1);
                if (eq) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }


}
