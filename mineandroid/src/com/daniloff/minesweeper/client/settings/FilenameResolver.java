package com.daniloff.minesweeper.client.settings;

import java.util.HashMap;
import java.util.Map;

import com.daniloff.minesweeper.client.field.model.Mark;

public class FilenameResolver {
	public static Map<Mark, String> MARK_TO_FILENAME = new HashMap<Mark, String>();

	static {
		MARK_TO_FILENAME.put(Mark.digit_one, "resources/images/1_25.png");
		MARK_TO_FILENAME.put(Mark.digit_two, "resources/images/2_25.png");
		MARK_TO_FILENAME.put(Mark.digit_three, "resources/images/3_25.png");
		MARK_TO_FILENAME.put(Mark.digit_four, "resources/images/4_25.png");
		MARK_TO_FILENAME.put(Mark.Digit5, "resources/images/5_25.png");
		MARK_TO_FILENAME.put(Mark.Digit6, "resources/images/6_25.png");
		MARK_TO_FILENAME.put(Mark.Digit7, "resources/images/7_25.png");
		MARK_TO_FILENAME.put(Mark.Digit8, "resources/images/8_25.png");

		MARK_TO_FILENAME.put(Mark.blasted, "resources/images/Blast_25.png");
		MARK_TO_FILENAME.put(Mark.mine, "resources/images/Mine_25.png");
		MARK_TO_FILENAME.put(Mark.discovered_mine, "resources/images/DiscoveredMine_25.png");
		MARK_TO_FILENAME.put(Mark.empty, "resources/images/NoMines_25.png");
		MARK_TO_FILENAME.put(Mark.red_flag, "resources/images/Redflag_25.png");

		MARK_TO_FILENAME.put(Mark.yellow_flag, "resources/images/Yellowflag_25.png");

	}
}
