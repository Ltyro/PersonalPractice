package basic;

import java.util.Random;

public class TestRandom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testRandom();
	}
	public static void testRandom() {
		Random rd = new Random();
		for(int i = 0; i < 20; i ++) {
			System.out.println(rd.nextLong());
		}
	}
}
