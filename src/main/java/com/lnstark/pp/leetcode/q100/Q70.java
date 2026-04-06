package com.lnstark.pp.leetcode.q100;

/**
 *
 * https://leetcode.cn/problems/climbing-stairs
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月7日
 */
public class Q70 {

	public static void main(String[] args) {
		new Q70().run();
	}

	public void run() {
		Q70 q = new Q70();
		System.out.println(q.climbStairs(4));
	}

	public int climbStairs(int n) {
		if (n == 1) return 1;
		if (n == 2) return 2;
		int[] dp = new int[n];
		dp[0] = 1;
		dp[1] = 2;
		for (int i = 2; i < n; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		return dp[n - 1];
	}

}
