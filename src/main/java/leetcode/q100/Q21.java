package leetcode.q100;

import leetcode.Base;

/**
 * 21. 合并两个有序链表
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年1月27日
 */
public class Q21 extends Base {

	
    public static void main(String[] args) {
    	Q21 q = new Q21();
    	ListNode l1 = q.constructNodeList(-9, 3);
    	ListNode l2 = q.constructNodeList(5, 7);
    	ListNode ret = q.mergeTwoLists(l1, l2);
        q.printNodeList(ret);

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
