package com.daniloff.minesweeper.strategy;

import java.util.Random;


public class FixedMineCountCreationStrategy implements MineFieldCreationStrategy {
	private int mineCount;
	private Random rnd = new Random();

	public FixedMineCountCreationStrategy(int mineCount) {
		this.mineCount = mineCount;
	}

	@Override
	public boolean[][] createMineField(int xSize, int ySize, int xProhibited, int yProhibited) {

		boolean[][] minedCells = new boolean[xSize][ySize];
		int mineRemain = mineCount;
		while (mineRemain > 0) {
			int x = (int) (rnd.nextDouble() * xSize);
			int y = (int) (rnd.nextDouble() * ySize);
			if (!minedCells[x][y] && !forbiddenCell(x, y, xProhibited, yProhibited)) {
				minedCells[x][y] = true;
				mineRemain--;
			}

		}
		return minedCells;
	}

	private boolean forbiddenCell(int x, int y, int xProhibited, int yProhibited) {
		return Math.abs(x - xProhibited) <= 1 && Math.abs(y - yProhibited) <= 1;
	}
}
