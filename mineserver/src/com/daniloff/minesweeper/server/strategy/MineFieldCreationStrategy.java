package com.daniloff.minesweeper.server.strategy;

public interface MineFieldCreationStrategy {

	boolean[][] createMineField(int xSize, int ySize, int xProhibited, int yProhibited);

}