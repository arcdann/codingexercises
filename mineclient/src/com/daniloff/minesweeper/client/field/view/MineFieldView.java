package com.daniloff.minesweeper.client.field.view;

import java.io.IOException;

import com.daniloff.minesweeper.client.field.model.MineFieldModel;
import com.daniloff.minesweeper.client.settings.GameSettings;

public interface MineFieldView {

	void setGameProcessTxt(String gameGoes);

	void setTimeGameRemainTxt(String string);

	void setTimeMoveRemainTxt(String string);

	void setMoveCountTxt(String format, String string, int count);

	void setFlagsCountTxt(String format, String string, int count);

	void setPauseButtonTxt(String string);

	void redrawMineField() throws IOException;

	// void setImage(MineFieldImage image);

	boolean isStopTimeWatch();

	// void setField(MineField field);

	void setGameSettings(GameSettings gameSettings);

	public abstract void drawMineField();

	public abstract void setField(MineFieldModel field);

}