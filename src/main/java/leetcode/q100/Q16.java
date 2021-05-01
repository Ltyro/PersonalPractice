package leetcode.q100;

/**
 * 下一个排列
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月9日
 */
public class Q16 {

	public static void main(String[] args) {
		int[] nums = {1, 1, 5};
		nextPermutation(nums);
		for(int i : nums)
			System.out.print(i);
	}

	public static void nextPermutation(int[] nums) {
		if(nums == null || nums.length < 2)
			return;
		
		int n = nums.length;
		for (int i = n - 1; i >= 1; i--) {
			for(int j = i - 1; j >= 0; j--) {
				if (nums[i] > nums[j]) {
					int t = nums[i];
					nums[i] = nums[j];
					nums[j] = t;
					return;
				}
			}
		}
		for (int i = n - 1; i >= 1; i--) {
			for(int j = i - 1; j >= 0; j--) {
				if (nums[i] < nums[j]) {
					int t = nums[i];
					nums[i] = nums[j];
					nums[j] = t;
				}
			}
		}
    }

	

}
