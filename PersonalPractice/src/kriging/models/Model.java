package kriging.models;

public interface Model {
	double excuteModel(double h, double nugget, double range, double sill, double A);
}
