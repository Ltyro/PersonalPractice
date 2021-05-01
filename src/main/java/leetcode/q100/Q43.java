package leetcode.q100;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 
 * 示例 1:
 * 
 * 输入: num1 = "2", num2 = "3" 输出: "6" 示例 2:
 * 
 * 输入: num1 = "123", num2 = "456" 输出: "56088" 
 * 
 * 说明：
 * 
 * num1 和 num2 的长度小于110。 num1 和 num2 只包含数字 0-9。 num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年9月9日
 */
public class Q43 {

	public static void main(String[] args) {
		
		String num1 = "123", num2 = "456";
		System.out.println(multiply(num1, num2));
//		System.out.println(sumOfIntStr("9999999999999999", 9));
		int temp = 0;
//		System.out.println(String.format("%0"+ 0 + "d", 0));
	}

	public static String multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		String result = "";
		// 进位数
		int temp = 0;
		for (int i = num1.length() - 1; i >= 0; i--) {
			String sum = sumOfIntStr(num2, (num1.charAt(i) - '1' + 1));
			if (temp > 0) {
				sum += String.format("%0"+ temp + "d", 0);
			}
			temp++;
			result = add2numStr(result, sum);
		}
		return result + "";
    }
	
	public static String add2numStr(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

	private static String sumOfIntStr(String str, int n) {
		StringBuilder sb = new StringBuilder("0");
		int tens = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			int sum = n * getNum(str, i) + tens;
			int units = sum % 10;
			sb.setCharAt(0, int2char(units));
			sb.insert(0, '0');
			tens = sum / 10;
		}
		sb.setCharAt(0, int2char(tens));
		if (sb.charAt(0) == '0')
			sb.deleteCharAt(0);
		return sb.toString();
	}
	
	private static int getNum(String s, int i) {
		return s.charAt(i) - '0';
	}
	
	private static char int2char(int i) {
		return (char) (i + '0');
	}
	
	
	
}
