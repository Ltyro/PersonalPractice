package krigingLayer;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class Model {
	public double[] x, y, response;
	public double[][] X_inv;
	public double[][][] polygons;
	public double response_min, response_max, response_range, nugget, range, sill;
	public int n;
	public ArrayList<Double> semivariance, distance;
	
	public Model() {}
	
	public Model(double[] x, double[] y, double[] response, double[][][] polygons, double response_min,
			double response_max, double response_range) {
		super();
		this.x = x;
		this.y = y;
		this.response = response;
		this.polygons = polygons;
		this.response_min = response_min;
		this.response_max = response_max;
		this.response_range = response_range;
		semivariance = new ArrayList<>();
		distance = new ArrayList<>();
	}
	
	public double spherical(double h) {
		if (h > this.range) return this.sill;
	    if (h <= this.range && h > 0) {
	    	return this.nugget + (this.sill - this.nugget) * ((3 * h) / (2 * this.range) - Math.pow(h, 3) / (2 * Math.pow(this.range, 3)));
	    } else 
	    	return 0;
	}
	
	public double pred(double x, double y) {
	    double[] L = MatrixUtil.R_rep(1, this.n + 1);
	    for (int i = 0; i < n; i++) { 
	      L[i] = spherical(Math.sqrt(Math.pow(this.x[i] - x, 2) + Math.pow(this.y[i] - y, 2)));
	    }
	    double[] R = MatrixUtil.matrixmult(this.X_inv, new double[][]{L})[0];
	    R = Arrays.copyOf(R, R.length - 1);
	    return MatrixUtil.matrixmult(MatrixUtil.R_t(new double[][]{R}), new double[][]{this.response})[0][0];
	}
	
	public double[] getSemiArr() {
		double[] semiArr = new double[semivariance.size()];
		for(int i = 0; i < semivariance.size(); i++) 
			semiArr[i] = semivariance.get(i).doubleValue();
		return semiArr;
	}
	
	public double[] getDisArr() {
		double[] disArr = new double[distance.size()];
		for(int i = 0; i < distance.size(); i++) 
			disArr[i] = distance.get(i).doubleValue();
		return disArr;
	}
	
}
