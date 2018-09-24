package kriging;

import kriging.models.Model;

public class Variogram {
	public double x[], y[], t[];
	public double nugget = 0.0, range = 0.0, sill = 0.0,
			A = 1 / 3, n = 0;
	Model model;
	public Variogram() {
		
	}

	public Variogram(double[] x, double[] y, double[] t, double nugget, double range, double sill, double a, double n) {
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
