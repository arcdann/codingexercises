package com.daniloff.minesweeper.client;

import com.daniloff.minesweeper.client.field.model.MineFieldLogic;
import com.daniloff.minesweeper.client.field.view.MineFieldImage;
import com.daniloff.minesweeper.client.settings.GameSettings;

public class Game {

	private MineFieldImage image = new MineFieldImage();
	private MineFieldLogic field;
	private GameSettings gameSettings;

	public Game(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public void start(GameSettings gameSettings) {
		this.gameSettings = getGameSettings();
		field = new MineFieldLogic(gameSettings);
		image.setField(field);
		image.setGameSettings(gameSettings);
		field.setGameSettings(gameSettings);
		image.drawMineField();

		field.setImage(image);

	}

	public GameSettings getGameSettings() {
		return gameSettings;
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

}
