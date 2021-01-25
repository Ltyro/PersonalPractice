package leetcode.q100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import leetcode.Base;
import leetcode.Base.TreeNode;

/**
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * 
 * 输入：root = [1,null,2,3] 输出：[1,3,2]
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月30日
 */
public class Q94 extends Base {

	public static void main(String[] args) {
		Q94 q = new Q94();
		TreeNode tn = new TreeNode(1);
		tn.right = new TreeNode(2);
		tn.right.left = new TreeNode(3);
		List<Integer> result = q.inorderTraversal(tn);
		System.out.println(result);
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> l = new ArrayList<>();
		traverse(root, l);
		return l;
	}

	private void traverse(TreeNode root, List<Integer> l) {
		if (root == null)
			return;
		traverse(root.left, l);
		l.add(root.val);
		traverse(root.right, l);
	}

}
