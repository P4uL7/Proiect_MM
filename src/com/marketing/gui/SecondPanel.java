package com.marketing.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SecondPanel extends JPanel {
	private static final long serialVersionUID = -9155290085936293956L;

	public SecondPanel() {
		final DataContainer data = DataContainer.getInstance();
		final JTextField[] critNames = new JTextField[data.getCritCount()];

		this.setLayout(new GridLayout(data.getCritCount() + 2, 1));
		this.setPreferredSize(new Dimension(550, 200));
		this.setBorder(new EmptyBorder(15, 15, 5, 15));

		final JLabel title = new JLabel("Dati numele criteriilor:", SwingConstants.CENTER);
		title.setFont(new Font(title.getName(), Font.BOLD, 16));
		add(title);

		for (int i = 0; i < data.getCritCount(); i++) {
			final JPanel critPan = new JPanel();
			final JLabel label = new JLabel("Criteriul " + (i + 1) + ":");
			critNames[i] = new JTextField();
			critNames[i].setPreferredSize(new Dimension(120, 25));

			critPan.add(label);
			critPan.add(critNames[i]);

			add(critPan);
		}

		final JPanel pan = new JPanel();
		final JButton button = new JButton("OK");
		button.setPreferredSize(new Dimension(60, 25));
		button.addActionListener(e -> {
			final String[] names = new String[data.getCritCount()];
			for (int i = 0; i < data.getCritCount(); i++)
				names[i] = critNames[i].getText().trim().isEmpty() ? "null" + i : critNames[i].getText().trim();
			data.setCriteriaNames(names);

			for (final String text : data.getCriteriaNames())
				System.out.println(text);

			showNextWindow();

		});
		pan.add(button);
		add(pan);
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
