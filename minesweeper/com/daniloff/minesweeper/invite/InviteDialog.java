package com.daniloff.minesweeper.invite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.daniloff.minesweeper.Game;
import com.daniloff.minesweeper.field.model.MineFieldImpl;
import com.daniloff.minesweeper.field.view.MineFieldImage;

public class InviteDialog extends JFrame {

	private static final long serialVersionUID = 8802687501085736809L;

	private final int X = 10;
	private final int Y = 10;

	public void init() {

		final JFrame welcomeFrame = new JFrame("Welcome!");
		welcomeFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		welcomeFrame.setSize(230, 300);
		welcomeFrame.setResizable(false);
		welcomeFrame.setLocation(850, 250);

		JButton goButton = new JButton("Go!");
		goButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					new Game().start(X, Y);

					welcomeFrame.setVisible(false);
				}
			}

		});

		JPanel choiceSizePanel = new JPanel();
		choiceSizePanel.add(new JLabel("Choose field size"));
		String[] sizes = { "Little", "Normal", "Big", "Huge" };
		JComboBox<String> sizesComboBox = new JComboBox<String>(sizes);
		sizesComboBox.setSelectedItem("Normal");
		choiceSizePanel.add(sizesComboBox);

		JPanel choiceStrategyPanel = new JPanel();
		choiceStrategyPanel.add(new JLabel("Choose strategy"));
		String[] strategies = { "Fixed mines count", "Probability mining" };
		JComboBox strategiesCombo = new JComboBox(strategies);

		choiceStrategyPanel.add(strategiesCombo);

		// strategiesCombo.getSelectedItem();

		JPanel choicePacePanel = new JPanel();
		choicePacePanel.add(new JLabel("Choose game pace"));
		String[] paces = { "Slow", "Normal", "Fast" };
		JComboBox pacesComboBox = new JComboBox(paces);
		pacesComboBox.setSelectedItem("Normal");
		choicePacePanel.add(pacesComboBox);

		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(0, 1));
		choicePanel.add(choiceSizePanel);
		choicePanel.add(choiceStrategyPanel);
		choicePanel.add(choicePacePanel);

		JPanel welcomePanel = new JPanel();

		welcomePanel.add(new JLabel("Welcome to Minesweeper game"));

		JPanel goPanel = new JPanel();
		goPanel.add(goButton);
		welcomeFrame.getContentPane().add(BorderLayout.EAST, choicePanel);
		welcomeFrame.getContentPane().add(BorderLayout.SOUTH, goPanel);
		welcomeFrame.getContentPane().add(BorderLayout.NORTH, welcomePanel);

		welcomeFrame.setVisible(true);

	}


}
