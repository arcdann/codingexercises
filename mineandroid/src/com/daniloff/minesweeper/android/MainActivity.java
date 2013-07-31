package com.daniloff.minesweeper.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daniloff.minesweeper.android.R.drawable;
import com.daniloff.minesweeper.client.field.model.MineFieldModel;
import com.daniloff.minesweeper.client.field.model.MineFieldModelImpl;
import com.daniloff.minesweeper.client.field.view.MineFieldView;
import com.daniloff.minesweeper.client.settings.GameSettings;

public class MainActivity extends Activity implements OnClickListener, MineFieldView {

	private int X;
	private int Y;
	private boolean flagButtonPressed;
	private int screenWidth;
	private int screenHeight;
	private ImageButton[][] buttons;
	public MineFieldModel field;
	public GameSettings gameSettings;
	TextView topText;
	TextView gameProcessTxt;
	OnClickListener listener;
	// private TextView timeGameRemainTxt;
	// private TextView timeMoveRemainTxt;
	// private TextView moveCountTxt;
	// private TextView flagsCountTxt;

	private Button pauseButton;
	private boolean stopTimeWatch;
	private Context mContext;
	ImageButton flagButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		topText = (TextView) findViewById(R.id.topText);
		// topText.setText("Minesweeper Android top text");

		DisplayMetrics metrics = getResources().getDisplayMetrics();

		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		topText.setText("screen " + screenWidth + " x " + screenHeight);
		listenButton();
		gameSettings = new GameSettings("Normal", "Fixed mines count", "Normal", "No Limit");
		field = new MineFieldModelImpl(gameSettings);
		field.setView(this);
		X = gameSettings.getXSize();
		Y = gameSettings.getYSize();
		buttons = new ImageButton[X][Y];
		topText.setText("X= " + X + " Y= " + Y);
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
				// v.setBackgroundColor(Color.RED);

			} else {
				topText.setText("Flag button unpressed");
				flagButtonPressed = false;
				// v.setBackgroundColor(Color.CYAN);
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
				int x = id % Y;
				int y = (id - x) / Y;
				ImageButton b = (ImageButton) v;
				if (!flagButtonPressed) {
					topText.setText("buttons[" + x + "][" + y + "] stepped");
					// v.setBackgroundColor(Color.BLUE);
					// buttons[x][y].setText("#");
					b.setImageResource(R.drawable.empty);// //////////////////

					field.step(x, y);
					redrawMineField();
					// String stubText = field.stub();
					// topText.setText(stubText);

				} else {
					topText.setText("buttons[" + x + "][" + y + "] flagged");
					b.setImageResource(R.drawable.redflag);
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

				buttons[x][y] = new ImageButton(this);// *************************************
				ImageButton b = buttons[x][y];
				
				Uri imgUri=Uri.parse("android.resource://com.daniloff.minesweeper.android/2130837505");
				
				System.out.println(R.drawable.empty);
				
				b.setImageURI(imgUri);
				int id = y * Y + x;
				b.setId(id);
				b.setOnClickListener(listener);
				tr.addView(b);
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

		// Button pauseButton = (Button) findViewById(R.id.pauseButton);
		// pauseButton.setText("Pause");
		// setPauseButtonTxt("pause");
		// pauseButton.setOnClickListener(this);

		// Button flagButton = (Button) findViewById(R.id.flagButton);
		// flagButton.setText("Flag");
		// flagButton.setBackgroundColor(Color.CYAN);
		// flagButton.setOnClickListener(this);

		// Button button1 = (Button) findViewById(R.id.);

		flagButton = (ImageButton) findViewById(R.id.flagButton);
		flagButton.setOnClickListener(this);

	}

	public void redrawMineField() {

		for (int x = 0; x < X; x++) {
			for (int y = 0; y < Y; y++) {

				if (field.getCells()[x][y].isShown()) {

					// buttons[x][y].setText(field.getCells()[x][y].getMark().toString());
					
//					buttons[x][y].setImageLevel(R.drawable.empty);
				}
			}
		}
	}

	@Override
	public void setGameProcessTxt(String gameGoes) {
		// gameProcessTxt.setText(gameGoes);
		// gameProcessTxt.setText("gameGoes");
	}

	@Override
	public void setTimeGameRemainTxt(String string) {
		// timeGameRemainTxt.setText(string);
		// timeGameRemainTxt.setText("string");
	}

	@Override
	public void setTimeMoveRemainTxt(String string) {
		// timeMoveRemainTxt.setText(string);
		// timeMoveRemainTxt.setText("string");
	}

	@Override
	public void setMoveCount(int count) {
		// moveCountTxt.setText(String.format("%s%2d", "Moves: ", count));
		// moveCountTxt.setText(String.format("%s%2d", "Moves: ", 22));
	}

	@Override
	public void setFlagsCount(int count) {
		// flagsCountTxt.setText(String.format("%s%2d", "Flags: ", count));
		// flagsCountTxt.setText(String.format("%s%2d", "Flags: ", 11));
	}

	@Override
	public void setPauseButtonTxt(String string) {
		pauseButton.setText(string);
		// pauseButton.setText("string");
	}

	@Override
	public boolean isStopTimeWatch() {
		// return stopTimeWatch;
		return false;
	}

	@Override
	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	@Override
	public void setField(MineFieldModel field) {
		this.field = field;
	}

}
