package com.marketing.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

public class ThirdPanel extends JPanel {
	private static final long serialVersionUID = 5174745425152574232L;

	public ThirdPanel() {
		final DataContainer data = DataContainer.getInstance();
		this.setPreferredSize(new Dimension(Math.min(1200, 170 + data.getCritCount() * 125), 90 + data.getVarCount() * 16));
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		final JLabel title = new JLabel("Introduceti variabilele fiecarui criteriu:", SwingConstants.CENTER);
		title.setFont(new Font(title.getName(), Font.BOLD, 16));
		add(title);

		final String[] columnNames = new String[data.getCritCount() + 1];
		columnNames[0] = "";
		for (int i = 0; i < data.getCriteriaNames().length; i++)
			columnNames[i + 1] = data.getCriteriaNames()[i];

		final Object[][] dataEntries = { { "Saravan", "Pantham", new Integer(50), "B", new Boolean(false) }, { "Eric", "", new Integer(180), "O", new Boolean(true) },
				{ "John", "", new Integer(120), "AB", new Boolean(false) }, { "Mathew", "", new Integer(140), "A", new Boolean(true) }, };

		final Object[][] dataz = new Object[data.getVarCount()][columnNames.length + 1];//
		for (int i = 0; i < data.getVarCount(); i++)
			for (int j = 0; j < columnNames.length + 1; j++)
				if (j == 0)
					dataz[i][j] = "Var" + (i + 1);
				else
					dataz[i][j] = "";

		final TableModel model = new EditableTableModel(columnNames, dataz);
		final JTable table = new JTable(model);
		table.createDefaultColumnsFromModel();
		table.getTableHeader().setReorderingAllowed(false);
		//
		// final String[] bloodGroups = { "A", "B", "AB", "O" };
		// final JComboBox comboBox = new JComboBox(bloodGroups);
		// table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));

		final JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(Math.min(1100, data.getCritCount() * 125), 28 + data.getVarCount() * 16));
		sp.setOpaque(true);
		add(sp, BorderLayout.CENTER);

		final JButton button = new JButton("OK");
		button.setPreferredSize(new Dimension(70, 25));
		button.addActionListener(e -> {
			showNextWindow();
		});
		add(button);

	}

	private void showNextWindow() {
		Container parent = getParent();
		while (parent.getParent() != null) {
			parent = parent.getParent();
			System.out.println(parent);
		}

		EventQueue.invokeLater(() -> {
			final ThirdWindow third = new ThirdWindow();
			third.setVisible(true);
		});

		((JFrame) parent).dispose();
	}
}