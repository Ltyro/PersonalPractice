package kriging.models;

public class ModelImpl {

	Model model;
	public ModelImpl(Model model) {
		this.model = model;
	}

	public ModelImpl() {
		super();
	}

	public double excuteModel(double h, double nugget, double range, double sill, double A) {
		return model.excuteModel(h, nugget, range, sill, A);
	}

}
