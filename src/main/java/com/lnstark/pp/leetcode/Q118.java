package com.lnstark.pp.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/pascals-triangle
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年01月21日
 */
public class Q118 extends Base {

	public static void main(String[] args) {
		Q118 q = new Q118();
		System.out.println(q.generate(5));
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> l = new ArrayList<>();
		l.add(List.of(1));
		if (numRows == 1) return l;
		for (int i = 1; i < numRows; i++) {
			List<Integer> lastLine = l.get(i - 1);
			List<Integer> thisLine = new ArrayList<>();
			thisLine.add(1);
			for (int j = 1; j < lastLine.size(); j++) {
				thisLine.add(lastLine.get(j - 1) + lastLine.get(j));
			}
			thisLine.add(1);
			l.add(thisLine);
		}
		return l;
	}
	
	
	

}
