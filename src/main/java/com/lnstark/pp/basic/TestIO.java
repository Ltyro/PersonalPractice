package com.lnstark.pp.basic;

public class TestIO extends Exception{
	
	class myExc extends Exception {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "haha", b = "xixi";
		int i = 10;
//		System.out.printf("ss%sf%sf%d", a, b, i);
		int count = 0;
		for(count = 0; count < 10; count ++) {
			try {
				if(count == 5) 
					throw new TestIO();
			} catch (Exception e) {
				System.out.println("catch exception " + e);
			} finally {
				System.out.println("finally do sth");
			}
		}
		System.out.println(count);
	}

}
