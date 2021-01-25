package leetcode.q100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: nums = [1,2,3] 输出: 
 * [ 
 * [3],   
 * [1],   
 * [2],   
 * [1,2,3],   
 * [1,3],   
 * [2,3],  
 * [1,2],   
 * [] 
 * ]
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月8日
 */
public class Q78 {

	public static void main(String[] args) {
		int nums[] = {1, 2, 3};
		List<List<Integer>> result = subsets(nums);
		System.out.println(result);
	}


	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> l = new ArrayList<>();
		dfs(nums, 0, new ArrayList<Integer>(), l);
		return l;
    }

	/**
	 * 回溯
	 * @param nums
	 * @param i
	 * @param path
	 * @param result
	 */
	private static void dfs(int[] nums, int i, List<Integer> path, List<List<Integer>> result) {
		if (i == nums.length) {
			result.add(new ArrayList<>(path));
			return;
		}
		path.add(nums[i]);
		dfs(nums, i + 1, path, result);
		path.remove(path.size() - 1);
		dfs(nums, i + 1, path, result);
	}
	
	
}
