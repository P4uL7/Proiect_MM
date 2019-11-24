package com.marketing.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FourthPanel extends JPanel {
	public FourthPanel() {
		final DataContainer data = DataContainer.getInstance();
		final int tableHeight = data.getVarCount() * 20;
		final int tableWidth = data.getCritCount() * 125;

		this.setPreferredSize(new Dimension(Math.min(1200, 30 + tableWidth), 155 + 3 * tableHeight));
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		//
		final Object[][] initialValues = new Object[data.getVarCount()][data.getCritCount()];
		for (int i = 0; i < data.getVarCount(); i++)
			for (int j = 0; j < data.getCritCount(); j++)
				initialValues[i][j] = data.getValues()[i][j];

		final DefaultTableModel initialModel = new DefaultTableModel(initialValues, data.getCriteriaNames());
		final JTable initialTable = new JTable(initialModel);
		initialTable.setTableHeader(null);

		final JScrollPane initialSP = new JScrollPane(initialTable);
		initialSP.setBorder(BorderFactory.createTitledBorder("<html><b>Matricea initiala</b></html>"));
		initialSP.setPreferredSize(new Dimension(Math.min(1100, tableWidth), 24 + tableHeight));
		initialSP.setOpaque(true);
		add(initialSP);
		//
		final NumberFormat formatter = new DecimalFormat("0.00");
		final Object[][] utilityValues = new Object[data.getVarCount()][data.getCritCount()];
		for (int i = 0; i < data.getVarCount(); i++)
			for (int j = 0; j < data.getCritCount(); j++)
				utilityValues[i][j] = formatter.format(data.getUtilityMatrix()[i][j]);

		final DefaultTableModel utilityModel = new DefaultTableModel(utilityValues, data.getCriteriaNames());
		final JTable utilityTable = new JTable(utilityModel);
		utilityTable.setTableHeader(null);

		final JScrollPane utilitySP = new JScrollPane(utilityTable);
		utilitySP.setBorder(BorderFactory.createTitledBorder("<html><b>Matricea utilitatii</b></html>"));
		utilitySP.setPreferredSize(new Dimension(Math.min(1100, tableWidth), 24 + tableHeight));
		utilitySP.setOpaque(true);
		add(utilitySP);
		//
		final Object[][] globalValues = new Object[data.getVarCount()][2];
		for (int i = 0; i < data.getVarCount(); i++) {
			globalValues[i][0] = "<html><b>Var" + (i + 1) + "</b></html>";
			globalValues[i][1] = data.getGeneralUtility()[i];
		}

		final DefaultTableModel globalModel = new DefaultTableModel(globalValues, new String[] { "Var", "ut" });
		final JTable globalTable = new JTable(globalModel);
		globalTable.setTableHeader(null);

		final JScrollPane globalSP = new JScrollPane(globalTable);
		globalSP.setBorder(BorderFactory.createTitledBorder("<html><b>Utilitatea globala a fiecarei variabile</b></html>"));
		globalSP.setPreferredSize(new Dimension(Math.min(1100, tableWidth), 24 + tableHeight));
		globalSP.setOpaque(true);
		add(globalSP);
		//
		final String maxVal = "" + formatter.format(data.getMaxVal());
		final JLabel variantaOptima = new JLabel("<html>   Varianta optima:  <b>" + maxVal + "   </b></html>");
		variantaOptima.setBorder(BorderFactory.createTitledBorder("<html><b>Result</b></html>"));

		add(variantaOptima);
		//
		final JButton button = new JButton("Close");
		button.setPreferredSize(new Dimension(60, 25));
		button.addActionListener(e -> {
			Container parent = getParent();
			while (parent.getParent() != null) {
				parent = parent.getParent();
				System.out.println(parent);
			}

			((JFrame) parent).dispose();
		});
		add(button);

	}
}
