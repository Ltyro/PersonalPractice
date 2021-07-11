package com.lnstark.pp.leetcode.q100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 判断你是否能够到达最后一个位置。
 * 
 * 示例 1:
 * 
 * 输入: [2,3,1,1,4] 输出: true 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3步到达最后一个位置。
 * 示例 2:
 * 
 * 输入: [3,2,1,0,4] 输出: false 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0
 * ，所以你永远不可能到达最后一个位置。
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年10月9日
 */
public class Q59 {

	public static void main(String[] args) {
//		int[] nums = { 2, 3, 1, 1, 4 };
		int[] nums = {3,2,1,0,4};
		boolean l = canJump(nums);
		System.out.println(l);
	}

	public static boolean canJump(int[] nums) {
		int k = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > k) {
				return false;
			}
			k = Math.max(k, i + nums[i]);
		}
		return true;

	}

}
