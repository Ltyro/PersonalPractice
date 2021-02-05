package leetcode.q100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年1月26日
 */
public class Q14 {

	public static void main(String[] args) {
		Q14 q = new Q14();
		String[] strs = {"dog","racecar","car"};
		String ret = q.longestCommonPrefix(strs);
		System.out.println(ret);
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		int i = 0;
		boolean brk = false;;
		while (!brk) {
			Character c = null;
			for (String s : strs) {
				if (s.length() < i + 1) {
					brk = true;
					break;
				}
				if (c == null)
					c = s.charAt(i);
				else if (s.charAt(i) != c) {
					brk = true;
					break;
				}
			}
			if (!brk)
				sb.append(c);
			i++;
		}
		return sb.toString();
    }

}
