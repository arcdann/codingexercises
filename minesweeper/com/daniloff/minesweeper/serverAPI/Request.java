package com.daniloff.minesweeper.serverAPI;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = -5325309898871936783L;

	private String strategy;
	private int xSize;
	private int ySize;
	private int xInit;
	private int yInit;
	private int minesCount;
	private double minesProbability;

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	public int getxInit() {
		return xInit;
	}

	public void setxInit(int xInit) {
		this.xInit = xInit;
	}

	public int getyInit() {
		return yInit;
	}

	public void setyInit(int yInit) {
		this.yInit = yInit;
	}

	public int getMinesCount() {
		return minesCount;
	}

	public void setMinesCount(int mineCount) {
		this.minesCount = mineCount;
	}

	public double getMinesProbability() {
		return minesProbability;
	}

	public void setMinesProbability(double minesProbability) {
		this.minesProbability = minesProbability;
	}

}
