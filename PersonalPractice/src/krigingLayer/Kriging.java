//package krigingLayer;
//
//import java.util.Arrays;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class Kriging {
//
//	double pixleSize;
//	double[][][] polygons;
//	double[][] polygoncenters;
//	double[] polygonsorted;
//	Model model;
//	String[] terraincolors = {"#00A600", "#07A800", "#0EAB00", "#16AE00", "#1DB000", "#25B300", "#2DB600", "#36B800", "#3EBB00", "#47BE00", "#50C000", "#59C300", "#63C600", "#6CC800", "#76CB00", "#80CE00", "#8BD000", "#95D300", "#A0D600", "#ABD800", "#B6DB00", "#C2DE00", "#CEE000", "#D9E300", "#E6E600", "#E6DD09", "#E7D612", "#E7CF1C", "#E8C825", "#E8C32E", "#E9BE38", "#E9BA41", "#EAB74B", "#EAB454", "#EBB25E", "#EBB167", "#ECB171", "#ECB17B", "#EDB285", "#EDB48E", "#EEB798", "#EEBAA2", "#EFBFAC", "#EFC4B6", "#F0C9C0", "#F0D0CA", "#F1D7D4", "#F1DFDE", "#F2E8E8", "#F2F2F2"};
// 	public Model getModel() {
//		return model;
//	}
//	
//	public Kriging(double pixleSize) {
//		this.pixleSize = pixleSize;
//	}
//	
//	public static void main(String[] args) {
//		Kriging kg = new Kriging(19);
//		double[] pxX = {835807, 835755, 835755, 835765, 835775, 835796, 835807, 835807, 835817, 835827, 835827, 835827, 835827, 835838, 835848, 835848, 835880};
//		double[] pxY = {-219368, -219408, -219422, -219435, -219449, -219462, -219476, -219489, -219503, -219516, -219529, -219543, -219556, -219570, -219583, -219597, -219610};
//		double[] response = {7.32191e-11, 2.6859e-298, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		double[][][] pxPoly = {
//			{
//				{835739, 835867, 835947, 835809, 835739},
//				{-219419, -219630, -219583, -219361, -219419}
//			}
//		};
//		double[] longrange = {kg.getExtreme(pxX, false), kg.getExtreme(pxX, true)};
//    	double[] latrange = {kg.getExtreme(pxY, false), kg.getExtreme(pxY, true)};
//		kg.krig(pxX, pxY, response, pxPoly);
//		Model model = kg.getModel();
//		JSONArray color2coords = kg.computeC2C();
//		System.out.println(1);
//	}
//	
//	private void krig(double[] x, double[] y, double[] response, double[][][] polygons) {
//		this.polygons = polygons;
//		this.polygoncenters = new double[polygons.length][2];
//		this.polygonsorted = new double[polygons.length];
//		for(int i = 0; i < polygons.length; i++) {
//			this.polygoncenters[i] = new double[]{getMean(polygons[i][0]), getMean(polygons[i][1])};
//			this.polygonsorted[i] = 0;
//		}
//		double response_min = getExtreme(response, false);
//		double response_max = getExtreme(response, true);
//		double response_range = response_max - response_min;
//		Model model = new Model(x, y, response, polygons, response_min, response_max, response_range);
//		
//		this.model = model;
//		model.n = response.length;
//		int i, j, k;
//	    double[][] D = new double[model.n][];
//	    double[][] V = new double[model.n][];
//	    for (i = 0; i < model.n; i++) {
//	      D[i] = new double[model.n];
//	      V[i] = new double[model.n];
//	      for (j = 0; j < model.n; j++) {
//	        D[i][j] = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
//	        V[i][j] = Math.abs(response[i] - response[j]);
//	      }
//	    }
//	    
//	    /* Fit the observations to the variogram */
//	    int lags = 10;
//	    double sum_z, n_h;
//	    double cutoff = Math.sqrt(Math.pow(getExtreme(x, true) - getExtreme(x, false), 2) + Math.pow(getExtreme(y, true) - getExtreme(y, false), 2)) / 3;
//	    for (i = 0; i < lags; i++) {
//	      sum_z = 0;
//	      n_h = 0;
//	      for (j = 0; j < model.n; j++) {
//	        for (k = j + 1; k < model.n; k++) {
//	          if (D[j][k] <= ((i + 1) * cutoff / lags)) {
//	            sum_z += Math.pow(V[j][k], 2);
//	            n_h++;
//	          }
//	        }
//	      }
//	      if (n_h != 0.0) {
//	        model.semivariance.add(sum_z / n_h);
//	        model.distance.add((i + 1) * cutoff / lags);
//	      }
//	    }
//	    
//	    /* Check for enough points in the lag model */
//	    if (model.semivariance.size() < 3) {
//	      /* ERROR -- quit app */
//	    }
//
//	    /* Estimate the model parameters */
//	    double[][] coef = MatrixUtil.R_lm(model.getSemiArr(), model.getDisArr());
//	    model.nugget = 0; //coef[0][0]; /* Intercept */
//	    model.range = getExtreme(model.getDisArr(), true);
//	    model.sill = getExtreme(model.getSemiArr(), true) - model.nugget; //coef[0][1] * this.canvas.model.range;
//	    
//	    /**
//	     * Calculate the inverted (n+1) x (n+1) matrix
//	     * Used to calculate weights
//	     */
//	    double[][] X = new double[model.n + 1][];
//	    for (i = 0; i <= model.n; i++) {
//	      X[i] = new double[model.n + 1];
//	      for (j = 0; j <= model.n; j++) {
//	        if (i == model.n && j !=model.n) X[i][j] = 1;
//	        else {
//	          if (i != model.n && j == model.n) X[i][j] = 1;
//	          else {
//	            if (i == model.n && j == model.n) X[i][j] = 0;
//	            else {
//	              X[i][j] = model.spherical(D[i][j]);
//	            }
//	          }
//	        }
//	      }
//	    }
//
//	    /* Invert the matrix */
//	    model.X_inv = MatrixUtil.R_solve(X);
//	}
//	
//	private JSONArray computeC2C() {
//		JSONArray color2coords = new JSONArray();
//		int i, j, k;
////	    for (i = 0; i < this.polygoncenters.length; i++) {
////	      this.polygonsorted[i] = Math.sqrt(Math.pow(this.polygoncenters[i][0]/* - this.xlim.mean()*/, 2) + Math.pow(this.polygoncenters[i][1]/* - this.ylim.mean()*/, 2));
////	    }
////	    
////	    double maxVal = getExtreme(this.polygonsorted, true);
////	    int nearest = indexOf(this.polygonsorted, getExtreme(this.polygonsorted, false));
//	    double[] xbox;
//	    double[] ybox;
//	    int color;
//
//	    for (i = 0; i < this.polygons.length; i++) {
//	      int nearest = i;
//	      xbox = new double[]{getExtreme(this.polygons[nearest][0], false), getExtreme(this.polygons[nearest][0], true)};
//	      ybox = new double[]{getExtreme(this.polygons[nearest][1], false), getExtreme(this.polygons[nearest][1], true)};
//	      JSONObject c2c = new JSONObject(), coord;
//	      for (j = (int) xbox[0]; j <= xbox[1]; j += 1/*this.xpixel*/) {
//	        for (k = (int) ybox[0]; k <= ybox[1]; k += 1/*this.ypixel*/) {
//	          if (pip(this.polygons[nearest][0], this.polygons[nearest][1], j, k)) {
//	            color = (int) Math.round(49 * (this.model.pred(j, k) - this.model.response_min) / (this.model.response_range));
//	            if (color < 0) color = 0;
//	            else if (color > 49) color = 49;
//	            String colorStr = terraincolors[color];
//	            if(!c2c.has(colorStr))
//	              c2c.put(colorStr, new JSONArray());
//	            coord = new JSONObject();
//	            coord.put("x", j);
//	            coord.put("y", k);
//	            ((JSONArray)c2c.get(colorStr)).put(coord);
//	            // this.pixel(j, k, this.colorspectrum.terraincolors[color])
//	          }
//	        }
//	      }
//	      color2coords.put(c2c);
//	      // this.draw()
////	      this.polygonsorted[nearest] = maxVal;
////	      nearest = indexOf(this.polygonsorted, getExtreme(this.polygonsorted, false));
//
//	    }
//		return color2coords;
//	}
//	
//	private boolean pip(double[] X, double[] Y, double x, double y) {
//		int i, j;
//		boolean c = false;
//		for (i = 0, j = X.length - 1; i < X.length; j = i++) {
//			if (((Y[i] > y) != (Y[j] > y)) && (x < ( X[j] - X[i]) * (y - Y[i]) / (Y[j] - Y[i]) + X[i])) {
//				c = !c;
//			}
//		}
//		return c;
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
//	private double getMean(double[] array) {
//		double mean = 0;
//		for(double d : array) 
//			mean += d;
//		mean /= array.length;
//		return mean;
//	}
//	
//	private int indexOf(double[] array, double value) {
//		for(int i = 0; i < array.length; i++)
//			if(value == array[i])
//				return i;
//		return -1;
//	}
//}
