package com.marketing.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ThirdWindow extends JFrame {

	public ThirdWindow() {
		initUI();
	}

	private void initUI() {
		try {
			UIManager.setLookAndFeel(App.THEME);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		add(new ThirdPanel());

		pack();

		setTitle("Proiect MM");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
