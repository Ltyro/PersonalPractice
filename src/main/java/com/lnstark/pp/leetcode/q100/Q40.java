package com.lnstark.pp.leetcode.q100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和2(回溯)
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年5月8日
 */
public class Q40 {

	
    public static void main(String[] args) {
        combinationSum(new int[]{1,1,2,5,6,7,10}, 8);
        System.out.println(res);

    }
    
    public static List<List<Integer>> res =  new LinkedList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        LinkedList<Integer> track = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, track);
        return res;
    }

    private static void backtrack(int[] candidates, int start, int target, LinkedList<Integer> track) {
        if (target < 0) return;
        if (target == 0 && !res.contains(track)){
            res.add(new LinkedList<>(track));
            return;
        }
        for(int i = start;i < candidates.length;i++){
            if(target < candidates[i]) break;
            track.add(candidates[i]);
            backtrack(candidates,i + 1,target-candidates[i],track);
            track.removeLast();
        }
    }


}
