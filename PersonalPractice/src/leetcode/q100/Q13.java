package leetcode.q100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 括号生成
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q13 {

	public static void main(String[] args) {
		System.out.println(generateParenthesis(4));
	}

	public static List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
		if (n == 0)
            return ans;
		List<List<String>> total_l = new ArrayList<>();
		List<String> l = new ArrayList<>();
		l.add(null);
        total_l.add(l);    // 0组括号时记为None
        l = new ArrayList<>();
        l.add("()");
        total_l.add(l);    // 1组括号只有一种情况
        for (int i = 2; i < n + 1; i++) {    // 开始计算i组括号时的括号组合
            l = new ArrayList<>();  
            for(int j = 0; j < i; j++) {    			// 开始遍历 p q ，其中p+q=i-1 , j 作为索引
                List<String> now_list1 = total_l.get(j);    // p = j 时的括号组合情况
                List<String> now_list2 = total_l.get(i-1-j);    // q = (i-1) - j 时的括号组合情况
                for (String k1 : now_list1) {  
                    for (String k2 : now_list2) {
                        if (k1 == null)
                            k1 = "";
                        if (k2 == null)
                            k2 = "";
                        String el = "(" + k1 + ")" + k2;
                        l.add(el);    // 把所有可能的情况添加到 l 中
                    }
                }
            }
            total_l.add(l);    // l这个list就是i组括号的所有情况，添加到total_l中，继续求解i=i+1的情况
        }
		return total_l.get(n);
    }

	

}
