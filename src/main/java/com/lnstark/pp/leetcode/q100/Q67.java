package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 二进制求和
 * https://leetcode-cn.com/problems/add-binary/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年11月21日
 */
public class Q67 extends Base {

	public static void main(String[] args) {
		new Q67().run();
	}

	public void run() {
		Q67 q = new Q67();
		String a = "1010", b = "1011";
		String s = q.addBinary(a, b);
		System.out.println(s);
	}

	public String addBinary(String a, String b) {
		StringBuilder result = new StringBuilder();
		int jw = 0;
		int i = a.length() - 1;
		int j = b.length() - 1;
		while (i >= 0 || j >= 0 || jw > 0) {
			int an = i >= 0 ? (a.charAt(i) - '0') : 0;
			int bn = j >= 0 ? b.charAt(j) - '0' : 0;
			int sum = an + bn + jw;
			result.insert(0, sum % 2);
			jw = sum / 2;
			i--;
			j--;
		}
		return result.toString();
	}

}
