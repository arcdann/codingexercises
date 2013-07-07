package com.daniloff.minesweeper.client.settings;

import java.util.HashMap;
import java.util.Map;

import com.daniloff.minesweeper.client.field.model.Mark;

public class FilenameResolver {
	public static Map<Mark, String> MARK_TO_FILENAME = new HashMap<>();

	static {
		MARK_TO_FILENAME.put(Mark.Digit1, "images/1_25.png");
		MARK_TO_FILENAME.put(Mark.Digit2, "images/2_25.png");
		MARK_TO_FILENAME.put(Mark.Digit3, "images/3_25.png");
		MARK_TO_FILENAME.put(Mark.Digit4, "images/4_25.png");
		MARK_TO_FILENAME.put(Mark.Digit5, "images/5_25.png");
		MARK_TO_FILENAME.put(Mark.Digit6, "images/6_25.png");
		MARK_TO_FILENAME.put(Mark.Digit7, "images/7_25.png");
		MARK_TO_FILENAME.put(Mark.Digit8, "images/8_25.png");

		MARK_TO_FILENAME.put(Mark.Blast, "images/Blast_25.png");
		MARK_TO_FILENAME.put(Mark.Mine, "images/Mine_25.png");
		MARK_TO_FILENAME.put(Mark.DiscoveredMine, "images/DiscoveredMine_25.png");
		MARK_TO_FILENAME.put(Mark.NoMines, "images/NoMines_25.png");
		MARK_TO_FILENAME.put(Mark.RedFlag, "images/Redflag_25.png");

		MARK_TO_FILENAME.put(Mark.YellowFlag, "images/Yellowflag_25.png");

	}
}
