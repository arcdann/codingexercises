package com.daniloff.minesweeper.serverAPI;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Response implements Serializable {
	private boolean[][] mines;

	public boolean[][] getMinedCells() {
		return mines;
	}

	public void setMines(boolean[][] minedCells) {
		this.mines = minedCells;
	}

}
