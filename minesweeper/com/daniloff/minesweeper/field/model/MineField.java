package com.daniloff.minesweeper.field.model;

interface MineField {

	// int getXSize();
	//
	// int getYSize();

	void step(int x, int y);

	void flag(int x, int y);

	Cell[][] getCells();

	void gameOver();

	boolean isGameOver();
}
