package com.marketing.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 732452956504787039L;

	JLabel title;

	public MainPanel() {

		this.setLayout(null);
		this.setPreferredSize(new Dimension(900, 325));
		this.setBorder(new EmptyBorder(30, 30, 30, 30));

		addTitle();

	}

	private void addTitle() {
		title = new JLabel("Marketing App", SwingConstants.CENTER);
		title.setFont(new Font(title.getName(), Font.BOLD, 16));
		title.setBounds(100, 40, 100, 25);
		this.add(title);
	}
}
