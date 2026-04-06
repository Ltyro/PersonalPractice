package com.lnstark.pp.leetcode.q100;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年4月6日
 */
public class Q20 {

	private Map<Character, Character> pair = Map.of(
			'(', ')', '[', ']', '{', '}'
	);

    public static void main(String[] args) {
    	Q20 q = new Q20();
    	boolean ret = q.isValid("([])");
        System.out.println(ret);

    }

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (Character c : s.toCharArray()) {
			if (isLeft(c)) {
				stack.push(c);
			} else if (stack.isEmpty() || !isPair(stack.pop(), c)){
				return false;
			}
		}
		return stack.isEmpty();
	}

	private boolean isLeft(Character c) {
		return Set.of('(', '[', '{').contains(c);
	}

	private boolean isPair(Character left, Character right) {
		return right.equals(pair.get(left));
	}
}
