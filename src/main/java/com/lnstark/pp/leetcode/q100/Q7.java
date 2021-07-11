package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 * 
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 
 * 假设环境不允许存储 64 位整数（有符号或无符号）。  
 * 
 * 示例 1：
 * 输入：x = 123 输出：321 
 * 
 * 示例 2：
 * 输入：x = -123 输出：-321 
 * 
 * 示例 3：
 * 输入：x = 120 输出：21 
 * 
 * 示例 4：
 * 输入：x = 0 输出：0
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q7 extends Base {

	public static void main(String[] args) {
		Q7 q = new Q7();
		int i = -2147483648;
//		System.out.println(-i);
		int result = q.reverse(i);
		System.out.println(result);
	}

	public int reverse(int x) {
		if (x == Integer.MIN_VALUE)
			return 0;
		StringBuilder sb = new StringBuilder();
		sb.append(x >= 0 ? x : -x);
		sb.reverse();
		Long l = Long.parseLong(x < 0 ? "-" + sb.toString() : sb.toString());
		return l > Integer.MAX_VALUE || l < Integer.MIN_VALUE ? 0 : l.intValue();
    }

}
