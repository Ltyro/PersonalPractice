package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 加一
 * https://leetcode-cn.com/problems/plus-one/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年11月21日
 */
public class Q66 extends Base {

	public static void main(String[] args) {
		new Q66().run();
	}

	public void run() {
		Q66 q = new Q66();
		int[] digits = {9, 8, 9};
		int[] n = q.plusOne(digits);
		printArray(n);
	}

	public int[] plusOne(int[] digits) {
		int l = digits.length;
		int temp;
		// 进位
		int jw = 1;
		for (int i = l - 1; i >= 0; i--) {
			temp = digits[i] + jw;
			digits[i] = temp % 10;
			if (temp >= 10) {
				jw = 1;
			} else {
				jw = 0;
			}
			if (i == 0 && temp >= 10) {
				int[] newArr = new int[l + 1];
				System.arraycopy(digits, 0, newArr, 1, l);
				newArr[0] = 1;
				return newArr;
			}
		}
		return digits;
	}

}
