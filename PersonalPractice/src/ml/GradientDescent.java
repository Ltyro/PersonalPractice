package ml;

import org.junit.Test;

/**
 * 小试梯度下降
 * @author Lnstark
 * 2018年9月10日
 *
 */
public class GradientDescent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int paramNum = 10;
		double a[] = {2.0, 3.0, 5.0, 4.0, 8.0, 6.0, 4.0, 13.0, 550000.0, -20.0};
		double trainA[] = new double[10];
		double b = 4.0, trainB = 0.0;
		
		int dataNum = 1000;
		double dataX[][] = new double[dataNum][paramNum], dataY[] = new double[dataNum];
		for(int i = 0; i < dataNum; i++) {
			for(int j = 0; j < paramNum; j++) {
				dataX[i][j] = Math.random();
				dataY[i] += dataX[i][j] * a[j];
			}
			dataY[i] += b;
		}
		double alpha = 1e-2;
		GradientDescent GD = new GradientDescent(dataX, dataY, alpha);
		int trainCount = 30000;
		for(int i = 0; i < trainCount; i++) {
			if(i % (trainCount / 10) == 0) {
				for(int j = 0; j < paramNum; j++) {
					System.out.print("a"+j+": "+GD.getA()[j]+", ");
				}
				System.out.println("b: "+GD.getB());
				System.out.println("cost: "+GD.costFunction(trainA, trainB, dataX, dataY, dataNum, paramNum));
			}
			GD.train();
		}
	}
	
	private int dataNum, paramNum;
	
	private double dataX[][], dataY[];
	
	private double a[], b = 0.0, alpha = 0.0;
	private double phiX[];
	
	public GradientDescent() {
		
	}
	
	@Test
	public void testArray() {
		int a[][] = new int[3][2];
		System.out.println(a.length);
	}
	
	
	public GradientDescent(double[][] dataX, double[] dataY, double alpha) {
		if(dataX.length == 0 || dataY.length == 0)
			return;
		this.dataX = dataX;
		this.dataY = dataY;
		dataNum = dataX.length;
		paramNum = dataX[0].length;
		a = new double[paramNum];
		this.alpha = alpha;
		phiX = new double[dataNum];
		setPhiX();
	}
	
	private void setPhiX() {
		phiX = new double[dataNum];
		for(int i = 0; i < dataNum; i++) {
			for(int j = 0; j < paramNum; j++) {
				phiX[i] += dataX[i][j] * a[j];
			}
			phiX[i] += b - dataY[i];
		}
	}
	
	public void train() {
		double tempA[] = new double[paramNum], tempB = 0.0;
		for(int j = 0; j < paramNum; j++) {
			tempA[j] = a[j] - biasA(j) * alpha;
		}
		tempB = b - biasB() * alpha;
		a = tempA;
		b = tempB;
		setPhiX();
	}
	
	private double costFunction(double[] a, double b, double[][] x, double[] y, int dataNum, int paramNum) {
		double result = 0.0;
		for(int i = 0; i < dataNum; i++) {
			result += phiX[i] * phiX[i];
		}
		result /= dataNum;
		return result;
	}
	
	private double biasA(int aIndex) {
		double result = 0.0;
		for(int i = 0; i < dataNum; i++) { 
			
//			result += 2 * a[aIndex] * x[i][aIndex] * x[i][aIndex] +
//					2 * (phiX[i] - x[i][aIndex] * a[aIndex]) * x[i][aIndex];
			result += 2 * phiX[i] * dataX[i][aIndex];
		}
		result /= dataNum;
		return result;
	}
	
	
	private double biasB() {
		double result = 0.0;
		for(int i = 0; i < dataNum; i ++) {
			double otherMonoSum = 0.0;
			for(int j = 0; j < paramNum; j++) {
				otherMonoSum += dataX[i][j] * a[j];
			}
			otherMonoSum -= dataY[i];
			result += 2 * b + 2 * otherMonoSum;
		}
		result /= dataNum;
		return result;
	}

	public double[] getA() {
		return a;
	}

	public double getB() {
		return b;
	}
	
}
