package basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString {

	public static void main(String[] args) {
		testReg1();
	}
	
	public static void testReg0() {
		// TODO Auto-generated method stub
		String lyric = "[02:39.56]舍不得分开在每一次醒来";
		//String pattern = "\\[[^\\]]+\\]";	//中括号内
		String pattern = "\\([^)]*\\)";	//括号内
		//String pattern = "\\(.+"
		lyric = lyric.replaceAll("\\[.*\\]", "");
		System.out.println(lyric);
	}
	
	public static void testReg1() {
		String s = "http://172.19.20.134:8080/cas/serviceValidate?ticket=ST-5-pJiPweGjnP7pC65poAvH-cas01.example.org&service=http%3A%2F%2F172.19.20.134%3A8090%2Fbsp%2Forg%2Fsearch.do%3FTO_URL%3Dhttp%253A%252F%252F172.19.20.134";
		String regex = "%3FTfdgdf";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		
		int i = 0;
		if(m.find(i))
			System.out.println(m.group());
		
		System.out.println(m.replaceAll(""));
		System.out.println(s.replace("%3FTO_URL.*", ""));
	}
	
}
