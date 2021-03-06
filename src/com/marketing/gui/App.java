package com.marketing.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App extends JFrame {
	public static final String THEME = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
	// "com.jtattoo.plaf.texture.TextureLookAndFeel"
	// "javax.swing.plaf.nimbus.NimbusLookAndFeel"
	// "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"

	public App() {
		initUI();
	}

	private void initUI() {
		try {
			UIManager.setLookAndFeel(THEME);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		add(new MainPanel());

		pack();

		setTitle("Proiect MM");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(() -> {
			App app = new App();
			app.dispose(); // reboot the app
			app = new App();
			app.setVisible(true);
		});
	}

}
