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
		ListNode tmp = null;
		ListNode root = null;
		// 进位
		int jw = 0;
		// 余数
		int ys = 0;
		while (l1 != null || l2 != null) {
			int n1 = l1 == null ? 0 : l1.val;
			int n2 = l2 == null ? 0 : l2.val;
			int sum = n1 + n2 + jw;
			jw = sum / 10;
			ys = sum % 10;
			if (tmp == null) {
				tmp = new ListNode(ys);
				root = tmp;
			} else {
				tmp.next = new ListNode(ys);
				tmp = tmp.next;
			}
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
		if (jw > 0) {
			tmp.next = new ListNode(jw);
		}
		return root;
	}

}
