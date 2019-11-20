package com.marketing.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SecondPanel extends JPanel {

	public SecondPanel() {
		final DataContainer data = DataContainer.getInstance();
		final JTextField[] critNames = new JTextField[data.getCritCount()];

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(500, Math.min(125 + data.getCritCount() * 40, 600)));
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		final JLabel title = new JLabel("Dati numele criteriilor:", SwingConstants.CENTER);
		title.setFont(new Font(title.getName(), Font.BOLD, 16));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(title);
		add(new JLabel(" "));

		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		for (int i = 0; i < data.getCritCount(); i++) {
			final JPanel critPan = new JPanel();
			final JLabel label = new JLabel("Criteriul " + (i + 1) + ":");
			critNames[i] = new JTextField();
			critNames[i].setPreferredSize(new Dimension(120, 25));

			critPan.add(label);
			critPan.add(critNames[i]);

			panel.add(critPan);
		}

		final JScrollPane sp = new JScrollPane(panel);
		sp.setBorder(BorderFactory.createEmptyBorder());
		sp.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(sp);

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
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
