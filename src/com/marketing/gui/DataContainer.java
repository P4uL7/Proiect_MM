package com.marketing.gui;

public class DataContainer {

	private static DataContainer instance = null;

	private int critCount = 0;
	private int varCount = 0;

	public int getCritCount() {
		return critCount;
	}

	public void setCritCount(int critCount) {
		this.critCount = critCount;
	}

	public int getVarCount() {
		return varCount;
	}

	public void setVarCount(int varCount) {
		this.varCount = varCount;
	}

	private DataContainer() {
		// initialize data
	}

	public static DataContainer getInstance() {
		if (instance == null)
			instance = new DataContainer();

		return instance;
	}
}
