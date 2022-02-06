package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.HashMap;
import java.util.Map;

/**
 * 2. 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q2 extends Base {

	public static void main(String[] args) {
		Q2 q = new Q2();
		ListNode l1 = newNodeList(9);
		ListNode l2 = newNodeList(9, 9);
		printNodeList(q.addTwoNumbers(l1, l2));
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode n1 = l1, n2 = l2;
		int jw = 0, temp = 0;
		ListNode ret = null, head = null;
		while (n1 != null && n2 != null) {
			int sum = n1.val + n2.val + jw;
			jw = sum / 10;
			temp = sum % 10;
			if (head == null) {
				ret = new ListNode(temp);
				head = ret;
			} else {
				ret.next = new ListNode(temp);
				ret = ret.next;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		while (n1 != null) {
			int sum = n1.val + jw;
			jw = sum / 10;
			temp = sum % 10;
			ret.next = new ListNode(temp);
			ret = ret.next;
			n1 = n1.next;
		}
		while (n2 != null) {
			int sum = n2.val + jw;
			jw = sum / 10;
			temp = sum % 10;
			ret.next = new ListNode(temp);
			ret = ret.next;
			n2 = n2.next;
		}
		if (jw != 0) {
			ret.next = new ListNode(jw);
			ret = ret.next;
		}
		return head;

	}

}
