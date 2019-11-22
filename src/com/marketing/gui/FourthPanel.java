package com.marketing.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FourthPanel extends JPanel {
	public FourthPanel() {
		final DataContainer data = DataContainer.getInstance();

		this.setPreferredSize(new Dimension(550, 200));
		this.setBorder(new EmptyBorder(5, 15, 5, 15));

		final JButton button = new JButton("OK");
		button.setPreferredSize(new Dimension(60, 25));
		button.addActionListener(e -> {
			for (int i = 0; i < data.getVarCount(); i++) {
				for (int j = 0; j < data.getCritCount(); j++)
					System.out.print(data.getValues()[i][j] + " ");
				System.out.println();
			}
			data.getValues();

			data.getMaxVal();

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
			final FourthWindow fourth = new FourthWindow();
			fourth.setVisible(true);
		});

		((JFrame) parent).dispose();
	}
}
