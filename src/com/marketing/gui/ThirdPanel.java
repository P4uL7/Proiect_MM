package com.marketing.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
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

	public ThirdPanel() {
		final DataContainer data = DataContainer.getInstance();
		final int tableHeight = (data.getVarCount() + 2) * 20;
		final int tableWidth = data.getCritCount() * 125;

		this.setPreferredSize(new Dimension(Math.min(1200, 30 + tableWidth), 155 + tableHeight));
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		// this.setBorder(BorderFactory.createTitledBorder("yolo"));

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

		dataz[data.getVarCount() + 1][0] = "<html><b>*</b></html>";
		for (int j = 1; j < columnNames.length + 1; j++)
			dataz[data.getVarCount() + 1][j] = new Boolean(false);

		//
		final List<TableCellEditor> editors = new ArrayList<>(data.getCritCount());
		final JCheckBox[] boxes = new JCheckBox[data.getCritCount()];
		for (int i = 0; i < data.getCritCount(); i++) {
			final JCheckBox checkBox = new JCheckBox();
			checkBox.setHorizontalAlignment(SwingConstants.CENTER);
			final DefaultCellEditor dce = new DefaultCellEditor(checkBox);
			editors.add(dce);
			boxes[i] = checkBox;
		}

		//
		final TableModel model = new EditableTableModel(columnNames, dataz);
		final JTable table = new JTable(model) {
			@Override
			public TableCellEditor getCellEditor(final int row, final int column) {
				if (row == data.getVarCount() + 1 && column != 0)
					return editors.get(column - 1);
				else
					return super.getCellEditor(row, column);
			}

			@Override
			public TableCellRenderer getCellRenderer(final int row, final int column) {
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
		sp.setPreferredSize(new Dimension(Math.min(1100, tableWidth), 24 + tableHeight));
		sp.setOpaque(true);
		add(sp, BorderLayout.CENTER);

		final JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Math.min(1200, 30 + tableWidth), 100));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		final JButton button = new JButton("OK");
		// button.setPreferredSize(new Dimension(70, 25));
		button.setMaximumSize(new Dimension(70, 25));
		button.addActionListener(e -> {
			data.populateUtilityMatrix();
			data.populateGeneralUtility();
			showNextWindow();
		});

		final JLabel info = new JLabel("<html><b>* Cea mai favorabila utilitate pentru criteriul </b></html>", SwingConstants.CENTER);
		final JLabel info2 = new JLabel("<html><b>acesta este cea mai mare valoare?</b></html>", SwingConstants.CENTER);

		info.setAlignmentX(Component.CENTER_ALIGNMENT);
		info2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(info);
		panel.add(info2);
		panel.add(new JLabel("   "));
		panel.add(button);

		add(panel);

	}

	class CheckBoxCellRenderer implements TableCellRenderer {
		JCheckBox combo;

		public CheckBoxCellRenderer(final JCheckBox comboBox) {
			this.combo = comboBox;
		}

		@Override
		public Component getTableCellRendererComponent(final JTable jtable, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
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
			final FourthWindow fourth = new FourthWindow();
			fourth.setVisible(true);
		});

		((JFrame) parent).dispose();
	}
}