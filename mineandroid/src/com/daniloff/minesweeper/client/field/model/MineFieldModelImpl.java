package com.daniloff.minesweeper.client.field.model;

import java.io.IOException;

import com.daniloff.minesweeper.client.field.view.MineFieldView;
import com.daniloff.minesweeper.client.misc.SoundPlayer;
import com.daniloff.minesweeper.client.misc.TimeWatch;
import com.daniloff.minesweeper.client.settings.GameSettings;
import com.daniloff.minesweeper.serverAPI.Request;
import com.daniloff.minesweeper.serverAPI.Response;

public class MineFieldModelImpl implements MineFieldModel {

	private static final String GAME_GOES = "Game: Goes";
	private int xSize;
	private int ySize;
	final private Cell[][] cells;
	private MineFieldView image;
	private GameSettings gameSettings;
	private boolean gameStarted;
	private boolean gameOver;
	private boolean paused;
	private int pauseRemain;
	private boolean gameWon;
	private int mineCount;
	private int flagsCount;
	private int correctFlags;
	private int wrongFlags;
	private double timeGameRemain;
	private double timeMoveRemain;
	private int moveCount;
	private boolean noTimeLimit;
	private String sound;

	private Request request;
	private Response response;
	boolean[][] mines;

	public MineFieldModelImpl(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.xSize = gameSettings.getXSize();
		this.ySize = gameSettings.getYSize();
		this.pauseRemain = gameSettings.getPauseCount();

		cells = new Cell[xSize][ySize];
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				Cell cell = new Cell();
				cells[x][y] = cell;
			}
		}
	}

	private void arrangeMines(int xInit, int yInit) {

		 MiningClient client = new MiningClient();
		 try {
		 client.connectToServer();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		
		 prepareRequest(xInit, yInit);
		
		 client.request = this.request;
		
		 try {
		 client.outputStream.writeObject(request);
		 } catch (IOException e1) {
		 e1.printStackTrace();
		 }
		
		 try {
		 response = client.getResponse();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }

		 mines = response.getMinedCells();

//		boolean[][] mines = new boolean[xSize][ySize];
//
//		mines[1][2] = true;
//		mines[4][6] = true;
//		mines[5][9] = true;
//		mines[3][3] = true;
//		mines[7][5] = true;
//		mines[8][7] = true;

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
		image.setGameProcessTxt(GAME_GOES);
		setTimeGameRemain(gameSettings.getTimeForGame());
		setNoTimeLimit(gameSettings.isNoTimeLimit());
		if (!noTimeLimit) {
			startTimer();
		}
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

	public String stub() {
		return "Stub String Return";

	}

	@Override
	public void step(int x, int y) {
		if (!gameStarted) {
			gameStarted = true;
			arrangeMines(x, y);
		}
		setTimeMoveRemain(gameSettings.getTimeForMove());
		if (noTimeLimit) {
			image.setTimeGameRemainTxt("No gametime limit");
			image.setTimeMoveRemainTxt("No movetime limit");
		}

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

	private void prepareRequest(int x, int y) {
		request = new Request();
		request.setxInit(x);
		request.setyInit(y);
		request.setxSize(xSize);
		request.setySize(ySize);
		// request.setStrategy(gameSettings.getStrategy());
		request.setStrategy("FixedMineCountCreationStrategy");
		request.setMinesCount(gameSettings.getMinesCount());
		request.setMinesProbability(gameSettings.getMinesProbability());
	}

	@Override
	public void flag(int x, int y) {
		if (!cells[x][y].isFlagged()) {
			setTimeMoveRemain(gameSettings.getTimeForMove());
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

	private void showMoveCount() {
		image.setMoveCount(moveCount);
	}

	private void showFlagsCount() {
		image.setFlagsCount(flagsCount);
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
		sound = "resources/sounds/Blast.au";
		gameOver();
		image.setGameProcessTxt("Game: Blasted");
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
						cells[x][y].setMark(Mark.Mine);
						cells[x][y].setShown(true);
					}
				}
			}
		}
		System.out.println("Game Win: " + gameWon);
		if (gameWon) {
			image.setGameProcessTxt("Game: Win!");
			sound = "resources/sounds/Win.au";
		} else {
			if (sound == null) {
				sound = "resources/sounds/Lose.au";
			}
		}
		soundHelper(sound);

		try {
			image.redrawMineField();
		} catch (IOException e) {
			e.printStackTrace();
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

	private void checkGameOver() {
		if (correctFlags == mineCount && wrongFlags == 0) {
			gameWon = true;
			gameOver();
		}
	}

	@Override
	public void pause() {

		if (!paused) {
			if (pauseRemain > 0) {
				paused = true;
				pauseRemain--;
				image.setPauseButtonTxt("Resume");
				sound = "resources/sounds/Pause.au";
				soundHelper(sound);
			}
		} else {
			paused = false;
			image.setPauseButtonTxt("Pause (" + pauseRemain + ")");
			sound = "resources/sounds/Pause.au";
			soundHelper(sound);
		}
	}

	private void soundHelper(String sound) {
		this.sound = sound;
		SoundPlayer.play(sound);
		sound = null;
	}

	public MineFieldView getImage() {
		return image;
	}

	@Override
	public void setView(MineFieldView image) {
		this.image = image;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isPaused() {
		return paused;
	}

	public double getTimeGameRemain() {
		return timeGameRemain;
	}

	public double getTimeMoveRemain() {
		return timeMoveRemain;
	}

	public void setTimeGameRemain(double timeGameRemain) {
		this.timeGameRemain = timeGameRemain;
	}

	public void setTimeMoveRemain(double timeMoveRemain) {
		this.timeMoveRemain = timeMoveRemain;
	}

	public GameSettings getGameSettings() {
		return gameSettings;
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public void setNoTimeLimit(boolean noTimeLimit) {
		this.noTimeLimit = noTimeLimit;
	}
}
