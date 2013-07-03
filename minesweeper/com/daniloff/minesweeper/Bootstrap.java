package com.daniloff.minesweeper;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.daniloff.minesweeper.invite.InviteDialog;

public class Bootstrap {

	public static void main(String[] args) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		InviteDialog dialog = new InviteDialog();
		dialog.setScreen(screen);
		dialog.init();
	}
}
