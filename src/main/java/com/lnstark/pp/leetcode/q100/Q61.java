package leetcode.q100;

import leetcode.Base;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->4->5->NULL, k = 2 
 * 输出: 4->5->1->2->3->NULL 
 * 解释: 
 * 向右旋转 1 步: 5->1->2->3->4->NULL 
 * 向右旋转 2 步: 4->5->1->2->3->NULL 
 * 
 * 示例 2:
 * 
 * 输入: 0->1->2->NULL, k = 4 
 * 输出: 2->0->1->NULL 
 * 解释: 
 * 向右旋转 1 步: 2->0->1->NULL 
 * 向右旋转 2 步: 1->2->0->NULL 
 * 向右旋转 3 步: 0->1->2->NULL 
 * 向右旋转 4 步: 2->0->1->NULL
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年10月9日
 */
public class Q61 extends Base {

	public static void main(String[] args) {
		new Q61().run();
	}

	public void run() {
		ListNode n1 = newNodeList(0, 1, 2);
		ListNode afterRotate = rotateRight(n1, 4);
		printNodeList(afterRotate);
	}
	
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null) return null;
		int length = 1;
		ListNode temp = head;
		while ((temp = temp.next) != null) length++;
		int realK = k % length;
		if (realK == 0) return head;
		int i = 0;
		temp = head;
		ListNode newHead = null;
		while (temp.next != null) {
			i++;
			if (i == length - realK) {
				ListNode pre = temp;
				newHead = temp.next;
				temp = temp.next;
				pre.next = null;
			} else {
				temp = temp.next;
			}
		}
		temp.next = head;
		return newHead;
	}
	
}
