package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 
 *  
 * 
 * 示例:
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月7日
 */
public class Q4 extends Base {

	public static void main(String[] args) {
		Q4 q = new Q4();
		int nums[] = {3,3};
		int[] result = q.twoSum(nums, 6);
		printArray(result);
	}

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> m = new HashMap<>(nums.length);
		for (int i = 0; i < nums.length; i++) {
			if (m.containsKey(target - nums[i]))
				return new int[]{m.get(target - nums[i]), i};
			m.put(nums[i], i);
		}
		return null;
    }

}
