package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * Sqrt(x)
 * https://leetcode-cn.com/problems/sqrtx/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年11月26日
 */
public class Q69 {

	public static void main(String[] args) {
		new Q69().run();
	}

	public void run() {
		Q69 q = new Q69();
		System.out.println(q.mySqrt(Integer.MAX_VALUE - 1));
	}

	public int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		if (x <= 3) {
			return 1;
		}
		int i = 2;
		int j = x / 2;
		int mid = (i + j) / 2;
		while (j - i > 1) {
			if (mid <= x / mid) {
				i = mid;
			} else {
				j = mid;
			}
			mid = (i + j) / 2;
		}
		return i;
	}

}
