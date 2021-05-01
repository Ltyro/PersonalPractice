package leetcode.q100;

import leetcode.Base;

/**
 * Q35 https://leetcode-cn.com/problems/search-insert-position/
 *
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年4月24日
 */
public class Q35 extends Base {

	public static void main(String[] args) {
		Q35 q = new Q35();
		int[] nums1 = {1, 3}, nums2 = {};
		int result = q.searchInsert(nums1, 2);
		System.out.println(result);
	}

	public int searchInsert(int[] nums, int target) {
		int len = nums.length;
		if (target <= nums[0])
			return 0;
		if (target > nums[len - 1])
			return len;
		int l = 0;
		int r = len - 1;
		int midIndex = (r + l) / 2;
		while (l < r) {
			if (target < nums[midIndex]) {
				r = midIndex - 1;
			} else if (target > nums[midIndex]) {
				l = midIndex + 1;
			} else {
				return midIndex;
			}
			midIndex = (r + l) / 2;
		}
		return target > nums[l] ? l + 1 : l;
	}

}
