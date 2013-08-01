package com.daniloff.minesweeper.client.settings;

import java.util.HashMap;
import java.util.Map;

import com.daniloff.minesweeper.android.R;
import com.daniloff.minesweeper.client.field.model.Mark;

public class ResourceResolver {
	public static Map<Mark, Integer> MARK_TO_INT = new HashMap<Mark, Integer>();

	static {
		MARK_TO_INT.put(Mark.digit_one, R.drawable.one);
		MARK_TO_INT.put(Mark.digit_two, R.drawable.two);
		MARK_TO_INT.put(Mark.digit_three, R.drawable.three);
		MARK_TO_INT.put(Mark.digit_four, R.drawable.four);
//		MARK_TO_INT.put(Mark.Digit5, "resources/images/5_25.png");
//		MARK_TO_INT.put(Mark.Digit6, "resources/images/6_25.png");
//		MARK_TO_INT.put(Mark.Digit7, "resources/images/7_25.png");
//		MARK_TO_INT.put(Mark.Digit8, "resources/images/8_25.png");

		MARK_TO_INT.put(Mark.blasted, R.drawable.blasted);
		MARK_TO_INT.put(Mark.mine, R.drawable.mine);
		MARK_TO_INT.put(Mark.discovered_mine, R.drawable.discovered_mine);
		MARK_TO_INT.put(Mark.empty, R.drawable.empty);
		MARK_TO_INT.put(Mark.red_flag, R.drawable.redflag);
		MARK_TO_INT.put(Mark.yellow_flag, R.drawable.yellowflag);

	}
}
