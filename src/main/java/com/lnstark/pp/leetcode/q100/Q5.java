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
		String result = q.longestPalindrome("cbbd");
		System.out.println(result);
	}

	public String longestPalindrome(String s) {
		int left = 0, right = 0;
		boolean[] dp = new boolean[s.length()];
		for (int r = 0; r < s.length(); r++) {
			for (int l = 0; l <= r; l++) {

				if (l == r) {
					dp[l] = true;
				} else if (l + 1 == r) {
					dp[l] = s.charAt(l) == s.charAt(r);
				} else {
					dp[l] = dp[l + 1] && s.charAt(l) == s.charAt(r);
				}
				if (dp[l] && r - l > right - left) {
					left = l;
					right = r;
				}
			}
		}
		return s.substring(left, right + 1);
	}

}
