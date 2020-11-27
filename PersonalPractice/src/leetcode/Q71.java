package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点
 * （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * 
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 /
 * 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * 
 * 示例 1：
 * 输入："/home/" 输出："/home" 解释：注意，最后一个目录名后面没有斜杠。 
 * 
 * 示例 2：
 * 输入："/../" 输出："/" 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。 
 * 
 * 示例 3：
 * 输入："/home//foo/" 输出："/home/foo" 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 
 * 示例 4：
 * 输入："/a/./b/../../c/" 输出："/c" 
 * 
 * 示例 5：
 * 输入："/a/../../b/../c//.//" 输出："/c" 
 * 
 * 示例 6：
 * 输入："/a//b////c/d//././/.." 输出："/a/b/c"
 * 
 * 
 * @author Zaoji_Lai
 * @since 1.0
 * @date 2020年11月27日
 */
public class Q71 {

	public static void main(String[] args) {
		String path = "/a//b////c/d//././/..";
		String result = new Q71().simplifyPath(path);
		System.out.println(result);
	}

	/**
	 * big god solution
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
        String[] pathArray = path.split("/");
	    //分割后的几种情况 空格说明是多出来的/，.. .与目录
	    StringBuilder res =new StringBuilder();
	    Deque<String> stack = new ArrayDeque<>();
	    for(int i=0;i<pathArray.length;i++){
	        //2种情况，栈为空或者栈不为空
	        if(pathArray[i].length()==0||pathArray[i].equals("."))    continue;
	        if(!stack.isEmpty()){
	            if(pathArray[i].equals("..")){
	                stack.pop();
	            }else{
	                stack.push(pathArray[i]);
	            }
	        }else{
	            if(!pathArray[i].equals(".."))  stack.push(pathArray[i]);
	        }
	    }
	    if(stack.isEmpty())    return res.append('/').toString();
	    while(!stack.isEmpty()){
	        res.insert(0,stack.pop());
	        res.insert(0,'/');
	    }
	    return res.toString();
	}


	/**
	 * my solution
	 * @param path
	 * @return
	 */
	public String simplifyPath1(String path) {
		path = rmMultiSlash(path);
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		while (path.contains("/./")) {
			path = path.replace("/./", "/");
		}
		int dslashIndex;
		while ((dslashIndex = path.indexOf("/../")) > -1) {
			if (dslashIndex == 0) {
				path = path.substring(3);
				continue;
			}
			int preIndex = dslashIndex - 1;
			Character preChar = null;
			while (preIndex >= 0 && (preChar = path.charAt(preIndex)) != '/') {
				preIndex--;
			}
			if (preChar == '/') {
				path = path.substring(0, preIndex) + path.substring(dslashIndex + 3);
			}
		}
		
		if ("/".equals(path)) {
			return path;
		}
		path = path.substring(0, path.length() - 1);
		return path;
    }

	private String rmMultiSlash(String path) {
		char[] cs = path.toCharArray();
		char[] newcs = new char[cs.length];
		int newIndex = 0;
		boolean lastIsSlash = false;
		for (int i = 0, l = cs.length; i < l; i++) {
			char c = cs[i];
			if (!(c == '/' && lastIsSlash)) {
				newcs[newIndex++] = c;
			}
			lastIsSlash = c == '/';
		}
		return new String(newcs, 0, newIndex);
	}
}
