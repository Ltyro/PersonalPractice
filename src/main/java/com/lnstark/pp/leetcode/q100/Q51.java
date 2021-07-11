package com.lnstark.pp.leetcode.q100;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N 皇后。
 * https://leetcode-cn.com/problems/n-queens/
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月16日
 */
public class Q51 {

	public static void main(String[] args) {
		Q51 q = new Q51();
		List<List<String>> r = q.solveNQueens(7);
		System.out.println(r);
	}

	public List<List<String>> solveNQueens(int n) {
		char[][] carr = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				carr[i][j] = '.';
			}
		}
		List<List<String>> ret = new ArrayList<>();
		traceBack(n, ret, 0, carr);
		return ret;
    }

	private void traceBack(int n, List<List<String>> ret, int i, char[][] path) {
		if (i == n) {
			ret.add(construct(path));
		}
		for (int j = 0; j < n; j++) {
			if (valid(path, i, j, n)) {
				path[i][j] = 'Q';
				traceBack(n, ret, i + 1, path);
				path[i][j] = '.';
			}
		}
		
	}

	private List<String> construct(char[][] path) {
		List<String> l = new ArrayList<>();
		for (int i = 0; i < path.length; i++)
			l.add(new String(path[i]));
		return l;
	}

	private boolean valid(char[][] path, int row, int col, int n) {
		for (int i = 0; i < n; i++)
			if (path[i][col] == 'Q')
				return false;
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (path[i][j] == 'Q')
				return false;
		}
		for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
			if (path[i][j] == 'Q')
				return false;
		}
		return true;
	}


}
