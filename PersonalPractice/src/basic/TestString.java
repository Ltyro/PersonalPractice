package basic;

public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String lyric = "[02:39.56]舍不得分开在每一次醒来";
		//String pattern = "\\[[^\\]]+\\]";	//中括号内
		String pattern = "\\([^)]*\\)";	//括号内
		//String pattern = "\\(.+"
		lyric = lyric.replaceAll("\\[.*\\]", "");
		System.out.println(lyric);
	}

}
