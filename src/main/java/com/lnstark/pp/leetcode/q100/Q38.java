package leetcode.q100;

import leetcode.Base;

/**
 * Q38 https://leetcode-cn.com/problems/count-and-say/
 *
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年5月4日
 */
public class Q38 extends Base {

	public static void main(String[] args) {
		Q38 q = new Q38();
		String result = q.countAndSay(6);
		System.out.println(result);
	}

	public String countAndSay(int n) {
		String ret = "1";
		if (n == 1)
			return ret;
		for (int i = 2; i <= n; i++) {
			int last = Character.getNumericValue(ret.charAt(0));
			int tempNum = 1;
			StringBuilder newRet = new StringBuilder();
			int ci = 1;
			for (int j = 1; j < ret.length(); j++) {
				ci = Character.getNumericValue(ret.charAt(j));
				if (ci == last)
					tempNum++;
				else {
					newRet.append(tempNum).append(last);
					tempNum = 1;
				}
				last = ci;
			}
			newRet.append(tempNum).append(last);
			ret = newRet.toString();
		}
		return ret;
	}



}
