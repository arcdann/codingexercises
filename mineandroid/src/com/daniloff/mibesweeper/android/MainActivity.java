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

public class MainActivity extends Activity implements OnClickListener {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// listen control buttons
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.newGameButton:
			topText.setText("New game button pressed");
			break;
		case R.id.restartButton:
			topText.setText("Restart button pressed");
			break;
		case R.id.pauseButton:
			topText.setText("Pause button pressed");
			break;
		case R.id.flagButton:
			if (!flagButtonPressed) {
				topText.setText("Flag button pressed");
				flagButtonPressed = true;
				v.setBackgroundColor(Color.RED);
			} else {
				topText.setText("Flag button unpressed");
				flagButtonPressed = false;
				v.setBackgroundColor(Color.CYAN);
			}
			break;
		}
	}

	// listen play buttons
	private void listenButton() {
		listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				int id = v.getId();
				int x = id % 100;
				int y = (id - x) / 100;
				if (!flagButtonPressed) {
					topText.setText("buttons[" + x + "][" + y + "] stepped");
					// v.setBackgroundColor(Color.BLUE);
					buttons[x][y].setText("#");

					// field.step(xInner, yInner);
					// String stubText = field.stub();
					// topText.setText(stubText);

				} else {
					topText.setText("buttons[" + x + "][" + y + "] flagged");
					buttons[x][y].setText("F");
					flagButtonPressed = false;
					findViewById(R.id.flagButton).setBackgroundColor(Color.CYAN);
				}
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
				// buttons[x][y].setBackgroundColor(Color.GREEN);
				int id = y * 100 + x;
				buttons[x][y].setId(id);
				buttons[x][y].setOnClickListener(listener);
				tr.addView(buttons[x][y]);
			}
			tl.addView(tr);
		}

		Button newGameButton = (Button) findViewById(R.id.newGameButton);
		newGameButton.setText("New game");
		newGameButton.setOnClickListener(this);

		Button restartButton = (Button) findViewById(R.id.restartButton);
		restartButton.setText("Restart");
		restartButton.setBackgroundColor(Color.BLUE);
		restartButton.setOnClickListener(this);

		Button pauseButton = (Button) findViewById(R.id.pauseButton);
		pauseButton.setText("Pause");
		pauseButton.setOnClickListener(this);

		Button flagButton = (Button) findViewById(R.id.flagButton);
		flagButton.setText("Flag");
		flagButton.setBackgroundColor(Color.CYAN);
		flagButton.setOnClickListener(this);

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

}
