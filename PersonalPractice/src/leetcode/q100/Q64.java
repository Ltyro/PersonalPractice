package leetcode.q100;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 
 * 说明：每次只能向下或者向右移动一步。
 * 
 * 示例:
 * 
 * 输入: [   
 * [1,3,1], 
 * [1,5,1], 
 * [4,2,1] 
 * ] 
 * 输出: 7 
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年11月2日
 */
public class Q64 {

	public static void main(String[] args) {
		new Q64().run();
	}

	public void run() {
		int[][] obstacleGrid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int n = minPathSum(obstacleGrid);
		System.out.println(n);
	}

	public int minPathSum(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] wpath = new int[m][n];
		wpath[0][0] = grid[0][0]; 
		for (int i = 1; i < m; i++) wpath[i][0] = wpath[i - 1][0] + grid[i][0];
		for (int i = 1; i < n; i++) wpath[0][i] = wpath[0][i - 1] + grid[0][i];
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				int x = wpath[i - 1][j];
				int y = wpath[i][j - 1];
				wpath[i][j] = (x > y ? y : x) + grid[i][j];

			}
		}
		return wpath[m - 1][n - 1];
	}

}
