package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q5 extends Base {

	public static void main(String[] args) {
		Q5 q = new Q5();
		String result = q.longestPalindrome("aaaa");
		System.out.println(result);
	}

	public String longestPalindrome(String s) {
		int len = s.length();
		int begin = 0, max = 1;
		boolean[][] dp = new boolean[len][len];
		for (int i = 0; i < len; i++)
			dp[i][i] = true;
//		for (int L = 2; L <= len; L++) {
//			// 枚举左边界，左边界的上限设置可以宽松一些
//			for (int i = 0; i < len; i++) {
//				// 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
//				int j = L + i - 1;
//				// 如果右边界越界，就可以退出当前循环
//				if (j >= len) {
//					break;
//				}
//
//				if (s.charAt(i) != s.charAt(j)) {
//					dp[i][j] = false;
//				} else {
//					if (j - i < 3) {
//						dp[i][j] = true;
//					} else {
//						dp[i][j] = dp[i + 1][j - 1];
//					}
//				}
//
//				// 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
//				if (dp[i][j] && j - i + 1 > max) {
//					max = j - i + 1;
//					begin = i;
//				}
//			}
//		}

		for (int step = 1; step < len; step++) {
			for (int i = 0; i < len; i++) {
				int j = i + step;
				if (j >= len) break;
				if (s.charAt(i) != s.charAt(j)) {
					dp[i][j] = false;
				} else if (step < 3) {
					dp[i][j] = true;
				} else {
					dp[i][j] = dp[i + 1][j - 1];
				}

				if (dp[i][j] && step + 1 > max) {
					max = step + 1;
					begin = i;
				}
			}
		}
		return s.substring(begin, begin + max);
	}

}
