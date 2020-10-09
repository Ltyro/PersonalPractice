package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 
 * 示例:
 * 
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"] 
 * 
 * 输出: 
 * [ 
 * 		["ate","eat","tea"],
 * 		["nat","tan"], 
 * 		["bat"] 
 * ] 
 * 说明：
 * 
 * 所有输入均为小写字母。 不考虑答案输出的顺序。
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月16日
 */
public class Q49 {

	public static void main(String[] args) {
		String[] arr = { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> result = groupAnagrams(arr);
		System.out.println(result);
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> m = new HashMap<>();
		List<String> temp;
		for (String s : strs) {
			String sortedS = sortStr(s);
			if ((temp = m.get(sortedS)) == null) {
				temp = new ArrayList<>();
				m.put(sortedS, temp);
			}
			temp.add(s);
		}
		result.addAll(m.values());
		return result;
	}

	private static String sortStr(String s) {
		char[] carr = s.toCharArray();
		Arrays.sort(carr);
		return new String(carr);
	}

}
