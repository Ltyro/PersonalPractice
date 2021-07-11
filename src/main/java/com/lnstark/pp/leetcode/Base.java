package com.lnstark.pp.leetcode;

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

	public static ListNode newNodeList(int... vs) {
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

	public static void printNodeList(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			print(temp.val + " ");
			temp = temp.next;
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				print(matrix[i][j] + " ");
			}
			print("\n");
		}
	}

	public static void printMatrix(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				print(matrix[i][j] + " ");
			}
			print("\n");
		}
	}

	public static void printArray(int[] nums) {
		print(Arrays.toString(nums) + "\n");
	}

	private static void print(Object o) {
		System.out.print(o);
	}
}
