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
		switch (mark) {
		case NoMines:
			return ".";
		case Blast:
			return "X";
		case Digit1:
			return "1";
		case Digit2:
			return "2";
		case Digit3:
			return "3";
		case Digit4:
			return "4";
		case RedFlag:
			return "F";
		case YellowFlag:
			return "?";
		case Mine:
			return "*";
		case DiscoveredMine:
			return "#";
		default:
			return mark.toString();
		}
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

}
