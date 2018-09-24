package kriging.models;

public class SphericalModel implements Model {

	@Override
	public double excuteModel(double h, double nugget, double range, double sill, double A) {
		if (h > range) 
			return nugget + (sill - nugget) / range;
		return nugget + ((sill - nugget) / range) *
			(1.5 * (h / range) - 0.5 * Math.pow(h / range, 3));
	}

}
