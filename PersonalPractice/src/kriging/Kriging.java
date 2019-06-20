//package kriging;
//
//import java.util.Arrays;
//
//import org.junit.Test;
//import org.ujmp.core.DenseMatrix;
//import org.ujmp.core.Matrix;
//import org.ujmp.core.SparseMatrix;
//
//import kriging.models.Model;
//import kriging.models.ModelImpl;
//
//public class Kriging {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		int num = 15;
//		double x[] = {10, 100, 500, 300},
//				y[] = {10, 200, 150, 250},
//				response[] = {1, 2, 6, 4};
//		double width = 800, height = 800;
//		double[][][] world = {{
//		  			{0, 0}, {width, 0}, {width, height}, {0, height}
//				}};
//		String model = "exponential";
//		double sigma2 = 0, alpha = 10;
//		Kriging kg = new Kriging();
//		Variogram variogram = kg.train(response, x, y, model, sigma2, alpha);
//		Grid grid = kg.grid(world, variogram, 2);
//		System.out.println(1);
//	}
//	
//	private Variogram train(double[] t, double[] x, double[] y, String modelStr,
//			double sigma2, double alpha) {
//		Variogram variogram = new Variogram(x, y, t, 0.0, 0.0, 0.0, 1.0/3, 0);
//		ModelImpl model;
//		switch (modelStr) {
//		
//			case "exponential":
//				model = new ModelImpl((h, nugget, range, sill, A) -> nugget + ((sill - nugget) / range) *
//						(1.0 - Math.exp(-(1.0 / A) * (h / range))));
//				break;
//			case "spherical":
//				model = new ModelImpl((h, nugget, range, sill, A) -> {
//					if (h > range) 
//						return nugget + (sill - nugget) / range;
//					return nugget + ((sill - nugget) / range) *
//						(1.5 * (h / range) - 0.5 * Math.pow(h / range, 3));
//				});
//			case "gaussian":
//			default:
//				model = new ModelImpl((h, nugget, range, sill, A) -> nugget + ((sill - nugget) / range) *
//						(1.0 - Math.exp(-(1.0 / A) * Math.pow(h / range, 2))));
//				break;
//		}
//		variogram.setModel(model);
//		int i, j, k, l;
//		int n = t.length;
//		double distance[][] = new double[(n * n - n) / 2][];
//		for (i = 0, k = 0; i < n; i++)
//			for (j = 0; j < i; j++, k++) {
//				distance[k] = new double[2];
//				distance[k][0] = Math.pow(
//						Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2), 
//						0.5
//					);
//				distance[k][1] = Math.abs(t[i] - t[j]);
//			}
//		sort(distance, true);
//		variogram.range = distance[(n * n - n) / 2 - 1][0];
//		// Bin lag distance
//		int lags = ((n * n - n) / 2) > 30 ? 30 : (n * n - n) / 2;
//		double tolerance = variogram.range / lags;
//		double lag[] = rep(0, lags);
//		double semi[] = rep(0, lags);
//		
//		if (lags < 30) {
//			for (l = 0; l < lags; l++) {
//				lag[l] = distance[l][0];
//				semi[l] = distance[l][1];
//			}
//		} else {
//			for (i = 0, j = 0, k = 0, l = 0; i < lags && j < ((n * n - n) / 2); i++, k = 0) {
//				while (distance[j][0] <= ((i + 1) * tolerance)) {
//					lag[l] += distance[j][0];
//					semi[l] += distance[j][1];
//					j++;
//					k++;
//					if (j >= ((n * n - n) / 2)) break;
//				}
//				if (k > 0) {
//					lag[l] /= k;
//					semi[l] /= k;
//					l++;
//				}
//			}
//			if (l < 2) return variogram; // Error: Not enough points
//		}
//		
//		// Feature transformation
//		n = l;
//		variogram.range = lag[n - 1] - lag[0];
//		double[] X = rep(1, 2 * n);
//		double[] Y = new double[n];
//		double A = variogram.A;
//		for (i = 0; i < n; i++) {
//			switch (modelStr) {
//				case "gaussian":
//					X[i * 2 + 1] = 1.0 - Math.exp(-(1.0 / A) * Math.pow(lag[i] / variogram.range, 2));
//					break;
//				case "exponential":
//					X[i * 2 + 1] = 1.0 - Math.exp(-(1.0 / A) * lag[i] / variogram.range);
//					break;
//				case "spherical":
//					X[i * 2 + 1] = 1.5 * (lag[i] / variogram.range) -
//						0.5 * Math.pow(lag[i] / variogram.range, 3);
//					break;
//			};
//			Y[i] = semi[i];
//		}
//		
//		// Least squares
////		Matrix Y = array2Matrix(arrY, n, 1);
////		Matrix X = array2Matrix(arrX, n, 2);
//		double[] Xt = kriging_matrix_transpose(X, n, 2);
//		double[] Z = kriging_matrix_multiply(Xt, X, 2, n, 2);
//		Z = kriging_matrix_add(Z, kriging_matrix_diag(1 / alpha, 2), 2, 2);
//		double []cloneZ = Arrays.copyOf(Z, Z.length);
//		if (kriging_matrix_chol(Z, 2))
//			kriging_matrix_chol2inv(Z, 2);
//		else {
//			kriging_matrix_solve(cloneZ, 2);
//			Z = cloneZ;
//		}
//		
//		double[] W = kriging_matrix_multiply(kriging_matrix_multiply(Z, Xt, 2, 2, n), Y, 2, n, 1);
//
//		// Variogram parameters
//		variogram.nugget = W[0];
//		variogram.sill = W[1] * variogram.range + variogram.nugget;
//		variogram.n = x.length;
//		
//		// Gram matrix with prior
//		n = x.length;
//		double[] K = new double[n * n];
//		for (i = 0; i < n; i++) {
//			for (j = 0; j < i; j++) {
//				K[i * n + j] = variogram.model(Math.pow(Math.pow(x[i] - x[j], 2) +
//						Math.pow(y[i] - y[j], 2), 0.5));
//				K[j * n + i] = K[i * n + j];
//			}
//			K[i * n + i] = variogram.model(0);
//		}
//
//		// Inverse penalized Gram matrix projected to target vector
//		double[] C = kriging_matrix_add(K, kriging_matrix_diag(sigma2, n), n, n);
//		double[] cloneC = Arrays.copyOf(C, C.length);
//		if (kriging_matrix_chol(C, n))
//			kriging_matrix_chol2inv(C, n);
//		else {
//			kriging_matrix_solve(cloneC, n);
//			C = cloneC;
//		}
//		
//		// Copy unprojected inverted matrix as K
//		K = Arrays.copyOf(C, C.length);
//		double[] M = kriging_matrix_multiply(C, t, n, n, 1);
//		variogram.K = K;
//		variogram.M = M;
//		
//		return variogram;
//	}
//	
//	private Grid grid(double[][][] polygons, Variogram variogram, double width) {
//		int i, j, k, n = polygons.length;
//		if (n == 0) 
//			return null;
//
//		// Boundaries of polygons space
//		double []xlim = {polygons[0][0][0], polygons[0][0][0]};
//		double []ylim = {polygons[0][0][1], polygons[0][0][1]};
//		for (i = 0; i < n; i++) // Polygons
//			for (j = 0; j < polygons[i].length; j++) { // Vertices
//				if (polygons[i][j][0] < xlim[0])
//					xlim[0] = polygons[i][j][0];
//				if (polygons[i][j][0] > xlim[1])
//					xlim[1] = polygons[i][j][0];
//				if (polygons[i][j][1] < ylim[0])
//					ylim[0] = polygons[i][j][1];
//				if (polygons[i][j][1] > ylim[1])
//					ylim[1] = polygons[i][j][1];
//			}
//
//		// Alloc for O(n^2) space
//		double xtarget, ytarget;
//		double[] a = new double[2], b = new double[2];
//		double[] lxlim = new double[2], lylim = new double[2]; // Local dimensions
//		int x = (int) Math.ceil((xlim[1] - xlim[0]) / width);
//		int y = (int) Math.ceil((ylim[1] - ylim[0]) / width);
//
//		Grid A = new Grid(new double[x+1][]);
//		for(i = 0; i < A.datas.length; i++)
//			A.set(i, new double[x+1]);
//		for (i = 0; i < n; i++) {
//			// Range for polygons[i]
//			lxlim[0] = polygons[i][0][0];
//			lxlim[1] = lxlim[0];
//			lylim[0] = polygons[i][0][1];
//			lylim[1] = lylim[0];
//			for (j = 1; j < polygons[i].length; j++) { // Vertices
//				if (polygons[i][j][0] < lxlim[0])
//					lxlim[0] = polygons[i][j][0];
//				if (polygons[i][j][0] > lxlim[1])
//					lxlim[1] = polygons[i][j][0];
//				if (polygons[i][j][1] < lylim[0])
//					lylim[0] = polygons[i][j][1];
//				if (polygons[i][j][1] > lylim[1])
//					lylim[1] = polygons[i][j][1];
//			}
//
//			// Loop through polygon subspace
//			a[0] = Math.floor(((lxlim[0] - ((lxlim[0] - xlim[0]) % width)) - xlim[0]) / width);
//			a[1] = Math.ceil(((lxlim[1] - ((lxlim[1] - xlim[1]) % width)) - xlim[0]) / width);
//			b[0] = Math.floor(((lylim[0] - ((lylim[0] - ylim[0]) % width)) - ylim[0]) / width);
//			b[1] = Math.ceil(((lylim[1] - ((lylim[1] - ylim[1]) % width)) - ylim[0]) / width);
//			for (j = (int) a[0]; j <= a[1]; j++)
//				for (k = (int) b[0]; k <= b[1]; k++) {
//					xtarget = xlim[0] + j * width;
//					ytarget = ylim[0] + k * width;
//					if (pip(polygons[i], xtarget, ytarget))
//						A.set(j, k, predict(xtarget, ytarget, variogram));
//				}
//		}
//		A.xlim = xlim;
//		A.ylim = ylim;
//		A.zlim[0] = getExtreme(variogram.t, false);
//		A.zlim[1] = getExtreme(variogram.t, true);
//		A.width = width;
//		return A;
//	}
//	
//	private void sort(double a[][], boolean asc) {
//		for(int i = 0; i < a.length - 1; i++) 
//			for(int j = i + 1; j < a.length; j++) 
//				if((asc && a[j][0] < a[i][0]) || (!asc && a[j][0] > a[i][0])) {
//					double[] temp = a[i];
//					a[i] = a[j];
//					a[j] = temp;
//				}
//	}
//	
//	private double[] rep(double value, int len) {
//		double []repArr = new double[len];
//		Arrays.fill(repArr, value);
//		return repArr;
//	}
//	
//	
//	private double[] kriging_matrix_transpose(double []X, int n, int m) {
//		int i, j;
//		double Z[] = new double[m * n];
//		for (i = 0; i < n; i++)
//			for (j = 0; j < m; j++)
//				Z[j * n + i] = X[i * m + j];
//		return Z;
//	}
//	
//	private double[] kriging_matrix_add(double []X, double []Y, int n, int m) {
//		int i, j;
//		double []Z = new double[n * m];
//		for (i = 0; i < n; i++)
//			for (j = 0; j < m; j++)
//				Z[i * m + j] = X[i * m + j] + Y[i * m + j];
//		return Z;
//	}
//	
//	
//	private double[] kriging_matrix_multiply(double[] X, double[] Y, int n, int m, int p) {
//		int i, j, k;
//		double []Z = new double[n * p];
//		for (i = 0; i < n; i++) {
//			for (j = 0; j < p; j++) {
//				Z[i * p + j] = 0;
//				for (k = 0; k < m; k++)
//					Z[i * p + j] += X[i * m + k] * Y[k * p + j];
//			}
//		}
//		return Z;
//	};
//	
//	private double[] kriging_matrix_diag(double c, int n) {
//		double []Z = new double[n * n];
//		for (int i = 0; i < n; i++) 
//			for(int j = 0; j < n; j++)
//				Z[i * n + j] = i != j ? 0 : c;
//		return Z;
//	}
//	
//	private boolean kriging_matrix_chol(double []X, int n) {
//		int i, j, k, sum;
//		double []p = new double[n];
//		for (i = 0; i < n; i++) p[i] = X[i * n + i];
//		for (i = 0; i < n; i++) {
//			for (j = 0; j < i; j++)
//				p[i] -= X[i * n + j] * X[i * n + j];
//			if (p[i] <= 0) return false;
//			p[i] = Math.sqrt(p[i]);
//			for (j = i + 1; j < n; j++) {
//				for (k = 0; k < i; k++)
//					X[j * n + i] -= X[j * n + k] * X[i * n + k];
//				X[j * n + i] /= p[i];
//			}
//		}
//		for (i = 0; i < n; i++) X[i * n + i] = p[i];
//		return true;
//	}
//	
//	// Inversion of cholesky decomposition
//	private void kriging_matrix_chol2inv(double []X, int n) {
//		int i, j, k;
//		double sum;
//		for (i = 0; i < n; i++) {
//			X[i * n + i] = 1 / X[i * n + i];
//			for (j = i + 1; j < n; j++) {
//				sum = 0;
//				for (k = i; k < j; k++)
//					sum -= X[j * n + k] * X[k * n + i];
//				X[j * n + i] = sum / X[j * n + j];
//			}
//		}
//		for (i = 0; i < n; i++)
//			for (j = i + 1; j < n; j++)
//				X[i * n + j] = 0;
//		for (i = 0; i < n; i++) {
//			X[i * n + i] *= X[i * n + i];
//			for (k = i + 1; k < n; k++)
//				X[i * n + i] += X[k * n + i] * X[k * n + i];
//			for (j = i + 1; j < n; j++)
//				for (k = j; k < n; k++)
//					X[i * n + j] += X[k * n + i] * X[k * n + j];
//		}
//		for (i = 0; i < n; i++)
//			for (j = 0; j < i; j++)
//				X[i * n + j] = X[j * n + i];
//
//	}
//	
//	private boolean kriging_matrix_solve(double []X, int n) {
//		int m = n;
//		double[] b = new double[n * n];
//		int[] indxc = new int[n];
//		int[] indxr = new int[n];
//		double[] ipiv = new double[n];
//		int i, icol = 0, irow = 0, j, k, l, ll;
//		double big, dum, pivinv, temp;
//
//		for (i = 0; i < n; i++)
//			for (j = 0; j < n; j++) {
//				if (i == j) b[i * n + j] = 1;
//				else b[i * n + j] = 0;
//			}
//		for (j = 0; j < n; j++) ipiv[j] = 0;
//		for (i = 0; i < n; i++) {
//			big = 0;
//			for (j = 0; j < n; j++) {
//				if (ipiv[j] != 1) {
//					for (k = 0; k < n; k++) {
//						if (ipiv[k] == 0) {
//							if (Math.abs(X[j * n + k]) >= big) {
//								big = Math.abs(X[j * n + k]);
//								irow = j;
//								icol = k;
//							}
//						}
//					}
//				}
//			}
//			++(ipiv[icol]);
//
//			if (irow != icol) {
//				for (l = 0; l < n; l++) {
//					temp = X[irow * n + l];
//					X[irow * n + l] = X[icol * n + l];
//					X[icol * n + l] = temp;
//				}
//				for (l = 0; l < m; l++) {
//					temp = b[irow * n + l];
//					b[irow * n + l] = b[icol * n + l];
//					b[icol * n + l] = temp;
//				}
//			}
//			indxr[i] = irow;
//			indxc[i] = icol;
//
//			if (X[icol * n + icol] == 0) return false; // Singular
//
//			pivinv = 1 / X[icol * n + icol];
//			X[icol * n + icol] = 1;
//			for (l = 0; l < n; l++) X[icol * n + l] *= pivinv;
//			for (l = 0; l < m; l++) b[icol * n + l] *= pivinv;
//
//			for (ll = 0; ll < n; ll++) {
//				if (ll != icol) {
//					dum = X[ll * n + icol];
//					X[ll * n + icol] = 0;
//					for (l = 0; l < n; l++) X[ll * n + l] -= X[icol * n + l] * dum;
//					for (l = 0; l < m; l++) b[ll * n + l] -= b[icol * n + l] * dum;
//				}
//			}
//		}
//		for (l = (n - 1); l >= 0; l--)
//			if (indxr[l] != indxc[l]) {
//				for (k = 0; k < n; k++) {
//					temp = X[k * n + indxr[l]];
//					X[k * n + indxr[l]] = X[k * n + indxc[l]];
//					X[k * n + indxc[l]] = temp;
//				}
//			}
//
//		return true;
//	}
//
//	private boolean pip(double[][] polygon, double x, double y) {
//		int i, j;
//		boolean c = false;
//		for (i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
//			if (((polygon[i][1] > y) != (polygon[j][1] > y)) &&
//				(x < (polygon[j][0] - polygon[i][0]) * (y - polygon[i][1]) / (polygon[j][1] - polygon[i][1]) + polygon[i][0])) {
//				c = !c;
//			}
//		}
//		return c;
//	}
//	
//	private double predict(double x, double y, Variogram variogram) {
//		int i;
//		double []k = new double[variogram.n];
//		for (i = 0; i < variogram.n; i++)
//			k[i] = variogram.model(Math.pow(Math.pow(x - variogram.x[i], 2) +
//					Math.pow(y - variogram.y[i], 2), 0.5),
//				variogram.nugget, variogram.range,
//				variogram.sill, variogram.A);
//		return kriging_matrix_multiply(k, variogram.M, 1, variogram.n, 1)[0];
//	}
//	
//	private double getExtreme(double[] array, boolean max) {
//		double extreme = array[0];
//		for(int i = 1; i < array.length; i++) 
//			if((max && array[i] > extreme) || (!max && array[i] < extreme))
//				extreme = array[i];
//		return extreme;
//	}
//	
//	@Test
//	public void experiment() {
//		
//	} 
//}
