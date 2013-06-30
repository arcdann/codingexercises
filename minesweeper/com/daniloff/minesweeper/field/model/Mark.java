package com.daniloff.minesweeper.field.model;

public enum Mark {
	Mine, NoMines, RedFlag, YellowFlag, DiscoveredMine, Blast, One, Two, Three, Four;

	public static Mark valueOf(int digit) {
		if (digit == 1) {
			return Mark.One;
		} else if (digit == 2) {
			return Mark.Two;
		} else if (digit == 3) {
			return Mark.Three;
		} else if (digit == 4) {
			return Mark.Four;
		} else {
			throw new RuntimeException("No mark for digit " + digit);
		}
	}
}
