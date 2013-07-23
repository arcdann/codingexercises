package com.daniloff.minesweeper.client;

import com.daniloff.minesweeper.client.invite.InviteDialog;

public class ClientBootstrap {

	public static void main(String[] args) {
		InviteDialog dialog = new InviteDialog();
		dialog.init();
	}
}
