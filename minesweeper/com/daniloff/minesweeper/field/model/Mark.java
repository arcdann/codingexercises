package com.daniloff.minesweeper.field.model;

public enum Mark {
	Mine, NoMines, RedFlag, YellowFlag, DiscoveredMine, Blast, Digit1, Digit2, Digit3, Digit4, Digit5, Digit6, Digit7, Digit8;

	public static Mark valueOf(int digit) {
		if (digit == 1) {
			return Mark.Digit1;
		} else if (digit == 2) {
			return Mark.Digit2;
		} else if (digit == 3) {
			return Mark.Digit3;
		} else if (digit == 4) {
			return Mark.Digit4;
		} else if (digit == 5) {
			return Mark.Digit5;
		} else if (digit == 6) {
			return Mark.Digit6;
		} else if (digit == 7) {
			return Mark.Digit7;
		} else if (digit == 8) {
			return Mark.Digit8;
		} else {
			throw new RuntimeException("No mark for digit " + digit);
		}
	}
}
