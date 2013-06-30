package com.daniloff.minesweeper;

import com.daniloff.minesweeper.field.model.MineFieldImpl;
import com.daniloff.minesweeper.field.view.MineFieldImage;

public class Game {

	private MineFieldImage image = new MineFieldImage();
	private MineFieldImpl field;

	public void start(int x, int y) {
		field = new MineFieldImpl(x, y);
		image.setField(field);
		image.drawMineField();

		field.setImage(image);
	}

}
