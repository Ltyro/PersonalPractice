package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 	给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *  
 *  假设一个二叉搜索树具有如下特征：
 *  
 *  节点的左子树只包含小于当前节点的数。
 *  节点的右子树只包含大于当前节点的数。
 *  所有左子树和右子树自身必须也是二叉搜索树。
 *  示例 1:
 *  
 *  输入:
 *      2
 *     / \
 *    1   3
 *  输出: true
 *  示例 2:
 *  
 *  输入:
 *      5
 *     / \
 *    1   4
 *       / \
 *      3   6
 *  输出: false
 *  解释: 输入为: [5,1,4,null,null,3,6]。
 *       根节点的值为 5 ，但是其右子节点值为 4 。
 *  
 *
 * 
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月30日
 */
public class Q98 extends Base {

	public static void main(String[] args) {
		Q98 q = new Q98();
		TreeNode tn = new TreeNode(5);
		tn.left = new TreeNode(4);
		tn.right = new TreeNode(6);
		tn.right.left = new TreeNode(3);
		tn.right.right = new TreeNode(7);
		boolean result = q.isValidBST(tn);
		System.out.println(result);
	}

	private long pre = Long.MIN_VALUE;
	
	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		if (!isValidBST(root.left)) return false;
		if (root.val <= pre) return false;
		pre = root.val;
		return isValidBST(root.right);
    }


	
	
	
}
