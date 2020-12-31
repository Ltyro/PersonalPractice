package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * 
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums1 = [1,3], nums2 = [2] 输出：2.00000 解释：合并数组 = [1,2,3] ，中位数 2 
 * 
 * 示例 2：
 * 
 * 输入：nums1 = [1,2], nums2 = [3,4] 输出：2.50000 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5 
 * 
 * 示例 3：
 * 
 * 输入：nums1 = [0,0], nums2 = [0,0] 输出：0.00000 
 * 
 * 示例 4：
 * 
 * 输入：nums1 = [], nums2 = [1] 输出：1.00000 
 * 
 * 示例 5：
 * 
 * 输入：nums1 = [2], nums2 = [] 输出：2.00000
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q1 extends Base {

	public static void main(String[] args) {
		Q1 q = new Q1();
		int[] nums1 = {2}, nums2 = {};
		double result = q.findMedianSortedArrays(nums1, nums2);
		System.out.println(result);
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int mid = (nums1.length + nums2.length) / 2;
		int i = 0, i1 = 0, i2 = 0, r1 = -1, r2 = -1;
		while (i++ <= mid) {
			r1 = r2;
			if (i1 < nums1.length && (i2 >= nums2.length || nums1[i1] <= nums2[i2])) {
				r2 = nums1[i1++];
			} else  {
				r2 = nums2[i2++];
			} 
		}
		return (nums1.length + nums2.length) % 2 == 0 ? (r1 + r2) / 2.0 : r2;
    }

}
