package leetcode.q100;

/**
 * 下一个排列
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年4月9日
 */
public class Q17 {

	public static void main(String[] args) {
		int[] nums = {5,7,7,8,8,10};
		int target = 10;
		int[] ans = searchRange(nums, target);
		for(int i : ans)
			System.out.print(i);
	}

	public static int[] searchRange(int[] nums, int target) {
		if(nums == null)
			return new int[]{-1, -1};
		if(nums.length == 1)
			return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
		int start = 0, end = nums.length - 1, mid = (start + end + 1) / 2;
		while (start <= end ) {
			if(target < nums[mid]) {
				end = mid - 1;
				mid = (start + end + 1) / 2;
			} else if(target > nums[mid]) {
				start = mid + 1;
				mid = (start + end + 1) / 2;
			} else {
				start = end = mid;
				while(start >= 0 && nums[start] == target) start--;
				while(end < nums.length && nums[end] == target) end++;
				return new int[]{++start, --end};
			}
		}
		
		return new int[]{-1, -1};
		
    }

	

}
