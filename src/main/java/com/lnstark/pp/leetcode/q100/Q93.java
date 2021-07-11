package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
 * 和 "192.168@1.1" 是 无效的 IP 地址。
 * 
 *  
 * 
 * 示例 1：
 * 
 * 输入：s = "25525511135" 输出：["255.255.11.135","255.255.111.35"] 
 * 
 * 示例 2：
 * 
 * 输入：s = "0000" 输出：["0.0.0.0"] 
 * 
 * 示例 3：
 * 
 * 输入：s = "1111" 输出：["1.1.1.1"] 
 * 
 * 示例 4：
 * 
 * 输入：s = "010010" 输出：["0.10.0.10","0.100.1.0"] 
 * 
 * 示例 5：
 * 
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 
 * 
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年12月29日
 */
public class Q93 extends Base {

	public static void main(String[] args) {
		Q93 q = new Q93();
		String s = "101023";
		List<String> result = q.restoreIpAddresses(s);
		System.out.println(result);
	}

	private static int deep = 4;
	
	public List<String> restoreIpAddresses(String s) {
		int path[] = new int[4];
		List<String> res = new ArrayList<>();
		dfs(s, s.length(), path, res, 0, 0);
		return res;
    }

	private void dfs(String s, int len, int[] path, List<String> res, int pi, int si) {
		// 返回条件
		// 25525511135
		boolean b1 = pi == deep, b2 = si == len;
		if (b1 && b2) {
			StringJoiner sj = new StringJoiner(".");
			for (int i : path)
				sj.add(i + "");
			res.add(sj.toString());
			return;
		} else if (b1 ^ b2) {
			return;
		}
		if (s.charAt(si) == '0') {
			path[pi] = 0;
			dfs(s, s.length(), path, res, pi + 1, si + 1);
		}
		// 遍历
		int seg = 0;
		for (int i = si; i < len; i++) {
			seg = seg * 10 + (s.charAt(i) - '0');
			if (seg > 0 && seg < 256) {
				path[pi] = seg;
				dfs(s, s.length(), path, res, pi + 1, i + 1);
			} else {
				return;
			}
		}
	}
	
	
	

}
