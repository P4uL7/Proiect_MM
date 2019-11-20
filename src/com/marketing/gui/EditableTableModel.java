package com.marketing.gui;

import javax.swing.table.AbstractTableModel;

class EditableTableModel extends AbstractTableModel {

	String[] columnTitles;
	DataContainer data;
	Object[][] dataEntries;

	int rowCount;

	public EditableTableModel(final String[] columnTitles, final Object[][] dataEntries) {
		this.columnTitles = columnTitles;
		this.dataEntries = dataEntries;

		for (int i = 0; i < dataEntries.length - 1; i++)
			for (int j = 1; j < columnTitles.length; j++)
				setValueAt(null, i, j);
		data = DataContainer.getInstance();
	}

	@Override
	public int getRowCount() {
		return dataEntries.length;
	}

	@Override
	public int getColumnCount() {
		return columnTitles.length;
	}

	@Override
	public Object getValueAt(final int row, final int column) {
		return dataEntries[row][column];
	}

	@Override
	public String getColumnName(final int column) {
		return columnTitles[column];
	}

	@Override
	public Class getColumnClass(final int column) {
		if (getValueAt(0, column) == null)
			return Double.class;
		else
			return getValueAt(0, column).getClass();
	}

	@Override
	public boolean isCellEditable(final int row, final int column) {
		return column == 0 ? false : true;
	}

	@Override
	public void setValueAt(final Object value, final int row, final int column) {
		dataEntries[row][column] = value;
		if (row < dataEntries.length - 1) {
			if (value != null) {
				System.out.println("setValueAt[" + row + "][" + column + "] = " + value);
				if (row < dataEntries.length - 2)
					data.setValueAt((Double) value, row, column - 1);
				else if (row == dataEntries.length - 1)
					data.setCoefAt((Double) value, column - 1);
			}
		} else if (row == dataEntries.length - 1) {
			data.setFavAt((Boolean) value, column - 1);
			System.out.println("setValueAt[" + row + "][" + column + "] = " + value);
		}
	}
}