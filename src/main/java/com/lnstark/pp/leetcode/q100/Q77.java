package com.lnstark.pp.leetcode.q100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月8日
 */
public class Q77 {

	public static void main(String[] args) {
		List<List<Integer>> result = combine(4, 2);
		System.out.println(result);
	}

	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();
		if (k < 1 || n < k)
			return result;
		dfs(n, k, 1, new ArrayList<Integer>(), result);
		return result;
	}

	/**
	 * 回溯法
	 * 
	 * @param n
	 * @param k
	 * @param i
	 * @param arrayList
	 * @param result
	 */
	private static void dfs(int n, int k, int i, List<Integer> path, List<List<Integer>> result) {
		if (path.size() == k) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int j = i; j <= n; j++) {
			path.add(j);
			dfs(n, k, j + 1, path, result);
			path.remove(path.size() - 1);
		}
	}

}
