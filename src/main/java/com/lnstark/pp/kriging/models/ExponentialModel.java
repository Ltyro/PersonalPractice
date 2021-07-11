package com.lnstark.pp.kriging.models;

public class ExponentialModel implements Model{

	@Override
	public double excuteModel(double h, double nugget, double range, double sill, double A) {
		return nugget + ((sill - nugget) / range) *
				(1.0 - Math.exp(-(1.0 / A) * (h / range)));
	}

}
