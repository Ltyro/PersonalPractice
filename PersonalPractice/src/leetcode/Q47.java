package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,1,2] 输出: [ [1,1,2], [1,2,1], [2,1,1] ]
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月11日
 */
public class Q47 {

	public static void main(String[] args) {
		int[] arr = { 1, 1, 2 };
		List<List<Integer>> result = permuteUnique(arr);
		System.out.println(result);
	}

	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length < 1)
			return result;
		Arrays.sort(nums);
		boolean[] used = new boolean[nums.length];
		dfs(nums, new ArrayList<Integer>(), result, used);
		return result;
	}

	private static void dfs(int[] nums, List<Integer> path, List<List<Integer>> result, boolean[] used) {
		if (path.size() == nums.length) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int j = 0; j < nums.length; j++) {
			// 同层去重
			// 判断上次是否用过，上次没用过，则这次这个可以用
			if(j > 0 && nums[j] == nums[j - 1] && used[j - 1]) {
	            continue;
	        }
			
			if (!used[j]) {
				path.add(nums[j]);
				used[j] = true;
				dfs(nums,  path, result, used);
				used[j] = false;
				path.remove(path.size() - 1);
			}
		}
	}

}
