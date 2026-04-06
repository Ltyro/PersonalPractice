package com.lnstark.pp.leetcode.q100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/description/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月13日
 */
public class Q15 {

	public static void main(String[] args) {
        int[] input = {-2,0,1,1,2};
		System.out.println(new Q15().threeSum(input));
	}

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = nums.length - 1;
            while (k > j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    ret.add(List.of(nums[i], nums[j], nums[k]));
                    do j++;
                    while (k > j && nums[j] == nums[j - 1]);
                    do k--;
                    while (k > j && nums[k] == nums[k + 1]);
                } else if (sum > 0) {
                    do k--;
                    while (k > j && nums[k] == nums[k + 1]);
                } else {
                    do j++;
                    while (k > j && nums[j] == nums[j - 1]);
                }
            }
        }
        return ret;
    }

	

}
