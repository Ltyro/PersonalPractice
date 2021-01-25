package leetcode.q100;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断数独是否合理
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年5月8日
 */
public class Q18 {

	public static void main(String[] args) {
		char[][] board = {
		                  {'8','3','.','.','7','.','.','.','.'},
		                  {'6','.','.','1','9','5','.','.','.'},
		                  {'.','9','8','.','.','.','.','6','.'},
		                  {'8','.','.','.','6','.','.','.','3'},
		                  {'4','.','.','8','.','3','.','.','1'},
		                  {'7','.','.','.','2','.','.','.','6'},
		                  {'.','6','.','.','.','.','2','8','.'},
		                  {'.','.','.','4','1','9','.','.','5'},
		                  {'.','.','.','.','8','.','.','7','9'}
					};

		boolean ans = isValidSudoku(board);
		System.out.print(ans);
	}

	public static boolean isValidSudoku(char[][] board) {
		// 每行
		for (int i = 0; i < 9; i++) {
			Set<Character> cs = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				char c = board[j][i];
				if(c != '.' && cs.contains(c))
					return false;
				cs.add(c);
			}
		}
		
		// 每列
		for (int i = 0; i < 9; i++) {
			Set<Character> cs = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if(c != '.' && cs.contains(c))
					return false;
				cs.add(c);
			}
		}
		
		// 每个方块
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Set<Character> cs = new HashSet<>();
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						char c = board[i * 3 + k][j * 3 + l];
						if(c != '.' && cs.contains(c))
							return false;
						cs.add(c);
					}
				}
			}
		}
		return true;
    }

	

}
