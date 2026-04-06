package com.lnstark.pp.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

        public TreeNode(int... vals) {
            if (vals == null || vals.length == 0) return;
            this.val = vals[0];
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(this);
            int i = 1;
            while (!queue.isEmpty() && i < vals.length) {
                TreeNode node = queue.poll();
                // 左孩子
                if (i < vals.length && vals[i] != Integer.MIN_VALUE) {
                    node.left = new TreeNode(vals[i]);
                    queue.offer(node.left);
                }
                i++;
                // 右孩子
                if (i < vals.length && vals[i] != Integer.MIN_VALUE) {
                    node.right = new TreeNode(vals[i]);
                    queue.offer(node.right);
                }
                i++;
            }
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
        System.out.println();
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

    protected static void print(Object o) {
        System.out.print(o);
    }
}
