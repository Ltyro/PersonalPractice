package com.lnstark.pp.kriging;

public class Grid {

	public double[][] datas;
	public double[] xlim, ylim, zlim = new double[2];
	public double width;
	
	public Grid() {
		
	}
	
	public Grid(double[][] ds) {
		datas = ds;
	}
	
	public double[] get(int i) {
		return datas[i];
	}
	
	public double get(int i, int j) {
		return datas[i][j];
	}
	
	public void set(int i, double value[]) {
		datas[i] = value;
	}
	
	public void set(int i, int j, double value) {
		datas[i][j] = value;
	}
}
