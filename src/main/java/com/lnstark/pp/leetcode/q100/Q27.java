package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * https://leetcode.cn/problems/remove-element/
 *
 * by Lnstark
 * 2026/3/15
 */
public class Q27 extends Base {
    public static void main(String[] args) {
        Q27 q = new Q27();
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int k = q.removeElement(nums, val);
        System.out.println(k);
    }

    public int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[idx] = nums[i];
                idx++;
            }
        }
        return idx;
    }

}
