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

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 732452956504787039L;
	JTextField critCount;
	JTextField varCount;

	public MainPanel() {

		this.setLayout(new GridLayout(3, 1, 5, 5));
		this.setPreferredSize(new Dimension(550, 200));
		this.setBorder(new EmptyBorder(5, 15, 5, 15));
		final JLabel title = new JLabel("Marketing App", SwingConstants.CENTER);
		title.setFont(new Font(title.getName(), Font.BOLD, 24));

		add(title);
		add(pan());

		final JPanel pan = new JPanel();
		final JButton button = new JButton("OK");
		button.setPreferredSize(new Dimension(60, 25));
		button.addActionListener(e -> {
			final DataContainer data = DataContainer.getInstance();
			data.setCritCount(critCount.getText().isEmpty() ? 0 : Integer.parseInt(critCount.getText().trim()));
			data.setVarCount(varCount.getText().isEmpty() ? 0 : Integer.parseInt(varCount.getText().trim()));

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
			final SecondWindow second = new SecondWindow();
			second.setVisible(true);
		});

		((JFrame) parent).dispose();
	}

	private JPanel pan() {
		final JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2, 1, 5, 5));

		final JPanel top = new JPanel();
		top.add(new JLabel("Care este numarul de criterii pe care doriti sa le introduceti?", SwingConstants.LEFT));
		critCount = new JTextField();
		critCount.setPreferredSize(new Dimension(60, 25));
		top.add(critCount);

		final JPanel bottom = new JPanel();
		bottom.add(new JLabel("Care este numarul de variabile pe care doriti ca fiecare criteriu sa le aibe?", SwingConstants.LEFT));
		varCount = new JTextField();
		varCount.setPreferredSize(new Dimension(60, 25));
		bottom.add(varCount);

		pan.add(top);
		pan.add(bottom);

		return pan;
	}

}
