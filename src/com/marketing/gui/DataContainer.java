package com.marketing.gui;

public class DataContainer {

	private static DataContainer instance = null;

	private int critCount = 0;
	private int varCount = 0;

	private String[] criteriaName;

	private double[][] values;

	private double[][] utilityMatrix;
	private double[] coef;
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
	}

	public String[] getCriteriaNames() {
		return criteriaName;
	}

	public void setCriteriaNames(final String[] criteriaName) {
		this.criteriaName = criteriaName;
	}

}
