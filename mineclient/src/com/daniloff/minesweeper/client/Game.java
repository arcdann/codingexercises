package com.daniloff.minesweeper.client;

import com.daniloff.minesweeper.client.field.model.MineFieldModel;
import com.daniloff.minesweeper.client.field.model.MineFieldModelImpl;
import com.daniloff.minesweeper.client.field.view.MineFieldView;
import com.daniloff.minesweeper.client.field.view.MineFieldViewImpl;
import com.daniloff.minesweeper.client.settings.GameSettings;

public class Game {

	private MineFieldView image = new MineFieldViewImpl();
	private MineFieldModel field;
	private GameSettings gameSettings;

	public Game(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public void start(GameSettings gameSettings) {
		this.gameSettings = getGameSettings();
		field = new MineFieldModelImpl(gameSettings);
		image.setField(field);
		image.setGameSettings(gameSettings);
		field.setGameSettings(gameSettings);
		image.drawMineField();

		field.setView(image);

	}

	public GameSettings getGameSettings() {
		return gameSettings;
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

}
