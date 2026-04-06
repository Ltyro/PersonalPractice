package com.lnstark.pp.leetcode.q100;

/**
 * https://leetcode.cn/problems/jump-game-ii/description/
 *
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月4日
 */
public class Q45 {

	int minStep = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Q45 q45 = new Q45();
		int[] arr = {2,3,0,1,4};
		int result = q45.jump(arr);
		System.out.println(result);
	}

	public int jump(int[] nums) {
		int n = nums.length;
		// 边界情况：数组长度为1，无需跳跃
		if (n == 1) {
			return 0;
		}

		int end = 0;       // 当前跳跃能到达的边界
		int maxPos = 0;    // 在当前跳跃范围内，能跳到的最远位置
		int steps = 0;     // 记录跳跃次数

		// 遍历数组（不遍历最后一个元素，到达即停止）
		for (int i = 0; i < n - 1; i++) {
			// 更新能到达的最远位置
			maxPos = Math.max(maxPos, i + nums[i]);

			// 当遍历到当前跳跃的边界时，需要进行一次新的跳跃
			if (i == end) {
				end = maxPos;  // 更新下一次跳跃的边界
				steps++;       // 跳跃次数加1

				// 提前终止：如果当前能到达的最远位置已经覆盖最后一个元素
				if (maxPos >= n - 1) {
					break;
				}
			}
		}

		return steps;
	}



}
