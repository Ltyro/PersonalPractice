package leetcode.q100;


/**
 * 删除排序数组中的重复项
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2021年02月04日
 */
public class Q26 {

	
	public static void main(String[] args) {
		Q26 q = new Q26();
		int[] nums = {0,0,1,1,1,2,2,3,3,4}; 
		System.out.println(q.removeDuplicates(nums));
	}

    public int removeDuplicates(int[] nums) {
    	if (nums.length <= 1) return nums.length;
    	int ci = 0;
    	for (int i = 1; i < nums.length; i++) {
    		if (nums[i] != nums[ci]) {
    			nums[++ci] = nums[i];
    		}
    	}
    	return ci + 1;
    }
    
}
