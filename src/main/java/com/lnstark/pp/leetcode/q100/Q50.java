package com.lnstark.pp.leetcode.q100;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/powx-n/description/
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2026年3月16日
 */
public class Q50 {

	public static void main(String[] args) {
		double d = 2;
		int n = -2147483648;
		double r = myPow(d, n);
		System.out.println(r);
	}

    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        long absN = n;
        absN = Math.abs(absN);
        long i = 1;
        double tmp = x;
        long left = absN;
        List<Double> list = new ArrayList<>();
        Map<Long, Double> cache = new HashMap<>();
        while (left > 0) {
            if ((i << 1) < left) {
                i <<= 1;
                Double cacheV = cache.get(i);
                if (cacheV == null) {
                    tmp *= tmp;
                    cache.put(i, tmp);
                } else {
                    tmp = cacheV;
                }
            } else {
                list.add(tmp);
                left -= i;
                tmp = x;
                i = 1;
            }

        }
        double c = 1;
        for (Double e : list) {
            c *= e;
        }
        if (n < 0) {
            c = 1 / c;
        }
        return c;
    }



}
