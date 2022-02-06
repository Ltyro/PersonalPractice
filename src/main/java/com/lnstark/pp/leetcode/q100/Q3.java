package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.HashMap;
import java.util.Map;

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
		System.out.println(q.lengthOfLongestSubstring("abcabcbb"));
	}

	public int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> map = new HashMap<>();
		int max = 0, left = 0;
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (map.containsKey(c)) {
				left = Math.max(left, map.get(c) + 1);
			}
			map.put(c, i);
			max = Math.max(max, i - left + 1);
		}
		return max;
	}

}
