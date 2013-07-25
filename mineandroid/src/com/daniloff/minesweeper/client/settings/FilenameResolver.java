package com.daniloff.minesweeper.client.settings;

import java.util.HashMap;
import java.util.Map;

import com.daniloff.minesweeper.client.field.model.Mark;

public class FilenameResolver {
	public static Map<Mark, String> MARK_TO_FILENAME = new HashMap<Mark, String>();

	static {
		MARK_TO_FILENAME.put(Mark.Digit1, "resources/images/1_25.png");
		MARK_TO_FILENAME.put(Mark.Digit2, "resources/images/2_25.png");
		MARK_TO_FILENAME.put(Mark.Digit3, "resources/images/3_25.png");
		MARK_TO_FILENAME.put(Mark.Digit4, "resources/images/4_25.png");
		MARK_TO_FILENAME.put(Mark.Digit5, "resources/images/5_25.png");
		MARK_TO_FILENAME.put(Mark.Digit6, "resources/images/6_25.png");
		MARK_TO_FILENAME.put(Mark.Digit7, "resources/images/7_25.png");
		MARK_TO_FILENAME.put(Mark.Digit8, "resources/images/8_25.png");

		MARK_TO_FILENAME.put(Mark.Blast, "resources/images/Blast_25.png");
		MARK_TO_FILENAME.put(Mark.Mine, "resources/images/Mine_25.png");
		MARK_TO_FILENAME.put(Mark.DiscoveredMine, "resources/images/DiscoveredMine_25.png");
		MARK_TO_FILENAME.put(Mark.NoMines, "resources/images/NoMines_25.png");
		MARK_TO_FILENAME.put(Mark.RedFlag, "resources/images/Redflag_25.png");

		MARK_TO_FILENAME.put(Mark.YellowFlag, "resources/images/Yellowflag_25.png");

	}
}
