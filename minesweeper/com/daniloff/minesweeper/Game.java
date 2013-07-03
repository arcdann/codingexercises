package com.daniloff.minesweeper;

import com.daniloff.minesweeper.field.model.MineFieldImpl;
import com.daniloff.minesweeper.field.view.MineFieldImage;
import com.daniloff.minesweeper.settings.MineFieldSettings;

public class Game {

	private MineFieldImage image = new MineFieldImage();
	private MineFieldImpl field;
	private MineFieldSettings gameSettings;

	public Game(MineFieldSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public void start(MineFieldSettings gameSettings) {
		this.gameSettings = getGameSettings();
		field = new MineFieldImpl(gameSettings);
		image.setField(field);
		image.setGameSettings(gameSettings);
		field.setGameSettings(gameSettings);
		image.drawMineField();

		field.setImage(image);

	}

	public MineFieldSettings getGameSettings() {
		return gameSettings;
	}

	public void setGameSettings(MineFieldSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

}
