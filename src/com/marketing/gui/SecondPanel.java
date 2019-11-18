package com.marketing.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SecondPanel extends JPanel {
	private static final long serialVersionUID = -9155290085936293956L;

	public SecondPanel() {
		this.setPreferredSize(new Dimension(550, 200));
		this.setBorder(new EmptyBorder(5, 15, 5, 15));

		JButton button = new JButton("click");
		button.setPreferredSize(new Dimension(60, 25));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataContainer data = DataContainer.getInstance();
				System.out.println(data.getCritCount() + " " + data.getVarCount());
			}
		});

		add(button);
	}
}
