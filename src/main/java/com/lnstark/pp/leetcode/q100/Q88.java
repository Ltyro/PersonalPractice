package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 合并两个有序数组
 * https://leetcode.cn/problems/merge-sorted-array/description/
 *
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年11月21日
 */
public class Q88 extends Base {

	public static void main(String[] args) {
		Q88 q = new Q88();
		int[] nums1 = {4,0,0,0,0,0}, nums2 = {1,2,3,5,6};
		int n = nums2.length;
		int m = nums1.length - n;
		q.merge(nums1, m, nums2, n);
		printArray(nums1);
	}


	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (n == 0) return;
		int[] nums = new int[m + n];
		int i = 0, j = 0;
		while (i < m || j < n) {
			if (nums1[i] < nums2[j] && i < m) {
				nums[i + j] = nums1[i];
				i++;
			} else {
				nums[i + j] = nums2[j];
				j++;
			}
			if (i == m) {
				System.arraycopy(nums2, j, nums, i + j, n - j);
				break;
			}
			if (j == n) {
				System.arraycopy(nums1, i, nums, i + j, m - i);
				break;
			}
		}
		System.arraycopy(nums, 0, nums1, 0, m + n);
	}

}
