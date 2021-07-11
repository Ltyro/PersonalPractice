package com.lnstark.pp.leetcode.q100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 
 * 将图像顺时针旋转 90 度。
 * 
 * 说明：
 * 
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 
 * 示例 1:
 * 
 * 给定 matrix = 
 * [ 
 *   [1,2,3],
 *   [4,5,6], 
 *   [7,8,9] 
 * ],
 * 
 * 原地旋转输入矩阵，使其变为: 
 * [ 
 *   [7,4,1], 
 *   [8,5,2], 
 *   [9,6,3] 
 * ] 
 * 
 * 示例 2:
 * 
 * 给定 matrix = 
 * [ 
 * [ 5, 1, 9, 11], 
 * [ 2, 4, 8,10], 
 * [13, 3, 6, 7], 
 * [15, 14, 12, 16] 
 * ],
 * 
 * 原地旋转输入矩阵，使其变为: 
 * [ 
 * [15,13, 2, 5], 
 * [14, 3, 4, 1], 
 * [12, 6, 8, 9], 
 * [16, 7,10,11] 
 * ]
 * 
 * (0, 0) -> (n-1, 0) -> (n-1, n-1) -> (0, n-1)
 * (i, 0) -> (n-1, i) -> (n-1-i, n-1) -> (0, n-1-i)
 * (i, j) -> (n-1-j, i) -> (n-1-i, n-1-j) -> (j, n-1-i)
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月11日
 */
public class Q48 {

	public static void main(String[] args) {
//		int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] arr = {{5, 1, 9, 11}, {2, 4, 8,10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
		rotate(arr);
		System.out.println(arr);
	}

	
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		// 圈数
		for (int j = 0; j < n / 2; j++) {
			for (int i = j; i < n - j - 1; i++) {
				int temp = matrix[j][i];
				matrix[j][i] = matrix[n - 1 - i][j];
				matrix[n - 1 - i][j] = matrix[n - 1 - j][n - 1 - i];
				matrix[n - 1 - j][n - 1 - i] = matrix[i][n - 1 - j];
				matrix[i][n - 1 - j] = temp;
			}
		}
    }

}
