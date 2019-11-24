package com.marketing.gui;

public class DataContainer {

	private static DataContainer instance = null;

	private int critCount = 0;
	private int varCount = 0;

	private String[] criteriaName;

	private double[][] values;
	private double[] coef;
	private boolean[] fav;

	private double[][] utilityMatrix;
	private double[] generalUtility;

	private DataContainer() {
	}

	public static DataContainer getInstance() {
		if (instance == null)
			instance = new DataContainer();

		return instance;
	}

	public int getCritCount() {
		return critCount;
	}

	public void setCritCount(final int critCount) {
		this.critCount = critCount;
	}

	public int getVarCount() {
		return varCount;
	}

	public void setVarCount(final int varCount) {
		this.varCount = varCount;

		if (this.critCount != 0 && this.varCount != 0) {
			values = new double[this.varCount][this.critCount];

			coef = new double[this.critCount];
			fav = new boolean[this.critCount];
			for (int i = 0; i < fav.length; i++)
				fav[i] = false;

			utilityMatrix = new double[this.varCount][this.critCount];
			generalUtility = new double[this.varCount];//
		}
	}

	public String[] getCriteriaNames() {
		return criteriaName;
	}

	public void setCriteriaNames(final String[] criteriaName) {
		this.criteriaName = criteriaName;
	}

	public double[][] getValues() {
		return values;
	}

	public void setValueAt(final double value, final int row, final int column) {
		this.values[row][column] = value;
	}

	public void setCoefAt(final double value, final int index) {
		this.coef[index] = value;
	}

	public void setFavAt(final boolean value, final int index) {
		this.fav[index] = value;
	}

	public void populateUtilityMatrix() {
		for (int j = 0; j < this.critCount; j++) {
			double maxVal = 0d, minVal = values[0][j];
			for (int i = 0; i < this.varCount; i++) {
				if (values[i][j] > maxVal)
					maxVal = values[i][j];
				if (values[i][j] < minVal)
					minVal = values[i][j];
			}

			if (fav[j] == true) {
				for (int i = 0; i < this.varCount; i++)
					if (maxVal == values[i][j])
						utilityMatrix[i][j] = 1d;
					else if (minVal == values[i][j])
						utilityMatrix[i][j] = 0d;
					else
						utilityMatrix[i][j] = (values[i][j] - minVal) / (maxVal - minVal);
			} else
				for (int i = 0; i < this.varCount; i++)
					if (maxVal == values[i][j])
						utilityMatrix[i][j] = 0d;
					else if (minVal == values[i][j])
						utilityMatrix[i][j] = 1d;
					else
						utilityMatrix[i][j] = (values[i][j] - maxVal) / (minVal - maxVal);
		}
	}

	public void populateGeneralUtility() {
		boolean areEqual = true;
		for (int j = 1; j < this.critCount; j++)
			if (coef[j - 1] != coef[j]) {
				areEqual = false;
				break;
			}

		for (int i = 0; i < this.varCount; i++) // error
			generalUtility[i] = 0d;
		if (areEqual)
			for (int i = 0; i < this.varCount; i++)
				for (int j = 0; j < this.critCount; j++)
					generalUtility[i] += utilityMatrix[i][j];
		else
			for (int i = 0; i < this.varCount; i++)
				for (int j = 0; j < this.critCount; j++)
					generalUtility[i] += utilityMatrix[i][j] * coef[j];
	}

	public double getMaxVal() {
		double maxVal = 0d;
		for (int i = 0; i < this.varCount; i++)
			if (generalUtility[i] > maxVal)
				maxVal = generalUtility[i];
		System.out.printf("Varianta optima este: %.3f", maxVal);

		return maxVal;
	}

}
