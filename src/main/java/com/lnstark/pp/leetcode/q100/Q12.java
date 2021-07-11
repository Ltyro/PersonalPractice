package com.lnstark.pp.leetcode.q100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Remove Nth Node From End of List
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q12 {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
//		head.next.next = new ListNode(3);
//		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(5);
		int n = 2;
		head = removeNthFromEnd(head, n);
		print(head);
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode temp = head;
		int length = 1;
		while (temp.next != null) {
			temp = temp.next;
			length++;
		}
		if(n < 0 || n > length)
			return head;
		if(length == 1 && n == 1)
			return null;
		ListNode pre = null;
		temp = head;
		int i = 0;
		while (i++ + n < length) {
			pre = temp;
			temp = temp.next;
		}
		if(pre == null)
			head = temp.next;
		else
			pre.next = temp.next;
		return head;
	}

	public static void print(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
	}
	
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

}
