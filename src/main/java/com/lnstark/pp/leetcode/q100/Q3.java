package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q3 extends Base {

	public static void main(String[] args) {
		Q3 q = new Q3();
		System.out.println(q.lengthOfLongestSubstring("abcbecbb"));
	}

	public int lengthOfLongestSubstring(String s) {
		int maxLen = 0;
		Set<Character> charSet = new HashSet<>();
		for (int l = 0, r = 0 ; r < s.length() ; r++) {
			//当前考虑的元素
			while (l <= r && check(charSet, s.charAt(r))) {//区间[left,right]不符合题意
				//扩展左边界
				charSet.remove(s.charAt(l));
				l++;
			}
			//区间[left,right]符合题意，统计相关信息
			charSet.add(s.charAt(r));
			if (maxLen < charSet.size())
				maxLen = charSet.size();
		}
		return maxLen;
	}

	private boolean check(Set<Character> charSet, Character c) {
		return charSet.contains(c);
	}

}
