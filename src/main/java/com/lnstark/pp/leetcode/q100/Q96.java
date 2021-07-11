package leetcode.q100;

import leetcode.Base;

/**
 * 	给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *  
 *  示例:
 *  
 *  输入: 3
 *  输出: 5
 *  解释:
 *  给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *  
 *     1         3     3      2      1
 *      \       /     /      / \      \
 *       3     2     1      1   3      2
 *      /     /       \                 \
 *     2     1         2                 3
 *
 * 
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月30日
 */
public class Q96 extends Base {

	public static void main(String[] args) {
		Q96 q = new Q96();
		int result = q.numTrees(4);
		System.out.println(result);
	}

	public int numTrees(int n) {
		if (n == 0) return 0;
		int dp[] = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] += dp[j - 1] * dp[i - j];
			}
			
		}
		return dp[n];
    }


	
	
	
}
