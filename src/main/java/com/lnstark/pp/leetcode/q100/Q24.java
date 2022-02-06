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
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(4);
		printNodeList(swapPairs(head));
	}

	public static ListNode swapPairs(ListNode head) {
		if(head == null)
			return null;
		if (head.next == null)
			return head;
		ListNode temp = head, pre = null, next = head.next;
		head = head.next;
		boolean odd = true;
		while (next != null) {
			if (odd) {
				if(pre != null)
					pre.next = next;
				temp.next = next.next;
				next.next = temp;
				ListNode t = temp;
				temp = next;
				next = t;
			} 
			
			pre = temp;
			temp = next;
			next = next.next;

			odd = !odd;
		}
		return head;
	}

}
