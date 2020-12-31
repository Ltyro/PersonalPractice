package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 	给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 *	示例：
 *	
 *	输入：3
 *	输出：
 *	[
 *	  [1,null,3,2],
 *	  [3,2,null,1],
 *	  [3,1,null,null,2],
 *	  [2,1,3],
 *	  [1,null,2,null,3]
 *	]
 *	解释：
 *	以上的输出对应以下 5 种不同结构的二叉搜索树：
 *	
 *	   1         3     3      2      1
 *	    \       /     /      / \      \
 *	     3     2     1      1   3      2
 *	    /     /       \                 \
 *	   2     1         2                 3
 * 
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月30日
 */
public class Q95 extends Base {

	public static void main(String[] args) {
		Q95 q = new Q95();
		List<TreeNode> result = q.generateTrees(3);
		System.out.println(result);
	}

	public List<TreeNode> generateTrees(int n) {
		List<TreeNode> res = new ArrayList<>();
		if (n == 0) return res;
		res = generateTrees(1, n);
		return res;
    }

	private List<TreeNode> generateTrees(int l, int r) {
		List<TreeNode> allTrees = new ArrayList<>();
		if (l > r) {
			allTrees.add(null);
			return allTrees;
		}
		for (int i = l; i <= r; i++) {
			List<TreeNode> ltree = generateTrees(l, i - 1);
			List<TreeNode> rtree = generateTrees(i + 1, r);
			for (TreeNode lt : ltree) {
				for (TreeNode rt : rtree) {
					TreeNode n = new TreeNode(i);
					n.left = lt;
					n.right = rt;
					allTrees.add(n);
				}
			}
		}
		return allTrees;
	}

	
	
	
}
