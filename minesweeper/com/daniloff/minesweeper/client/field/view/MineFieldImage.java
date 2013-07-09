package com.daniloff.minesweeper.client.field.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.daniloff.minesweeper.client.Game;
import com.daniloff.minesweeper.client.field.model.Mark;
import com.daniloff.minesweeper.client.field.model.MineFieldLogic;
import com.daniloff.minesweeper.client.invite.InviteDialog;
import com.daniloff.minesweeper.client.settings.FilenameResolver;
import com.daniloff.minesweeper.client.settings.GameSettings;

@SuppressWarnings("serial")
public class MineFieldImage extends JFrame {
	private int frameXSize;
	private int frameYSize;
	private final int CELL_SIZE = 25;
	private final int ADD_HEIGHT = 120;
	private JButton[][] buttons;
	private JLabel timeGameRemainTxt = new JLabel("time Game Remain");
	private JLabel timeMoveRemainTxt = new JLabel("time Move Remain");
	private JLabel gameProcessTxt = new JLabel("Game: Ready");
	private JLabel moveCountTxt = new JLabel("Moves ");
	private JLabel flagsCountTxt = new JLabel("Flags");
	private JButton restartButton = new JButton("Restart");
	private JButton newGameButton = new JButton("New Game");
	private JButton pauseButton = new JButton("Pause");
	private MineFieldLogic field;
	private GameSettings gameSettings;
	private Dimension screen;
	private boolean stopTimeWatch;

	public void drawMineField() {

		frameXSize = gameSettings.getXSize() * CELL_SIZE;
		frameYSize = gameSettings.getYSize() * CELL_SIZE + ADD_HEIGHT;
		screen = gameSettings.getScreen();
		final JFrame mineField = new JFrame("Поляна");
		mineField.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		mineField.setSize(frameXSize, frameYSize);
		mineField.setResizable(true);
		mineField.setLocation((screen.width - frameXSize) / 2, (screen.height - frameYSize) / 2);

		JPanel topPanel = new JPanel();
		JPanel timePanel = new JPanel();
		timePanel.setLayout(new GridLayout(0, 1));
		timePanel.add(getTimeGameRemainTxt());
		timePanel.add(getTimeMoveRemainTxt());
		topPanel.add(timePanel);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 0));

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0, 1));
		infoPanel.add(getGameProcessTxt());
		infoPanel.add(getMoveCountTxt());
		infoPanel.add(getFlagsCountTxt());

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(0, 1));
		controlPanel.add(getPauseButton());
		controlPanel.add(newGameButton);
		controlPanel.add(restartButton);

		bottomPanel.add(infoPanel);
		bottomPanel.add(controlPanel);

		JPanel panel01 = new JPanel();
		panel01.setSize(30, 30);
		panel01.setLayout(new GridLayout(0, 1));
		mineField.getContentPane().add(panel01);

		buttons = new JButton[gameSettings.getXSize()][gameSettings.getYSize()];

		for (int y = 0; y < gameSettings.getYSize(); y++) {
			JPanel panel10 = new JPanel();
			panel01.add(panel10);
			panel10.setLayout(new GridLayout(1, 0));

			for (int x = 0; x < gameSettings.getXSize(); x++) {
				buttons[x][y] = new JButton();
				try {
					buttons[x][y].setIcon(new ImageIcon(ImageIO.read(new File("images/nonChecked_25.png"))));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				panel10.add(buttons[x][y]);

				final int xInner = x;
				final int yInner = y;
				buttons[x][y].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (field.isGameOver())
							return;
						if (e.getButton() == MouseEvent.BUTTON1) {
							if (field.getCells()[xInner][yInner].isFlagged())
								return;
							if (field.getCells()[xInner][yInner].isShown())
								return;

							field.step(xInner, yInner);
							try {
								redrawMineField();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						} else {
							if (e.getButton() == MouseEvent.BUTTON3) {
								field.flag(xInner, yInner);
								try {
									buttons[xInner][yInner].setIcon(new ImageIcon(ImageIO.read(new File(
											fileNameCompose(xInner, yInner)))));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				});
			}
		}

		restartButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					mineField.setVisible(false);
					stopTimeWatch = true;
					Game game = new Game(gameSettings);
					game.setGameSettings(gameSettings);
					game.start(gameSettings);
				}
			}
		});

		newGameButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					mineField.setVisible(false);
					stopTimeWatch = true;
					InviteDialog dialog = new InviteDialog();
					dialog.setScreen(screen);
					dialog.init();
				}
			}
		});

		getPauseButton().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					field.pause();
				}
			}
		});

		mineField.setLayout(new BorderLayout());
		mineField.getContentPane().add(BorderLayout.CENTER, panel01);
		mineField.getContentPane().add(BorderLayout.NORTH, topPanel);
		mineField.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
		mineField.setVisible(true);
	}

	public MineFieldLogic getField() {
		return field;
	}

	public void redrawMineField() throws IOException {
		for (int x = 0; x < gameSettings.getXSize(); x++) {
			for (int y = 0; y < gameSettings.getYSize(); y++) {
				if (field.getCells()[x][y].isShown()) {
					String fileName = fileNameCompose(x, y);
					buttons[x][y].setIcon(new ImageIcon(ImageIO.read(new File(fileName))));
				}
			}
		}
	}

	private String fileNameCompose(int xCmp, int yCmp) {
		Mark mark = field.getCells()[xCmp][yCmp].getMark();
		String fileName = FilenameResolver.MARK_TO_FILENAME.get(mark);
		if (fileName == null) {
			throw new RuntimeException("Cannot find filename for " + mark);
		}
		return fileName;
	}

	public void setField(MineFieldLogic field) {
		this.field = field;
	}

	public JLabel getTimeGameRemainTxt() {
		return timeGameRemainTxt;
	}

	public void setTimeGameRemainTxt(JLabel timeGameRemainTxt) {
		this.timeGameRemainTxt = timeGameRemainTxt;
	}

	public JLabel getTimeMoveRemainTxt() {
		return timeMoveRemainTxt;
	}

	public void setTimeMoveRemainTxt(JLabel timeMoveRemainTxt) {
		this.timeMoveRemainTxt = timeMoveRemainTxt;
	}

	public JLabel getGameProcessTxt() {
		return gameProcessTxt;
	}

	public void setGameProcessTxt(JLabel gameProcessTxt) {
		this.gameProcessTxt = gameProcessTxt;
	}

	public JLabel getMoveCountTxt() {
		return moveCountTxt;
	}

	public void setMoveCountTxt(JLabel moveCountTxt) {
		this.moveCountTxt = moveCountTxt;
	}

	public JLabel getFlagsCountTxt() {
		return flagsCountTxt;
	}

	public void setFlagsCountTxt(JLabel flagsCountTxt) {
		this.flagsCountTxt = flagsCountTxt;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public GameSettings getGameSettings() {
		return gameSettings;
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public boolean isStopTimeWatch() {
		return stopTimeWatch;
	}
}
