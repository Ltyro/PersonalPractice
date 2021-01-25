package leetcode.q100;

import java.util.HashSet;
import java.util.Set;

/**
 * 搜索二维矩阵 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3 输出：true  
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13 输出：false
 * 
 * 输入：matrix = [], target = 0 输出：false 
 * 
 * 提示：
 * 
 * m == matrix.length 
 * n == matrix[i].length 
 * 0 <= m, n <= 100 
 * -1e4 <= matrix[i][j], target <= 1e4
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年11月27日
 */
public class Q74 {

	public static void main(String[] args) {
		int[][] matrix = {{1,3,5,7}, {10,11,16,20}, {23,30,34,50}};
		boolean r = new Q74().searchMatrix(matrix, 13);
		System.out.println(r);
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) return false;
		int m = matrix.length, n = matrix[0].length;
		if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) return false;
		int i = 0;
		while(i < m && target >= matrix[i][0]) i++;
		for (int j = 0; j < n; j++) {
			if (target == matrix[i - 1][j]) {
				return true;
			}
		}
		return false;
    }
}
