package leetcode.q100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 20. 有效的括号
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年1月27日
 */
public class Q20 {

	
    public static void main(String[] args) {
    	Q20 q = new Q20();
    	boolean ret = q.isValid("(]");
        System.out.println(ret);

    }
    

    public boolean isValid(String s) {
    	LinkedList<Character> l = new LinkedList<>();
    	for (char c : s.toCharArray()) {
    		if (l.isEmpty()
    				|| c == '(' || c == '[' || c == '{') {
    			l.add(c);
    		} else if (getPair(l.removeLast()) != c) {
    			return false;
    		}
    	}
    	return l.isEmpty();
    }

    
    private char getPair(char c) {
    	switch(c) {
    		case '(' : return ')';
    		case '[' : return ']';
    		case '{' : return '}';
    	}
    	return ' ';
    }
}
