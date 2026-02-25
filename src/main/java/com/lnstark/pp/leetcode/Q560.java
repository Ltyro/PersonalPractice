package com.lnstark.pp.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 * 子数组是数组中元素的连续非空序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 * @author zaoji_lai
 * @since 2026/2/25 14:46
 */
public class Q560 extends Base {

    public static void main(String[] args) {
        Q560 q = new Q560();
        int[] nums = { 1,-1,0 };
        int k = 0;
        int ret = q.subarraySum(nums, k);
        System.out.println(ret);
    }

    public int subarraySum(int[] nums, int k) {
        // 哈希表：key=前缀和，value=该前缀和出现的次数
        Map<Integer, Integer> prefixCount = new HashMap<>();
        // 初始状态：前缀和0出现1次（对应preSum[0]=0）
        prefixCount.put(0, 1);

        int currentSum = 0; // 当前累计的前缀和
        int result = 0;     // 最终结果（和为k的子数组个数）

        for (int num : nums) {
            currentSum += num; // 计算当前前缀和

            // 核心：查找有多少个前缀和等于 currentSum - k
            int target = currentSum - k;
            if (prefixCount.containsKey(target)) {
                result += prefixCount.get(target);
            }

            // 将当前前缀和存入哈希表（更新出现次数）
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }

        return result;
    }

}
