package com.daniloff.minesweeper.invite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.daniloff.minesweeper.Game;
import com.daniloff.minesweeper.settings.GameSettings;

public class InviteDialog extends JFrame {

	private static final long serialVersionUID = 8802687501085736809L;

	// private final int X = 10;
	// private final int Y = 10;

	private String fieldSize;
	private String strategy;
	private String occurrence;
	private String pace;

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

					GameSettings gameSettings = new GameSettings(fieldSize, strategy, occurrence, pace);
					//

					new Game().start(gameSettings.getxSize(), gameSettings.getySize());

					welcomeFrame.setVisible(false);
				}
			}

		});

		JPanel choiceSizePanel = new JPanel();
		choiceSizePanel.add(new JLabel("Choose field size"));
		String[] sizes = { "Little", "Normal", "Big", "Huge" };
		final JComboBox<String> sizesComboBox = new JComboBox<String>(sizes);
		sizesComboBox.setSelectedItem("Normal");
		fieldSize = (String) sizesComboBox.getSelectedItem();
		ActionListener sizesComboActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldSize = (String) sizesComboBox.getSelectedItem();
			}
		};
		sizesComboBox.addActionListener(sizesComboActionListener);
		choiceSizePanel.add(sizesComboBox);

		JPanel choiceStrategyPanel = new JPanel();
		choiceStrategyPanel.add(new JLabel("Choose strategy"));
		String[] strategies = { "Fixed mines count", "Probability mining" };
		final JComboBox<String> strategiesComboBox = new JComboBox<String>(strategies);
		// strategiesComboBox.setSelectedItem("Normal");
		strategy = (String) strategiesComboBox.getSelectedItem();
		ActionListener stratefiesComboActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strategy = (String) strategiesComboBox.getSelectedItem();
			}
		};
		strategiesComboBox.addActionListener(stratefiesComboActionListener);
		choiceStrategyPanel.add(strategiesComboBox);

		JPanel choiceOccurrencePanel = new JPanel();
		choiceOccurrencePanel.add(new JLabel("Choose mines freqency"));
		String[] occurrences = { "Rare", "Normal", "Often" };
		final JComboBox<String> occurrenceComboBox = new JComboBox<String>(occurrences);
		occurrenceComboBox.setSelectedItem("Normal");
		occurrence = (String) occurrenceComboBox.getSelectedItem();
		ActionListener occurrenceComboActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				occurrence = (String) occurrenceComboBox.getSelectedItem();
			}
		};
		occurrenceComboBox.addActionListener(occurrenceComboActionListener);
		choiceOccurrencePanel.add(occurrenceComboBox);

		JPanel choicePacePanel = new JPanel();
		choicePacePanel.add(new JLabel("Choose game pace"));
		String[] paces = { "Slow", "Normal", "Fast" };
		final JComboBox<String> pacesComboBox = new JComboBox<String>(paces);
		pacesComboBox.setSelectedItem("Normal");
		pace = (String) pacesComboBox.getSelectedItem();
		ActionListener pasesComboActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pace = (String) pacesComboBox.getSelectedItem();
			}
		};
		pacesComboBox.addActionListener(pasesComboActionListener);
		choicePacePanel.add(pacesComboBox);

		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(0, 1));
		choicePanel.add(choiceSizePanel);
		choicePanel.add(choiceStrategyPanel);
		choicePanel.add(choiceOccurrencePanel);
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
