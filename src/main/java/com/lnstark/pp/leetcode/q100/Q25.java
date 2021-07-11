package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

/**
 * by Lnstark
 * 2021/5/1
 */
public class Q25 extends Base {
    public static void main(String[] args) {
        Q25 q = new Q25();
        ListNode head = newNodeList(1, 2, 3, 4, 5);
        head = q.reverseKGroup(head, 2);
        printNodeList(head);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
       // TODO
       return null;
    }

    /**
     * 反转子链表
     */
    private ListNode reverseSegment(ListNode head, ListNode tail) {
        return null;
    }
}
