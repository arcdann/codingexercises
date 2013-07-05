package com.daniloff.minesweeper.misc;

import com.daniloff.minesweeper.field.model.MineFieldImpl;
import com.daniloff.minesweeper.field.view.MineFieldImage;

public class TimeWatch implements Runnable {
	private final double EPS = 0.001;
	private MineFieldImage image;
	private MineFieldImpl field;

	public void setField(MineFieldImpl field) {
		this.field = field;
	}

	public void setImage(MineFieldImage image) {
		this.image = image;
	}

	@Override
	public void run() {

		while (!field.isGameOver() && !image.isStopTimeWatch()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (!field.isPaused()) {
				field.setTimeGameRemain(field.getTimeGameRemain() - 0.1);
				field.setTimeMoveRemain(field.getTimeMoveRemain() - 0.1);
			}
			if (field.getTimeGameRemain() <= EPS || field.getTimeMoveRemain() <= EPS) {
				field.gameOver();
			}
			image.getTimeGameRemainTxt().setText(
					String.format("%s%3.1f", "Time Game Remain: ", field.getTimeGameRemain()));
			image.getTimeMoveRemainTxt().setText(
					String.format("%s%3.1f", "Time Move Remain:  ", field.getTimeMoveRemain()));
		}
	}
}
