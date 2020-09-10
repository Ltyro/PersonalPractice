package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3] 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月10日
 */
public class Q46 {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		List<List<Integer>> result = permute(arr);
		System.out.println(result);
	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length < 1)
			return result;
		dfs(nums, 0, new ArrayList<Integer>(), result);
		return result;
    }
	
	private static void dfs(int[] nums, int begin, List<Integer> path, List<List<Integer>> result) {
		if (begin == nums.length) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int j = begin; j < nums.length; j++) {
			path.add(nums[j]);
			dfs(nums, j + 1, path, result);
			path.remove(path.size() - 1);
		}
	}
	

}
