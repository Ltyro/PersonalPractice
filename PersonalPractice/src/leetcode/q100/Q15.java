package leetcode.q100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import leetcode.q100.Q12.ListNode;

/**
 * Divide Two Integers
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月9日
 */
public class Q15 {

	public static void main(String[] args) {
		int dividend = -214748348;
		int divisor = -13;
		System.out.println(divide(dividend, divisor));
	}

	public static int divide(int a, int b) {
        // a = -2^31, b = -1, a/b = 2^31
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        // a = -2^31, b = -2^31, a/b = 1
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) return 1;
        // a != -2^31, b = -2^31, a/b = 0
        if (b == Integer.MIN_VALUE) return 0;
        // a = -2^31, b != -2^31:  a <= a + abs(b), fix = b > 0 ? -1 : 1
        int fix = 0;
        if (a == Integer.MIN_VALUE) {
            if (b > 0) {
                a += b;
                fix = -1;
            } else {
                a -= b;
                fix = 1;
            }
        }
        boolean neg = false;
        if (a < 0) {
            a = -a;
            neg = !neg;
        }
        if (b < 0) {
            b = -b;
            neg = !neg;
        }
        int result = 0;
        while (a >= b) {
            int x = b;
            int r = 1;
            while (x <= (a>>1)) {
                x <<= 1;
                r <<= 1;
            }
            a -= x;
            result += r;
        }
        return (neg ? -result : result) + fix;
    }

	
	public static int divide1(int dividend, int divisor) {
		long ans = (long)dividend / (long)divisor;
		if(ans < Integer.MIN_VALUE || ans > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return (int) ans;
	}

}
