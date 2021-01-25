package leetcode.q100;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 
 * 示例:
 * 
 * 输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年10月9日
 */
public class Q55 {

	public static void main(String[] args) {
		int[][] l = generateMatrix(3);
		System.out.println(l);
	}

	public static int[][] generateMatrix(int n) {
		int a[][] = new int[n][n];
		int max = n * n, num = 1;
		int l = 0, r = n - 1, t = 0, b = n - 1;
		while (num <= max) {
			for (int i = l; i <= r; i++) a[t][i] = num++;
			t++;
			for (int i = t; i <= b; i++) a[i][r] = num++;
			r--;
			for (int i = r; i >= l; i--) a[b][i] = num++;
			b--;
			for (int i = b; i >= t; i--) a[i][l] = num++;
			l++;
		}
		return a;
	}

}
