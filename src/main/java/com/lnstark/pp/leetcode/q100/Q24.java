package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 两两交换链表中的节点
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q24 extends Base {

	public static void main(String[] args) {
		ListNode head = newNodeList(1, 2, 3, 4);
//		head.next.next.next.next = new ListNode(4);
		printNodeList(swapPairs(head));
	}

	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode p1 = new ListNode(0);
		p1.next = head;
		head = head.next;
		while (p1.next != null && p1.next.next != null) {
			ListNode p2 = p1.next;
			ListNode p3 = p2.next;
			p1.next = p3;
			p2.next = p3.next;
			p3.next = p2;
			p1 = p1.next.next;
		}
		return head;
	}

}
