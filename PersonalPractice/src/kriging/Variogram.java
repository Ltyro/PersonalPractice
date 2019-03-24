package kriging;

import org.ujmp.core.Matrix;

import kriging.models.ModelImpl;

public class Variogram {
	public int n;
	public double x[], y[], t[];
	public double nugget = 0.0, range = 0.0, sill = 0.0, A = 1 / 3;
	ModelImpl model;
	public double[] K, M;
	
	public Variogram() {
		
	}

	public Variogram(double[] x, double[] y, double[] t, double nugget, double range, double sill, double a, int n) {
		super();
		this.x = x;
		this.y = y;
		this.t = t;
		this.nugget = nugget;
		this.range = range;
		this.sill = sill;
		A = a;
		this.n = n;
	}

	public ModelImpl getModel() {
		return model;
	}

	public void setModel(ModelImpl model) {
		this.model = model;
	}
	
	public double model(double h, double nugget, double range, double sill, double A) {
		return model.excuteModel(h, nugget, range, sill, A);
	}
	
	public double model(double h) {
		return model.excuteModel(h, nugget, range, sill, A);
	}
	
}
