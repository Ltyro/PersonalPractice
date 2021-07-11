package com.lnstark.pp.others.jvm;

public class TestProtected implements Cloneable {

	private int i = 2;
	
	protected void hi() {
		
	}
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public TestProtected(int i) {
		super();
		this.i = i;
	}

	public TestProtected() {
		super();
	}

	public static void main(String[] args) {
		new TestProtected().hi();
		
		TestProtected tp = null, tp1 = new TestProtected();
		tp1.setI(7);
		try {
			tp = (TestProtected) tp1.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tp);
	}
	
	@Override
	public String toString() {
		return i + "";
	}
	
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}
}
