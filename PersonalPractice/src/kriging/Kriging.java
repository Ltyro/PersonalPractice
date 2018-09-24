package kriging;

import java.util.Arrays;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

import kriging.models.Model;
import kriging.models.ModelImpl;

public class Kriging {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 15;
		double x[] = {10, 100, 500, 300},
				y[] = {10, 200, 150, 250},
				response[] = {1, 2, 6, 4};
		String model = "exponential";
		double sigma2 = 0, alpha = 10;
		
	}
	
	private Variogram train(double[] t, double[] x, double[] y, String modelStr,
			double sigma2, double alpha) {
		Variogram variogram = new Variogram(x, y, t, 0.0, 0.0, 0.0, 1/3, 0);
		ModelImpl model;
		switch (modelStr) {
		
			case "exponential":
				model = new ModelImpl((h, nugget, range, sill, A) -> nugget + ((sill - nugget) / range) *
						(1.0 - Math.exp(-(1.0 / A) * (h / range))));
				break;
			case "spherical":
				model = new ModelImpl((h, nugget, range, sill, A) -> {
					if (h > range) 
						return nugget + (sill - nugget) / range;
					return nugget + ((sill - nugget) / range) *
						(1.5 * (h / range) - 0.5 * Math.pow(h / range, 3));
				});
			case "gaussian":
			default:
				model = new ModelImpl((h, nugget, range, sill, A) -> nugget + ((sill - nugget) / range) *
						(1.0 - Math.exp(-(1.0 / A) * Math.pow(h / range, 2))));
				break;
		}
		int i, j, k, l;
		int n = t.length;
		double distance[][] = new double[(n * n - n) / 2][];
		for (i = 0, k = 0; i < n; i++)
			for (j = 0; j < i; j++, k++) {
				distance[k] = new double[2];
				distance[k][0] = Math.pow(
						Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2), 
						0.5
					);
				distance[k][1] = Math.abs(t[i] - t[j]);
			}
		sort(distance, true);
		variogram.range = distance[(n * n - n) / 2 - 1][0];
		// Bin lag distance
		int lags = ((n * n - n) / 2) > 30 ? 30 : (n * n - n) / 2;
		double tolerance = variogram.range / lags;
		double lag[] = rep(0, lags);
		double semi[] = rep(0, lags);
		
		if (lags < 30) {
			for (l = 0; l < lags; l++) {
				lag[l] = distance[l][0];
				semi[l] = distance[l][1];
			}
		} else {
			for (i = 0, j = 0, k = 0, l = 0; i < lags && j < ((n * n - n) / 2); i++, k = 0) {
				while (distance[j][0] <= ((i + 1) * tolerance)) {
					lag[l] += distance[j][0];
					semi[l] += distance[j][1];
					j++;
					k++;
					if (j >= ((n * n - n) / 2)) break;
				}
				if (k > 0) {
					lag[l] /= k;
					semi[l] /= k;
					l++;
				}
			}
			if (l < 2) return variogram; // Error: Not enough points
		}
		
		// Feature transformation
		n = l;
		variogram.range = lag[n - 1] - lag[0];
		double[] X = rep(1, 2 * n);
		double[] Y = new double[n];
		double A = variogram.A;
		for (i = 0; i < n; i++) {
			switch (modelStr) {
				case "gaussian":
					X[i * 2 + 1] = 1.0 - Math.exp(-(1.0 / A) * Math.pow(lag[i] / variogram.range, 2));
					break;
				case "exponential":
					X[i * 2 + 1] = 1.0 - Math.exp(-(1.0 / A) * lag[i] / variogram.range);
					break;
				case "spherical":
					X[i * 2 + 1] = 1.5 * (lag[i] / variogram.range) -
						0.5 * Math.pow(lag[i] / variogram.range, 3);
					break;
			};
			Y[i] = semi[i];
		}
		
		// Least squares
		Matrix Xt = kriging_matrix_transpose(X, n, 2);
		Matrix Z = Xt.mtimes(array2Matrix(Y, n, 2));
		Z = Z.plus(DenseMatrix.Factory.eye(2, 2).times(1 / alpha));
		Matrix cloneZ = Z.clone();
		if (Z.chol() != null)
			Z = Z.chol().inv();
		else {
			cloneZ.e
			kriging_matrix_solve(cloneZ, 2);
			Z = cloneZ;
		}
		var W = kriging_matrix_multiply(kriging_matrix_multiply(Z, Xt, 2, 2, n), Y, 2, n, 1);

		// Variogram parameters
		variogram.nugget = W[0];
		variogram.sill = W[1] * variogram.range + variogram.nugget;
		variogram.n = x.length;
		
		return variogram;
	}
	
	private void sort(double a[][], boolean asc) {
		for(int i = 0; i < a.length - 1; i++) 
			for(int j = i; j < a.length; j++) 
				if((asc && a[j][0] > a[i][0]) || (!asc && a[j][0] < a[i][0])) {
					double temp = a[i][0];
					a[i][0] = a[j][0];
					a[j][0] = temp;
				}
	}
	
	private double[] rep(double value, int len) {
		double []repArr = new double[len];
		Arrays.fill(repArr, value);
		return repArr;
	}
	
	private Matrix array2Matrix(double []X, int r, int l) {
		Matrix result = DenseMatrix.Factory.zeros(r, l);
		for(int i = 0; i < r; r++) {
			for(int j = 0; j < l; j++) {
				result.setAsDouble(X[i*l+j], i, j);
			}
		}
		return result;
	}
	
	private Matrix kriging_matrix_transpose(double []X, int r, int l) {
		return array2Matrix(X, r, l).transpose();
	}
	
}
