package com.daniloff.minesweeper.client.field.model;

public class Cell {
	private boolean mined;
	private boolean flagged;
	private boolean shown;
	private Mark mark;

	public boolean isMined() {
		return mined;
	}

	public void setMined(boolean mined) {
		this.mined = mined;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public boolean isShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}

	public String getMark() {
			return mark.toString();
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

}
