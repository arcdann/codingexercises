package com.daniloff.minesweeper.server.strategy;


public class BeforehandCreationStrategy implements MineFieldCreationStrategy {

	@Override
	public boolean[][] createMineField(int xSize, int ySize, int xProhibited, int yProhibited) {
		boolean[][] minedCells = new boolean[xSize][ySize];

		minedCells[1][2] = true;
		minedCells[4][6] = true;
		minedCells[5][9] = true;
		minedCells[3][3] = true;
		minedCells[7][5] = true;
		minedCells[8][7] = true;

		return minedCells;
	}

}
