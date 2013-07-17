package com.daniloff.mibesweeper.android;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daniloff.minesweeper.client.field.model.MineFieldLogic;
import com.daniloff.minesweeper.client.settings.GameSettings;

public class MainActivity extends Activity {

	private final int X = 10;
	private final int Y = 10;
	private boolean flagButtonPressed;
	private int screenWidth;
	private int screenHeight;
	private final Button[][] buttons = new Button[X][Y];
	public MineFieldLogic field;
	public GameSettings gameSettings;
	TextView topText;
	OnClickListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		topText = (TextView) findViewById(R.id.topText);
		// topText.setText("Minesweeper Android top text");

		Display display = getWindowManager().getDefaultDisplay();

		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		topText.setText("screen " + screenWidth + " x " + screenHeight);
		listenButton();
		drawMineField();

	}

	private void listenButton() {
		listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				int id = v.getId();
				int x = id % 100;
				int y = (id - x)/100;
				topText.setText("buttons[" + x + "][" + y + "] stepped");
//				v.setBackgroundColor(0);
				buttons[x][y].setText("#");
//				buttons[x][y].setBackgroundColor(Color.BLUE);
			}
		};

	}

	public void drawMineField() {

		TableLayout tl = (TableLayout) findViewById(R.id.tableLayout);

		for (int y = 0; y < Y; y++) {
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			for (int x = 0; x < X; x++) {

				buttons[x][y] = new Button(this);

				buttons[x][y].setWidth(screenWidth / X - 5);
				buttons[x][y].setHeight(buttons[x][y].getWidth());
//				buttons[x][y].setBackgroundColor(Color.GREEN);
				final int xInner = x;
				final int yInner = y;

				int id = y * 100 + x;
				buttons[x][y].setId(id);
				buttons[x][y].setOnClickListener(listener);

				// buttons[x][y].setOnClickListener(new OnClickListener() {
				// @Override
				// public void onClick(View v) {
				// if (!flagButtonPressed) {
				// topText.setText("buttons[" + xInner + "][" + yInner +
				// "] stepped");
				// buttons[xInner][yInner].setText("#");
				//
				// // field.step(xInner, yInner);
				//
				// // String stubText = field.stub();
				//
				// // topText.setText(stubText);
				//
				// } else {
				// topText.setText("buttons[" + xInner + "][" + yInner +
				// "] flagged");
				// flagButtonPressed = false;
				// buttons[xInner][yInner].setText("F");
				// }
				// }
				// });
				tr.addView(buttons[x][y]);
			}
			tl.addView(tr);
		}

		Button newGameButton = (Button) findViewById(R.id.newGameButton);
		newGameButton.setText("New game");
		newGameButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				topText.setText("New game button pressed");
			}
		});

		Button restartButton = (Button) findViewById(R.id.restartButton);
		restartButton.setText("Restart");
		restartButton.setBackgroundColor(Color.BLUE);
		restartButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				topText.setText("Restart button pressed");
			}
		});

		Button pauseButton = (Button) findViewById(R.id.pauseButton);
		pauseButton.setText("Pause");
		pauseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				topText.setText("Pause button pressed");
			}
		});

		Button flagButton = (Button) findViewById(R.id.flagButton);
		flagButton.setText("Flag");
		flagButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!flagButtonPressed) {
					topText.setText("Flag button pressed");
					flagButtonPressed = true;
				} else {
					topText.setText("Flag button unpressed");
					flagButtonPressed = false;
				}
			}
		});

	}

	public MineFieldLogic getField() {
		return field;
	}

	public void redrawMineField() {

		for (int x = 0; x < X; x++) {
			for (int y = 0; y < Y; y++) {

				if (field.getCells()[x][y].isShown()) {

					buttons[x][y].setText("+");
				}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
