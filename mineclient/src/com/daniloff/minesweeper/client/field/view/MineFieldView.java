package com.daniloff.minesweeper.client.field.view;

import java.io.IOException;

import com.daniloff.minesweeper.client.field.model.MineFieldModel;
import com.daniloff.minesweeper.client.settings.GameSettings;

public interface MineFieldView {

	void setGameProcessTxt(String gameGoes);

	void setTimeGameRemainTxt(String string);

	void setTimeMoveRemainTxt(String string);

	void setMoveCount(int count);

	void setFlagsCount(int count);

	void setPauseButtonTxt(String string);

	void redrawMineField() throws IOException;

	boolean isStopTimeWatch();

	void setGameSettings(GameSettings gameSettings);

	void drawMineField();

	void setField(MineFieldModel field);

}