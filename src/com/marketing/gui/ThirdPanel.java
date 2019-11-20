package com.marketing.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class ThirdPanel extends JPanel {
	private static final long serialVersionUID = 5174745425152574232L;

	public ThirdPanel() {
		final DataContainer data = DataContainer.getInstance();
		this.setPreferredSize(new Dimension(Math.min(1200, 170 + data.getCritCount() * 125), 90 + (data.getVarCount() + 2) * 16));
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		final JLabel title = new JLabel("Introduceti variabilele fiecarui criteriu:", SwingConstants.CENTER);
		title.setFont(new Font(title.getName(), Font.BOLD, 16));
		add(title);

		final String[] columnNames = new String[data.getCritCount() + 1];
		columnNames[0] = "";
		for (int i = 0; i < data.getCriteriaNames().length; i++)
			columnNames[i + 1] = data.getCriteriaNames()[i];

		final Object[][] dataz = new Object[data.getVarCount() + 2][columnNames.length + 1];
		for (int i = 0; i < data.getVarCount(); i++)
			for (int j = 0; j < columnNames.length + 1; j++)
				if (j == 0)
					dataz[i][j] = "<html><b>Var" + (i + 1) + "</b></html>";
				else
					dataz[i][j] = new Double(0);

		dataz[data.getVarCount()][0] = "<html><b>coef</b></html>";
		for (int j = 1; j < columnNames.length + 1; j++)
			dataz[data.getVarCount()][j] = new Double(0);

		dataz[data.getVarCount() + 1][0] = "";
		for (int j = 1; j < columnNames.length + 1; j++)
			dataz[data.getVarCount() + 1][j] = new Boolean(false);

		//
		List<TableCellEditor> editors = new ArrayList<TableCellEditor>(data.getCritCount());
		JCheckBox[] boxes = new JCheckBox[data.getCritCount()];
		for (int i = 0; i < data.getCritCount(); i++) {
			JCheckBox checkBox = new JCheckBox();
			checkBox.setHorizontalAlignment(SwingConstants.CENTER);
			DefaultCellEditor dce = new DefaultCellEditor(checkBox);
			editors.add(dce);
			boxes[i] = checkBox;
		}

		//
		final TableModel model = new EditableTableModel(columnNames, dataz);
		final JTable table = new JTable(model) {
			public TableCellEditor getCellEditor(int row, int column) {
				if (row == data.getVarCount() + 1 && column != 0)
					return editors.get(column - 1);
				else
					return super.getCellEditor(row, column);
			}

			public TableCellRenderer getCellRenderer(int row, int column) {
				if (row == data.getVarCount() + 1 && column != 0)
					return new CheckBoxCellRenderer(boxes[column - 1]);
				else if (column == 0)
					return super.getCellRenderer(row, column);
				else
					return new CenterCellRenderer();
			}
		};

		table.createDefaultColumnsFromModel();
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSelectionAllowed(false);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		final JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(Math.min(1100, data.getCritCount() * 125), 28 + (data.getVarCount() + 2) * 16));
		sp.setOpaque(true);
		add(sp, BorderLayout.CENTER);

		final JButton button = new JButton("OK");
		button.setPreferredSize(new Dimension(70, 25));
		button.addActionListener(e -> {
			showNextWindow();
		});
		add(button);

	}

	class CheckBoxCellRenderer implements TableCellRenderer {
		JCheckBox combo;

		public CheckBoxCellRenderer(JCheckBox comboBox) {
			this.combo = comboBox;
		}

		public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			return combo;
		}

	}

	class CenterCellRenderer extends DefaultTableCellRenderer {
		@Override
		public int getHorizontalAlignment() {
			return SwingConstants.CENTER;
		}
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