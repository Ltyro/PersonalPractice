package leetcode.q100;

import leetcode.Base;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 
 * 说明: 1 ≤ m ≤ n ≤ 链表长度。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4 输出: 1->4->3->2->5->NULL
 * 
 * 
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月29日
 */
public class Q92 extends Base {

	public static void main(String[] args) {
		Q92 q = new Q92();
		ListNode head = q.newNodeList(1, 2, 3, 4, 5);
		ListNode result = q.reverseBetween(head, 3, 4);
		q.printNodeList(result);
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		int tm = m;
		ListNode pre = null, temp = head, next = null;
		while (--tm > 0) {
			pre = temp;
			temp = temp.next;
		}
		next = temp.next;
		ListNode rHead = pre, rEnd = temp;
		int len = n - m + 1;
		while (len-- > 0) {
			temp.next = pre;
			pre = temp;
			temp = next;
			if (next != null)
				next = next.next;
		}
		if (rHead != null) {
			rHead.next = pre;
		} else {
			head = pre;
		}
		rEnd.next = temp;
		return head;
    }

	
	
}
