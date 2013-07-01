package com.daniloff.minesweeper.settings;

public class GameSettings {

	public static int xSize;
	public static int ySize;
	public static int pauseCount;

	public static String strategy;
	public static int minesCount;
	public static double minesProbability = 0.15;
	private double minesOccurence;

	public static double timeForMove;
	public static double timeForGame;
	private final double EPS = 0.1;
	private double rate;

	public GameSettings(String fieldSize, String strategy, String occurrence, String pace) {
		setSizes(fieldSize);
		setStrategy(strategy, occurrence);
		setTimer(pace);

	}

	private void setSizes(String fieldSize) {

		if (fieldSize.equals("Little")) {
			xSize = 10;
			ySize = 10;
		}
		if (fieldSize.equals("Normal")) {
			xSize = 20;
			ySize = 15;
			pauseCount = 1;
		}
		if (fieldSize.equals("Big")) {
			xSize = 30;
			ySize = 20;
			pauseCount = 2;
		}
		if (fieldSize.equals("Huge")) {
			xSize = 50;
			ySize = 25;
			pauseCount = 3;
		}

	}

	private void setStrategy(String strategy, String occurrence) {
		if (occurrence.equals("Rare")) {
			this.minesOccurence = 0.75;
		}
		if (occurrence.equals("Normal")) {
			this.minesOccurence = 1.0;
		}
		if (occurrence.equals("Often")) {
			this.minesOccurence = 1.5;
		}

		if (strategy.equals("Fixed mines count")) {
			GameSettings.strategy = "FixedMineCountCreationStrategy";
			minesCount = (int) (xSize * ySize * minesProbability * minesOccurence);
		}
		if (strategy.equals("Probability mining")) {
			GameSettings.strategy = "ProbabilityCreationStrategy";
			minesProbability = minesProbability * minesOccurence;
		}
	}

	private void setTimer(String pace) {
		if (pace.equals("Slow")) {
			timeForMove = 16 + EPS;
			rate = 0.7;
		}

		if (pace.equals("Normal")) {
			timeForMove = 12 + EPS;
			rate = 0.8;
		}

		if (pace.equals("Fast")) {
			timeForMove = 8 + EPS;
			rate = 0.9;
		}
		timeForGame = (int) ((timeForMove * xSize * ySize * rate) / 10) * 10 + EPS;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

}
