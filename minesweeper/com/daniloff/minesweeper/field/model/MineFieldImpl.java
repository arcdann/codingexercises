package com.daniloff.minesweeper.field.model;

import com.daniloff.minesweeper.field.view.MineFieldImage;
import com.daniloff.minesweeper.misc.SoundPlayer;
import com.daniloff.minesweeper.misc.TimeWatch;
import com.daniloff.minesweeper.settings.GameSettings;
import com.daniloff.minesweeper.strategy.BeforehandCreationStrategy;
import com.daniloff.minesweeper.strategy.FixedMineCountCreationStrategy;
import com.daniloff.minesweeper.strategy.MineFieldCreationStrategy;
import com.daniloff.minesweeper.strategy.ProbabilityCreationStrategy;

public class MineFieldImpl implements MineField {

	private static final String GAME_GOES = "Game: Goes";
	// private static final int DEFAULT_MINES_COUNT = 12;
	private int xSize = GameSettings.xSize;
	private int ySize = GameSettings.ySize;
	final private Cell[][] cells;
	private MineFieldCreationStrategy strategy;
	private boolean gameStarted;
	private boolean gameOver;
	private boolean paused;

	private boolean gameWon;
	private int mineCount;
	private int flagsCount;
	private int correctFlags;
	private int wrongFlags;
	private double timeGameRemain = GameSettings.timeForGame;
	private double timeMoveRemain = GameSettings.timeForMove;
	private int moveCount;
	private int pauseRemain = GameSettings.pauseCount;
	private MineFieldImage image;

	public MineFieldImage getImage() {
		return image;
	}

	public void setImage(MineFieldImage image) {
		this.image = image;
	}

	// public int getXSize() {
	// return xSize;
	// }
	//
	// public int getYSize() {
	// return ySize;
	// }

