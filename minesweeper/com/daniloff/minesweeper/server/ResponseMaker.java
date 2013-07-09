package com.daniloff.minesweeper.server;

import com.daniloff.minesweeper.client.settings.GameSettings;
import com.daniloff.minesweeper.server.strategy.BeforehandCreationStrategy;
import com.daniloff.minesweeper.server.strategy.FixedMineCountCreationStrategy;
import com.daniloff.minesweeper.server.strategy.MineFieldCreationStrategy;
import com.daniloff.minesweeper.server.strategy.ProbabilityCreationStrategy;
import com.daniloff.minesweeper.serverAPI.Request;
import com.daniloff.minesweeper.serverAPI.Response;

public class ResponseMaker {
	Request requestIn;
	private String strategyName;
	private int xSize;
	private int ySize;
	private int xInit;
	private int yInit;
	MineFieldCreationStrategy strategy;
	Response response;
	private boolean[][] mines;

	public void prepareResponse() {
		strategyName = requestIn.getStrategy();
		xSize = requestIn.getxSize();
		ySize = requestIn.getySize();
		xInit = requestIn.getxInit();
		yInit = requestIn.getyInit();

		applyStrategy(xInit, yInit);
	}

	private void applyStrategy(int xInit, int yInit) {

		String strategySwitch = strategyName;
		// String strategySwitch = "BeforehandCreationStrategy";
		switch (strategySwitch) {
		case "ProbabilityCreationStrategy":
			strategy = new ProbabilityCreationStrategy(requestIn.getMinesProbability());
			break;
		case "FixedMineCountCreationStrategy":
			strategy = new FixedMineCountCreationStrategy(requestIn.getMinesCount());
			break;
		case "BeforehandCreationStrategy":
			strategy = new BeforehandCreationStrategy();
			break;
		default:
			System.out.println("Invalid strategy");
			break;
		}

		mines = strategy.createMineField(xSize, ySize, xInit, yInit);

		response.setMines(this.mines);
	}

	public void setRequestIn(Request requestIn) {
		this.requestIn = requestIn;
	}

	public void setMineFieldSettings(GameSettings mineFieldSettings) {
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
