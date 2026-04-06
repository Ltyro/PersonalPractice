package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.Stack;

/**
 * 	https://leetcode.cn/problems/interleaving-string/
 *  
 *
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月21日
 */
public class Q97 extends Base {

	public static void main(String[] args) {
		Q97 q = new Q97();
		boolean result = q.isInterleave("aaa", "bbb", "bbbbbb");
		System.out.println(result);
	}

	public boolean isInterleave(String s1, String s2, String s3) {
		if (s3.length() != s1.length() + s2.length()) return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		if (s1.isEmpty()) return s2.equals(s3);
		if (s2.isEmpty()) return s1.equals(s3);
		dp[0][0] = true;

		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i > 0) {
					dp[i][j] |= dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
				}
				if (j > 0) {
					dp[i][j] |= dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
				}
			}
		}

		return dp[s1.length()][s2.length()];
	}



}
