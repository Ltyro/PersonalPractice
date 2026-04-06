package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 	https://leetcode.cn/problems/same-tree/description/
 * 
 *
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年4月6日
 */
public class Q100 extends Base {

	public static void main(String[] args) {
		Q100 q = new Q100();
		TreeNode t1 = new TreeNode(1, 3, 2);
		TreeNode t2 = new TreeNode(1, 2, 3);
		boolean result = q.isSameTree(t1, t2);
		System.out.println(result);
	}


	public boolean isSameTree(TreeNode t1, TreeNode t2) {
		if (!nodeEqual(t1, t2)) return false;
		if (t1 != null) {
			if (t2 == null) return false;
			if (!nodeEqual(t1.left, t2.left) || !nodeEqual(t1.right, t2.right)) return false;
			boolean leftSame = isSameTree(t1.left, t2.left);
			boolean rightSame = isSameTree(t1.right, t2.right);
			return leftSame && rightSame;
		}
		return t2 == null;
	}

	private boolean nodeEqual(TreeNode n1, TreeNode n2) {
		if (n1 == null || n2 == null) return n1 == n2;
		return n1.val == n2.val;
	}
	
}
