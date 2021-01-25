package leetcode.q100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 * 示例 1:
 * 
 * 输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] 
 * 
 * 输出: [1,2,3,6,9,8,7,4,5] 
 * 
 * 示例 2:
 * 
 * 输入: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10,11,12] ] 
 * 
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月17日
 */
public class Q54 {

	public static void main(String[] args) {
//		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}};
		int[][] matrix = {};
		List<Integer> l = spiralOrder(matrix);
		System.out.println(l);
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix.length == 0) {
			return result;
		}
		int m = matrix.length, n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		int temp = 0, i = 0, j = 0, dir = 0;
		int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		while (temp++ < m * n) {
			result.add(matrix[i][j]);
			visited[i][j] = true;
			int nexti = i + directions[dir][0];
			int nextj = j + directions[dir][1];
			if (nexti == m || nexti == -1 || nextj == n || nextj == -1 || visited[nexti][nextj]) {
				dir = ++dir % 4;
			}
			i += directions[dir][0];
			j += directions[dir][1];
			
		}
		
		return result;
	}

}
