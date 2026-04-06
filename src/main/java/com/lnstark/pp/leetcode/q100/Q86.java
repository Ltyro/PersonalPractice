package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * https://leetcode.cn/problems/partition-list/
 *
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月21日
 */
public class Q86 extends Base {

	public static void main(String[] args) {
		Q86 q = new Q86();
		ListNode head = newNodeList(1,4,3,2,5,2);
		ListNode newHead = q.partition(head, 1);
		printNodeList(newHead);
	}


	public ListNode partition(ListNode head, int x) {
		ListNode left = null, l = null,
				right = null, r = null;
		ListNode tmp = head;
		while (tmp != null) {
			if (tmp.val < x) {
				if (left == null) {
					left = new ListNode(tmp.val);
					l = left;
				} else {
					l.next = new ListNode(tmp.val);
					l = l.next;
				}
			} else {
				if (right == null) {
					right = new ListNode(tmp.val);
					r = right;
				} else {
					r.next = new ListNode(tmp.val);
					r = r.next;
				}
			}
			tmp = tmp.next;
		}
		if (left != null) {
			l.next = right;
			return left;
		}
		return right;
	}

}
