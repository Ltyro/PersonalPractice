package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年11月21日
 */
public class Q83 extends Base {

	public static void main(String[] args) {
		Q83 q = new Q83();
		ListNode head = newNodeList(1, 1, 2, 2);
		ListNode newHead = q.deleteDuplicates(head);
		printNodeList(newHead);
	}


	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode temp = head;
		ListNode next = temp.next;
		while (next != null) {
			if (temp.val == next.val) {
				temp.next = next.next;
				next = next.next;
			} else {
				temp = temp.next;
				next = temp == null ? null : temp.next;
			}
		}
		return head;
	}

}
