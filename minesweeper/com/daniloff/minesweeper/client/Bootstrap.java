package com.daniloff.minesweeper.client;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.daniloff.minesweeper.client.invite.InviteDialog;

public class Bootstrap {

	public static void main(String[] args) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		InviteDialog dialog = new InviteDialog();
		dialog.setScreen(screen);
		dialog.init();
	}
}
