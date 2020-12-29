package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: [1,2,2] 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 * 
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月29日
 */
public class Q90 {

	public static void main(String[] args) {
		int board[] = { 1, 2, 2};
		List<List<Integer>> result = subsetsWithDup(board);
		System.out.println(result);
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> l = new ArrayList<>();
		dfs(nums, nums.length, l, new ArrayList<Integer>(), 0);
		return l;
	}

	private static void dfs(int[] nums, int length, List<List<Integer>> l, List<Integer> path, int i) {
		l.add(new ArrayList<>(path));
		for (int j = i; j < length; j++) {
			if (j > i && nums[j] == nums[j - 1]) {
				continue;
			}
			path.add(nums[j]);
			dfs(nums, length, l, path, j + 1);
			path.remove(path.size() - 1);
		}
	}
	
	
	

}
