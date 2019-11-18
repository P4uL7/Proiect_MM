package com.marketing.logic;

import java.util.Scanner;

public class Logic {
	int rows = 0, columns = 0;
	double[][] values;
	String[] criteriaName;
	double[][] utilityMatrix;
	double[] coef;
	double[] generalUtility;

	public Logic() {
	}

	void setRows(final int rows) {
		this.rows = rows;
	}

	void setColumns(final int columns) {
		this.columns = columns;
	}

	void setValues_matrix(final double[][] values) {
		this.values = values;
	}

	void setCriteriaNames(final String[] criteriaName) {
		this.criteriaName = criteriaName;
	}

	void setCoef(final double[] coef) {
		this.coef = coef;
	}

	//

	void runLogic() {

		getRowsColumns();

		createMatrices();

		getCritVars();

		getFavUtility();

		getDecisionalCrit();

		// for (int i = 0; i < _rows; i++)
		// cout << ug[i] << " ";

		printMaxVal();

	}

	private void printMaxVal() {
		double maxVal = 0d;
		for (int i = 0; i < rows; i++)
			if (generalUtility[i] > maxVal)
				maxVal = generalUtility[i];
		System.out.printf("Varianta optima este: %.3f", maxVal);
	}

	private void getDecisionalCrit() {
		final Scanner scan = new Scanner(System.in);
		for (int j = 0; j < columns; j++) {
			System.out.println("Dati criteriul decizional pentru " + criteriaName[j]);
			coef[j] = scan.nextDouble();
		}
		scan.close();

		boolean areEqual = true;
		for (int j = 1; j < columns; j++)
			if (coef[j - 1] != coef[j]) {
				areEqual = false;
				break;
			}

		for (int i = 0; i < rows; i++)
			generalUtility[i] = 0d;
		if (areEqual)
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < columns; j++)
					generalUtility[i] += utilityMatrix[i][j];
		else
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < columns; j++)
					generalUtility[i] += utilityMatrix[i][j] * coef[j];
	}

	private void getFavUtility() {
		final Scanner scan = new Scanner(System.in);
		boolean check = true;
		for (int j = 0; j < columns; j++) {
			System.out.println("Cea mai favorabila utilitate pentru criteriul " + criteriaName[j] + " este cea mai mare valoare?(da = 1/nu = 0)");
			final int temp = scan.nextInt();
			check = (temp == 0) ? false : true;

			double maxVal = 0d, minVal = values[0][j];
			for (int i = 0; i < rows; i++) {
				if (values[i][j] > maxVal)
					maxVal = values[i][j];
				if (values[i][j] < minVal)
					minVal = values[i][j];
			}

			if (check == true) {
				for (int i = 0; i < rows; i++)
					if (maxVal == values[i][j])
						utilityMatrix[i][j] = 1d;
					else if (minVal == values[i][j])
						utilityMatrix[i][j] = 0d;
					else
						utilityMatrix[i][j] = (values[i][j] - minVal) / (maxVal - minVal);
			} else
				for (int i = 0; i < rows; i++)
					if (maxVal == values[i][j])
						utilityMatrix[i][j] = 0d;
					else if (minVal == values[i][j])
						utilityMatrix[i][j] = 1d;
					else
						utilityMatrix[i][j] = (values[i][j] - maxVal) / (minVal - maxVal);
		}
		scan.close();
	}

	private void getCritVars() {
		final Scanner scan = new Scanner(System.in);
		System.out.println("Dati numele criteriilor urmate de enter:");
		for (int j = 0; j < columns; j++)
			criteriaName[j] = scan.nextLine();
		scan.nextLine(); // cr
		for (int j = 0; j < columns; j++) {
			System.out.println("Introduceti variabilele criteriului " + criteriaName[j]);
			for (int i = 0; i < rows; i++)
				values[i][j] = scan.nextDouble();
		}
		scan.close();
	}

	private void createMatrices() {
		values = new double[rows][columns];
		criteriaName = new String[columns];
		utilityMatrix = new double[rows][columns];
		coef = new double[columns];
		generalUtility = new double[rows];
	}

	private void getRowsColumns() {
		final Scanner scan = new Scanner(System.in);

		System.out.println("Care este numarul de criterii pe care doriti sa le introduceti?");
		columns = scan.nextInt();

		System.out.println("Care este numarul de variabile pe care doriti ca fiecare criteriu sa le aibe?");
		rows = scan.nextInt();
		scan.close();
	}

}
