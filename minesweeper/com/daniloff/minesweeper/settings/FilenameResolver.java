package com.daniloff.minesweeper.settings;

import java.util.HashMap;
import java.util.Map;

import com.daniloff.minesweeper.field.model.Mark;

public class FilenameResolver {
	public static Map<Mark, String> MARK_TO_FILENAME = new HashMap<>();

	static {
		MARK_TO_FILENAME.put(Mark.One, "images/1_25.png");
		MARK_TO_FILENAME.put(Mark.Two, "images/2_25.png");
		MARK_TO_FILENAME.put(Mark.Three, "images/3_25.png");
		MARK_TO_FILENAME.put(Mark.Four, "images/4_25.png");

		MARK_TO_FILENAME.put(Mark.Blast, "images/Blast_25.png");
		MARK_TO_FILENAME.put(Mark.Mine, "images/Mine_25.png");
		MARK_TO_FILENAME.put(Mark.DiscoveredMine, "images/DiscoveredMine_25.png");
		MARK_TO_FILENAME.put(Mark.NoMines, "images/NoMines_25.png");
		MARK_TO_FILENAME.put(Mark.RedFlag, "images/Redflag_25.png");

		MARK_TO_FILENAME.put(Mark.YellowFlag, "images/Yellowflag_25.png");

	}
}
