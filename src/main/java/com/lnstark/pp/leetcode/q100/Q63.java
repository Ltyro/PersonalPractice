package com.lnstark.pp.leetcode.q100;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 
 * 
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 
 * 说明：m 和 n 的值均不超过 100。
 * 
 * 示例 1:
 * 
 * 输入: [   [0,0,0],   [0,1,0],   [0,0,0] ] 输出: 2 解释: 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径： 1. 向右 -> 向右 -> 向下 -> 向下 2. 向下 -> 向下 -> 向右 -> 向右
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年11月2日
 */
public class Q63 {

	public static void main(String[] args) {
		new Q63().run();
	}

	public void run() {
		int[][] obstacleGrid = {{1, 0}}; 
		int n = uniquePathsWithObstacles(obstacleGrid);
		System.out.println(n);
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		
		// 第一列都是1
		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0] == 1)
				break;
			dp[i][0] = 1;
		}
		
		// 第一行都是1
		for (int i = 0; i < n; i++) {
			if (obstacleGrid[0][i] == 1)
				break;
			dp[0][i] = 1;
		}

		// 这里是递推公式
		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
		return dp[m - 1][n - 1];

	}

}
