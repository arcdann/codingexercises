package com.daniloff.minesweeper.client.field.model;

import com.daniloff.minesweeper.client.field.view.MineFieldView;
import com.daniloff.minesweeper.client.settings.GameSettings;

public interface MineFieldModel {

	void step(int x, int y);

	void flag(int x, int y);

	Cell[][] getCells();

	void gameOver();

	boolean isGameOver();

	void pause();

	public abstract void setGameSettings(GameSettings gameSettings);

	public abstract void setView(MineFieldView image);

	boolean isPaused();

	double getTimeGameRemain();

	void setTimeGameRemain(double d);

	double getTimeMoveRemain();

	void setTimeMoveRemain(double d);
}
