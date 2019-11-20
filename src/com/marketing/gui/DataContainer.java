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
			generalUtility = new double[this.critCount];
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

}
