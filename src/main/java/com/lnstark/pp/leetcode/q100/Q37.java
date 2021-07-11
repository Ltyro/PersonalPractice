package leetcode.q100;

import leetcode.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * Q37解数独
 * https://leetcode-cn.com/problems/sudoku-solver/
 *
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年5月4日
 */
public class Q37 extends Base {

	public static void main(String[] args) {
		Q37 q = new Q37();
		char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		q.solveSudoku(board);
		printMatrix(board);
	}

	boolean[][] row = new boolean[9][9];
	boolean[][] col = new boolean[9][9];
	boolean[][][] sq = new boolean[3][3][9];
	boolean valid = false;
	List<int[]> spaces = new ArrayList<>();

	public void solveSudoku(char[][] board) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.')
					spaces.add(new int[]{i, j});
				else {
					int num = board[i][j] - '1';
					row[i][num] = col[j][num] = sq[i / 3][j / 3][num] = true;
				}
			}
		}
		dfs(board, 0);
	}

	void dfs(char[][] board, int pos) {
		if (pos == spaces.size()) {
			valid = true;
			return;
		}

		for (int i = 0; i < 9 && !valid; i++) {
		    int[] posArr = spaces.get(pos);
		    int r = posArr[0];
		    int c = posArr[1];
			if (!row[r][i] && !col[c][i] && !sq[r / 3][c / 3][i]) {
				row[r][i] = col[c][i] = sq[r / 3][c / 3][i] = true;
				board[r][c] = (char) (i + '1');
				dfs(board, pos + 1);
				row[r][i] = col[c][i] = sq[r / 3][c / 3][i] = false;
			}
		}
	}



}
