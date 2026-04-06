package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/
 *
 * by Lnstark
 * 2026/3/15
 */
public class Q31 extends Base {
    public static void main(String[] args) {
        Q31 q = new Q31();
        int[] nums = {1,2,3};
        q.nextPermutation(nums);
        printArray(nums);
    }

    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;
        int start = 0;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                start = i + 1;
                break;
            }
        }
        if (start > 0) {
            for (int j = nums.length - 1; j >= start; j--) {
                if (nums[j] > nums[start - 1]) {
                    int tmp = nums[start - 1];
                    nums[start - 1] = nums[j];
                    nums[j] = tmp;
                    break;
                }
            }
        }

        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

}
