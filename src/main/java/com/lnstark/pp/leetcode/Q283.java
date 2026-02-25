package main.java.com.lnstark.pp.leetcode;

import com.lnstark.pp.leetcode.Base;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 * @author zaoji_lai
 * @since 2026/2/25 14:00
 */
public class Q283 extends Base {

    public static void main(String[] args) {
        Q283 q = new Q283();
        int[] nums = { 0,0,1,5,0,0,0,0,3,6,0 };
        q.moveZeroes(nums);
        printArray(nums);
    }

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        for (int i = j; i < nums.length; i++)
            nums[i] = 0;

    }

}
