package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * 23. 合并K个升序链表
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年1月27日
 */
public class Q23 extends Base {

	public static void main(String[] args) {
    	Q23 q = new Q23();
    	ListNode l1 = q.newNodeList(1, 4, 5);
    	ListNode l2 = q.newNodeList(1, 3, 4);
    	ListNode l3 = q.newNodeList(2, 6);
    	ListNode ret = q.mergeKLists(new ListNode[]{l1, l2, l3});
        q.printNodeList(ret);

    }

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		ListNode ret = null;
		for (ListNode l : lists) {
			ret = mergeTwoLists (l, ret);
		}
		return ret;
    }
	
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null) {
    		return l2;
    	} 
    	if (l2 == null) {
    		return l1;
    	}
    	if (l1.val < l2.val) {
    		l1.next = mergeTwoLists(l1.next, l2);
    		return l1;
    	}
    	l2.next = mergeTwoLists(l2.next, l1);
    	return l2;
    }


	

}
