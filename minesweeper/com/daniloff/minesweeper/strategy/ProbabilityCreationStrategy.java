package com.daniloff.minesweeper.strategy;

import java.util.Random;


public class ProbabilityCreationStrategy implements MineFieldCreationStrategy {

	private double probability;

	private Random rnd = new Random();

	public ProbabilityCreationStrategy(double probability) {
		this.probability = probability;
	}

	@Override
	public boolean[][] createMineField(int xSize, int ySize, int xInit, int yInit) {

		boolean[][] minedCells = new boolean[xSize][ySize];
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				if (rnd.nextDouble() < probability && !forbiddenCell(x, y, xInit, yInit)) {
					minedCells[x][y] = true;
				}
			}
		}
		return minedCells;
	}

	private boolean forbiddenCell(int x, int y, int xInit, int yInit) {
		return ((Math.abs(x - xInit) <= 1) && (Math.abs(y - yInit) <= 1));
	}
}
