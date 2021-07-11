package com.lnstark.pp.leetcode.q100;

/**
 * Q41 缺失的第一个正数
 * https://leetcode-cn.com/problems/first-missing-positive/
 *
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年5月8日
 */
public class Q41 {

	
    public static void main(String[] args) {
        Q41 q = new Q41();
        int ret = q.firstMissingPositive(new int[]{-2, 2, 4, 1, 1});
        System.out.println(ret);

    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] < 1)
                nums[i] = n + 1;
        for (int i = 0; i < nums.length; i++) {
            int trueValue = Math.abs(nums[i]);
            if (trueValue <= n && nums[trueValue - 1] > 0)
                nums[trueValue - 1] *= -1;
        }
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > 0)
                return i + 1;
        return n + 1;
    }


}
