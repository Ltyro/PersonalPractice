package com.lnstark.pp.leetcode.q100;

import com.lnstark.pp.leetcode.Base;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/valid-sudoku/
 *
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月20日
 */
public class Q36 extends Base {

	public static void main(String[] args) {
		Q36 q = new Q36();
//		char[][] board = {{'5','3','.','.','7','.','.','.','.'}
//						,{'6','.','.','1','9','5','.','.','.'}
//						,{'.','9','8','.','.','.','.','6','.'}
//						,{'8','.','.','.','6','.','.','.','3'}
//						,{'4','.','.','8','.','3','.','.','1'}
//						,{'7','.','.','.','2','.','.','.','6'}
//						,{'.','6','.','.','.','.','2','8','.'}
//						,{'.','.','.','4','1','9','.','.','5'}
//						,{'.','.','.','.','8','.','.','7','9'}};
		char[][] board = {{'.','.','.','.','5','.','.','1','.'},
						{'.','4','.','3','.','.','.','.','.'},
						{'.','.','.','.','.','3','.','.','1'},
						{'8','.','.','.','.','.','.','2','.'},
						{'.','.','2','.','7','.','.','.','.'},
						{'.','1','5','.','.','.','.','.','.'},
						{'.','.','.','.','.','2','.','.','.'},
						{'.','2','.','9','.','.','.','.','.'},
						{'.','.','4','.','.','.','.','.','.'}};
		boolean result = q.isValidSudoku(board);
		System.out.println(result);
	}

	public boolean isValidSudoku(char[][] board) {
		// 行
		for (char[] line : board) {
			if (!checkArr(line))
				return false;
		}
		// 列
		for (int i = 0; i < 9; i++) {
			Set<Character> charSet = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				char tmp = board[j][i];
				if (tmp == '.') continue;
				if (!charSet.add(tmp)) return false;
			}
		}
		// 九宫格
		for (int i = 0; i < 9; i++) {
			// 0~2 0  3~5 3  6~8 6
			int startRow = i / 3 * 3;
			// 036 0  147 3  258 6
			int startCol = (i % 3) * 3;
			Set<Character> charSet = new HashSet<>();
			for (int j = startRow; j < startRow + 3; j++) {
				for (int k = startCol; k < startCol + 3; k++) {
					char tmp = board[j][k];
					if (tmp == '.') continue;
					if (!charSet.add(tmp)) return false;
				}
			}
		}
		return true;
	}

	private boolean checkArr(char[] arr) {
		Set<Character> charSet = new HashSet<>();
		for (char c : arr) {
			if (c == '.') continue;
			if (!charSet.add(c)) return false;
		}
		return true;
	}
}
