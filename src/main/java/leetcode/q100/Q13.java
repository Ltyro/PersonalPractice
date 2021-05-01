package leetcode.q100;

/**
 * 13. 罗马数字转整数 
 * https://leetcode-cn.com/problems/roman-to-integer/
 * 
 * 字符 数值 
 * I 1 
 * V 5 
 * X 10 
 * L 50 
 * C 100 
 * D 500 
 * M 1000
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年1月26日
 */
public class Q13 {

	public static void main(String[] args) {
		Q13 q = new Q13();
		// MCMXCIV
		int ret = q.romanToInt("MCMXCIV");
		System.out.println(ret);
	}

	public int romanToInt(String s) {
		int ret = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i), nextC = (i == s.length() - 1) ? '0' : s.charAt(i + 1);
			
			if (c == 'M') {
				ret += 1000;
			} else if (c == 'D') {
				ret += 500;
			} else if (c == 'C') {
				if (nextC == 'M') {
					ret += 900;
					i++;
				} else if(nextC == 'D') {
					ret += 400;
					i++;
				} else {
					ret += 100;
				}
			} else if (c == 'L') {
				ret += 50;
			} else if (c == 'X') {
				if (nextC == 'C') {
					ret += 90;
					i++;
				} else if(nextC == 'L') {
					ret += 40;
					i++;
				} else {
					ret += 10;
				}
			} else if (c == 'V') {
				ret += 5;
			} else if (c == 'I') {
				if (nextC == 'X') {
					ret += 9;
					i++;
				} else if(nextC == 'V') {
					ret += 4;
					i++;
				} else {
					ret += 1;
				}
			}
		}
		return ret;
    }

}
