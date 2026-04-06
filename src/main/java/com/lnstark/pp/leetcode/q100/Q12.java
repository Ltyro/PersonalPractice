package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/integer-to-roman/description/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月31日
 */
public class Q12 extends Base {

	public static void main(String[] args) {
		String head = new Q12().intToRoman(1994);
		System.out.println(head);
	}

	public String intToRoman(int num) {
		StringBuilder sb = new StringBuilder();
		int cs = num / 1000;
		if (cs > 0) {
			for (int i = 0; i < cs; i++)
				sb.append("M");
		}
		cs = num / 100 % 10;

		if (cs > 0) {
			sb.append(subProcess(cs, "C", "D", "M"));
		}
		cs = num / 10 % 10;
		if (cs > 0) {
			sb.append(subProcess(cs, "X", "L", "C"));
		}
		cs = num % 10;
		if (cs > 0) {
			sb.append(subProcess(cs, "I", "V", "X"));
		}
		return sb.toString();
	}

	private String subProcess(int cs, String c1, String c5, String c10) {
		StringBuilder sb = new StringBuilder();
		if (cs == 4) {
			return c1 + c5;
		} else if (cs == 9) {
			return c1 + c10;
		} else if (cs < 4) {
			for (int i = 0; i < cs; i++)
				sb.append(c1);
		} else {
			sb.append(c5);
			for (int i = 0; i < cs - 5; i++)
				sb.append(c1);
		}
		return sb.toString();
	}

}
