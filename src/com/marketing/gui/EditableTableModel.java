package com.marketing.gui;

import javax.swing.table.AbstractTableModel;

class EditableTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6424001624305680388L;

	String[] columnTitles;

	Object[][] dataEntries;

	int rowCount;

	public EditableTableModel(final String[] columnTitles, final Object[][] dataEntries) {
		this.columnTitles = columnTitles;
		this.dataEntries = dataEntries;
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
		return getValueAt(0, column).getClass();
	}

	@Override
	public boolean isCellEditable(final int row, final int column) {
		return column == 0 ? false : true;
	}

	@Override
	public void setValueAt(final Object value, final int row, final int column) {
		dataEntries[row][column] = value;
	}
}