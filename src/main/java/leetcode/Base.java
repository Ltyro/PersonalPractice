package leetcode;

import java.util.Arrays;

public class Base {

	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return val + "";
		}
	}

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode() {
		}

		public TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return val + " ";
		}
		
	}

	public ListNode constructNodeList(int... vs) {
		if (vs.length == 0)
			return null;
		ListNode temp, head;
		temp = head = new ListNode(vs[0]);
		for (int i = 1; i < vs.length; i++) {
			temp.next = new ListNode(vs[i]);
			temp = temp.next;
		}
		return head;
	}

	public void printNodeList(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void printArray(int[] nums) {
		// for (int i = 0; i < nums.length; i++) {
		// System.out.print(i);
		// if (i < nums.length - 1)
		// System.out.print(", ");
		// }
		System.out.println(Arrays.toString(nums));
	}
}
