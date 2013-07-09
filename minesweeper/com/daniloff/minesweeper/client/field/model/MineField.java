package com.daniloff.minesweeper.client.field.model;

interface MineField {

	void step(int x, int y);

	void flag(int x, int y);

	Cell[][] getCells();

	void gameOver();

	boolean isGameOver();
}
