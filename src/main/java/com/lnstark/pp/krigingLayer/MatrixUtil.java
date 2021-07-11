package krigingLayer;

import java.util.Arrays;

public class MatrixUtil {
	
	public static double[] R_rep (double x, int times) {
	    double[] i = new double[times];
	    Arrays.fill(i, x);
	    return i;
	}
	
	public static double[][] R_lm(double[] prY, double[] prX) {
	  int n = prY.length;

	  /* Add an intercept term to the design matrix */
	  double[][] x = {R_rep(1, n), prX}, y = {prY};

	  /* OLS estimate */
	  return matrixmult(matrixmult(R_solve(matrixmult(R_t(x), x)), R_t(x)), y);
	}
	
	/**
	 * Matrix multiplication 
	 */
	public static double[][] matrixmult(double[][] y, double[][] x) {
	  int i, j, k;
	  int n = x.length;
	  int m = x[0].length;
	  if (m != y.length) 
		  return null;
	  int p = y[0].length;
	  double[][] z = new double[n][];

	  for (i = 0; i < n; i++) {
	    z[i] = new double[p];
	    for (j = 0; j < p; j++) {
	      z[i][j] = 0;
	      for (k = 0; k < m; k++) {
	        z[i][j] += x[i][k] * y[k][j];
	      }
	    }
	  }
	  return z;
	}
	
	/* Matrix transpose */
	public static double[][] R_t(double[][] x) {
	  /* Must be a 2-dimensional matrix */
	  int i, j, n, m;
	  n = x.length;
	  m = x[0].length;

	  double[][] y = new double[m][];
	  for (i = 0; i < m; i++) {
	    y[i] = new double[n];
	    for (j = 0; j < n; j++) {
	      y[i][j] = x[j][i];
	    }
	  }
	  return y;
	}
	
	/* Matrix inversion -- Gauss-jordan elimination */
	public static double[][] R_solve (double[][] a) {
	  int n = a.length;
	  int m = n;
	  double[][] b = new double[n][];
	  int[] indxc = new int[n];
	  int[] indxr = new int[n];
	  double[] ipiv = new double[n];

	  int i, icol = 0, irow = 0, j, k, l, ll;
	  double big, dum, pivinv, temp;

	  for (i = 0; i < n; i++) {
	    b[i] = new double[n];
	    for (j = 0; j < n; j++) {
	      if (i == j) b[i][j] = 1;
	      else b[i][j] = 0;
	    }
	  }
	  for (j = 0; j < n; j++) ipiv[j] = 0;
	  for (i = 0; i < n; i++) {
	    big = 0;
	    for (j = 0; j < n; j++) {
	      if (ipiv[j] != 1) {
	        for (k = 0; k < n; k++) {
	          if (ipiv[k] == 0) {
	            if (Math.abs(a[j][k]) >= big) {
	              big = Math.abs(a[j][k]);
	              irow = j;
	              icol = k;
	            }
	          }
	        }
	      }
	    }
	    ++(ipiv[icol]);

	    if (irow != icol) {
	      for (l = 0; l < n; l++) {
	        temp = a[irow][l];
	        a[irow][l] = a[icol][l];
	        a[icol][l] = temp;
	      }
	      for (l = 0; l < m; l++) {
	        temp = b[irow][l];
	        b[irow][l] = b[icol][l];
	        b[icol][l] = temp;
	      }
	    }

	    indxr[i] = irow;
	    indxc[i] = icol;

	    if (a[icol][icol] == 0) { /* Singular matrix */
	      return null;
	    }

	    pivinv = 1 / a[icol][icol];
	    a[icol][icol] = 1;
	    for (l = 0; l < n; l++) a[icol][l] *= pivinv;
	    for (l = 0; l < m; l++) b[icol][l] *= pivinv;

	    for (ll = 0; ll < n; ll++) {
	      if (ll != icol) {
	        dum = a[ll][icol];
	        a[ll][icol] = 0;
	        for (l = 0; l < n; l++) a[ll][l] -= a[icol][l] * dum;
	        for (l = 0; l < m; l++) b[ll][l] -= b[icol][l] * dum;
	      }
	    }
	  }

	  for (l = (n - 1); l >= 0; l--) {
	    if (indxr[l] != indxc[l]) {
	      for (k = 0; k < n; k++) {
	        temp = a[k][indxr[l]];
	        a[k][indxr[l]] = a[k][indxc[l]];
	        a[k][indxc[l]] = temp;
	      }
	    }
	  }

	  return a;
	}
	
	
}
