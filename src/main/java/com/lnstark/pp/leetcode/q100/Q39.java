package leetcode.q100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 所有数字（包括 target）都是正整数。 解集不能包含重复的组合。  
 * 示例 1：
 * 
 * 输入：candidates = [2,3,6,7], target = 7, 所求解集为： [ [7], [2,2,3] ]
 * 
 * 示例 2：
 * 
 * 输入：candidates = [2,3,5], target = 8, 所求解集为： [   [2,2,2,2],   [2,3,3],   [3,5] ]
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月9日
 */
public class Q39 {

	public static void main(String[] args) {
		int candidates[] = {2,3,5};
		int target = 8;
		List<List<Integer>> result = combinationSum(candidates, target);
		System.out.println(result);
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		dfs(candidates, target, new ArrayList<Integer>(), result, 0, 0);
		return result;
	}

	private static void dfs(int[] candidates, int target, List<Integer> path, List<List<Integer>> result, int begin, int sum) {
		if (sum == target) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int i = begin; i < candidates.length; i++) {
			if (sum + candidates[i] > target) {
				break;
			}
			path.add(candidates[i]);
			dfs(candidates, target, path, result, i, sum + candidates[i]);
			path.remove(path.size() - 1);
		}
	}
	
	
}
