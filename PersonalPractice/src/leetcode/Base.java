package leetcode;


public class Base {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public ListNode constructNodeList(int... vs) {
		if (vs.length == 0) return null;
		ListNode temp, head;
		temp = head = new ListNode(vs[0]);
		for (int i = 1; i < vs.length; i++) {
			temp.next = new ListNode(vs[i]);
			temp = temp.next;
		}
		return head;
	}
	
	public void printNodeList(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
	}
	
}
