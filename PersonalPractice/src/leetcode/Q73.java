package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 
 * 示例 1:
 * 
 * 输入: [   
 * 	[1,1,1],   
 * 	[1,0,1],   
 * 	[1,1,1] 
 * ] 
 * 
 * 输出: [   
 * 	[1,0,1],   
 * 	[0,0,0],   
 * 	[1,0,1]
 * ] 
 * 
 * 示例 2:
 * 
 * 输入: [   
 * 	[0,1,2,0],   
 * 	[3,4,5,2],   
 * 	[1,3,1,5] 
 * ] 
 * 输出: [   
 * 	[0,0,0,0],   
 * 	[0,4,5,0],
 *  [0,3,1,0] 
 * ] 
 * 
 * 进阶:
 * 
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n)的额外空间，但这仍然不是最好的解决方案。 
 * 你能想出一个常数空间的解决方案吗？
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年11月27日
 */
public class Q73 extends Base {

	public static void main(String[] args) {
//		int[][] matrix = {{1,1,1}, {1,0,1}, {1,1,1}};
		int[][] matrix = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
		new Q73().setZeroes(matrix);
		printMatrix(matrix);
	}

	/**
	 * O(m + n)
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		if (matrix.length == 0) return;
		int m = matrix.length, n = matrix[0].length;
		Set<Integer> mset = new HashSet<>(m), nset = new HashSet<>(n);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					mset.add(i);
					nset.add(j);
				}
			}
		}
		for (Integer mi : mset) {
			for (int i = 0; i < n; i++) {
				matrix[mi][i] = 0;
			}
		}
		for (Integer ni : nset) {
			for (int i = 0; i < m; i++) {
				matrix[i][ni] = 0;
			}
		}
    }
}
