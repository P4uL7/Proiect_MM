package com.marketing.test;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class TableComboBoxByRow extends JFrame {
	List<TableCellEditor> editors = new ArrayList<TableCellEditor>(2);
	List<JCheckBox> boxes = new ArrayList<JCheckBox>(2);

	public TableComboBoxByRow() {
		// Create the editors to be used for each row

		JCheckBox checkBox1 = new JCheckBox();
		DefaultCellEditor dce1 = new DefaultCellEditor(checkBox1);
		editors.add(dce1);
		boxes.add(checkBox1);

		JCheckBox checkBox2 = new JCheckBox();
		DefaultCellEditor dce2 = new DefaultCellEditor(checkBox2);
		editors.add(dce2);
		boxes.add(checkBox2);

		// Create the table with default data

		Object[][] data = { { "Color", "Red" }, { "Shape", "Square" }, { "Fruit", "Banana" }, { "Something", new Boolean(false) } };
		String[] columnNames = { "Type", "Value" };
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		JTable table = new JTable(model) {
			// Determine editor to be used by row
			public TableCellEditor getCellEditor(int row, int column) {
				int modelColumn = convertColumnIndexToModel(column);

				// if (modelColumn == 1 && row < 3)
				if (row == 3 && column != 0)
					return editors.get(column);
				else
					return super.getCellEditor(row, column);
			}

			public TableCellRenderer getCellRenderer(int row, int column) {
				if (row == 3 && column != 0)
					return new CheckBoxCellRenderer(boxes.get(column));
				else
					return super.getCellRenderer(row, column);
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
	}

	class CheckBoxCellRenderer implements TableCellRenderer {
		JCheckBox check;

		public CheckBoxCellRenderer(JCheckBox comboBox) {
			this.check = comboBox;
		}

		public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			return check;
		}
	}

	public static void main(String[] args) {
		TableComboBoxByRow frame = new TableComboBoxByRow();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}