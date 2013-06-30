package com.daniloff.minesweeper.strategy;

public interface MineFieldCreationStrategy {

	boolean[][] createMineField(int xSize, int ySize, int xProhibited, int yProhibited);

}