	public Cell[][] getCells() {
		return cells;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isPaused() {
		return paused;
	}

	public double getTimeMoveRemain() {
		return timeMoveRemain;
	}

	public void setTimeMoveRemain(double timeMoveRemain) {
		this.timeMoveRemain = timeMoveRemain;
	}

	public double getTimeGameRemain() {
		return timeGameRemain;
	}

	public void setTimeGameRemain(double timeGameRemain) {
		this.timeGameRemain = timeGameRemain;
	}

	public MineFieldImpl(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;

		cells = new Cell[xSize][ySize];
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				Cell cell = new Cell();
				cells[x][y] = cell;
			}
		}
	}

	private void applyStrategy(int xInit, int yInit) {
		boolean[][] mines;

		// int strategySwitch = 2;

		String strategySwitch = GameSettings.strategy;
		switch (strategySwitch) {
		case "ProbabilityCreationStrategy":
			strategy = new ProbabilityCreationStrategy(GameSettings.minesProbability);
			break;
		case "FixedMineCountCreationStrategy":
			strategy = new FixedMineCountCreationStrategy(GameSettings.minesCount);
			break;
		case "BeforehandCreationStrategy":
			strategy = new BeforehandCreationStrategy();
			break;
		default:
			System.out.println("Invalid strategy");
			break;
		}

		mines = strategy.createMineField(xSize, ySize, xInit, yInit);

		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				cells[x][y].setMined(mines[x][y]);
				if (cells[x][y].isMined())
					cells[x][y].setMark(Mark.Mine);
				else
					cells[x][y].setMark(Mark.NoMines);
			}
		}
		createConsoleMap();
		image.getGameProcessTxt().setText(GAME_GOES);
		setTimeGameRemain(timeGameRemain);
		startTimer();
	}

	private void startTimer() {
		TimeWatch timeWatch = new TimeWatch();
		timeWatch.setField(this);
		timeWatch.setImage(image);
		Thread tw = new Thread(timeWatch);
		tw.setDaemon(true);
		tw.start();
	}

	private void createConsoleMap() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (cells[x][y].isMined()) {
					System.out.print("I" + "O");
					mineCount++;
				} else {
					System.out.print("I ");
				}
			}
			System.out.print("I");
			System.out.println();
		}
		System.out.println("MineCount: " + mineCount);
	}

	@Override
	public void step(int x, int y) {
		if (!gameStarted) {
			gameStarted = true;
			applyStrategy(x, y);
		}
		setTimeMoveRemain(timeMoveRemain);

		cells[x][y].setShown(true);
		moveCount++;
		showMoveCount();

		if (!cells[x][y].isMined()) {
			int minesAround = countMinesAround(x, y);
			if (minesAround == 0)
				cells[x][y].setMark(Mark.NoMines);
			else
				cells[x][y].setMark(Mark.valueOf(minesAround));
			if (minesAround == 0) {
				openAdjacentCells(x, y);
			}
		} else {
			gameOver(x, y);
		}
		if (paused)
			pause();
	}

	private void showMoveCount() {
		image.getMoveCountTxt().setText(String.format("%s%2d", "Moves: ", moveCount));
	}

	private void showFlagsCount() {
		image.getFlagsCountTxt().setText(String.format("%s%2d", "Flags: ", flagsCount));
	}

	private void openAdjacentCells(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i >= 0 && i < xSize && j >= 0 && j < ySize) {
					int minesAround = countMinesAround(i, j);
					if (minesAround == 0 && !cells[i][j].isShown()) {
						cells[i][j].setMark(Mark.NoMines);
						cells[i][j].setShown(true);
						openAdjacentCells(i, j);
					}
					if (countMinesAround(i, j) != 0) {
						cells[i][j].setMark(Mark.valueOf(minesAround));
						cells[i][j].setShown(true);
					}
				}
			}
		}
	}

	private void gameOver(int xBlast, int yBlast) {
		cells[xBlast][yBlast].setMark(Mark.Blast);
		SoundPlayer.play("sounds/Blast.au");
		gameOver();
		image.getGameProcessTxt().setText("Game: Blasted");
	}

	public void gameOver() {
		gameOver = true;
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				if (cells[x][y].isMined()) {
					if (cells[x][y].isFlagged()) {
						cells[x][y].setMark(Mark.DiscoveredMine);
						cells[x][y].setShown(true);
					} else {
						cells[x][y].setShown(true);
						cells[x][y].setMark(Mark.Mine);
					}
				}
			}
		}
		System.out.println("Game Win: " + gameWon);
		if (gameWon) {
			image.getGameProcessTxt().setText("Game: Win!");
			SoundPlayer.play("sounds/Win.au");
		} else {
			if (image.getGameProcessTxt().getText().equals("Game: Blasted"))
				return;
			image.getGameProcessTxt().setText("Game: Lose");
			SoundPlayer.play("sounds/Lose.au");
		}
	}

	int countMinesAround(int x, int y) {
		int count = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i >= 0 && i < xSize && j >= 0 && j < ySize && cells[i][j].isMined()) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public void flag(int x, int y) {
		if (!cells[x][y].isFlagged()) {
			setTimeMoveRemain(timeMoveRemain);
			moveCount++;
			flagsCount++;
			cells[x][y].setFlagged(true);
			cells[x][y].setMark(Mark.RedFlag);
			if (cells[x][y].isMined()) {
				correctFlags++;
			} else {
				wrongFlags++;
			}

		} else {
			cells[x][y].setFlagged(false);
			cells[x][y].setMark(Mark.YellowFlag);
			moveCount--;
			flagsCount--;
			if (cells[x][y].isMined()) {
				correctFlags--;
			} else {
				wrongFlags--;
			}
		}
		if (paused)
			pause();
		showMoveCount();
		showFlagsCount();
		checkGameOver();
	}

	private void checkGameOver() {
		if (correctFlags == mineCount && wrongFlags == 0) {
			gameWon = true;
			gameOver();
		}
	}

	public void pause() {

		if (!paused) {
			if (pauseRemain > 0) {
				paused = true;
				pauseRemain--;
				image.getPauseButton().setText("Resume");
				SoundPlayer.play("sounds/Pause.au");
			}
		} else {
			paused = false;
			image.getPauseButton().setText("Pause (" + pauseRemain + ")");
			try {
				SoundPlayer.play("sounds/Pause.au");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
