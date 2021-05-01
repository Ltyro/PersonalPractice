package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *示例：
 *二叉树：[3,9,20,null,null,15,7],
 *
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 *返回其层序遍历结果：
 *
 *[
 *  [3],
 *  [9,20],
 *  [15,7]
 *]
 *
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月30日
 */
public class Q102 extends Base {

	public static void main(String[] args) {
		Q102 q = new Q102();
		TreeNode tn = new TreeNode(3);
		tn.left = new TreeNode(9);
		tn.right = new TreeNode(20);
		tn.right.left = new TreeNode(15);
		tn.right.right = new TreeNode(7);
		List<List<Integer>> result = q.levelOrder(tn);
		System.out.println(result);
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		List<TreeNode> list = new LinkedList<>();
		list.add(root);
		while (!list.isEmpty()) {
			List<Integer> level = new ArrayList<>();
			List<TreeNode> newSt = new LinkedList<>();
			while (!list.isEmpty()) {
				TreeNode node = list.remove(0);
				level.add(node.val);
				if (node.left != null) {
					newSt.add(node.left);
				}
				if (node.right != null) {
					newSt.add(node.right);
				}
			}
			if (!level.isEmpty()) res.add(level);
			list = newSt;
		}
		return res;
    }

}
