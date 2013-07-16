package com.daniloff.mibesweeper.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int X = 10;
	private final int Y = 10;
	private boolean flagButtonPressed;
	private int screenWidth;
	private int screenHeight;
	private final Button[][] buttons=new Button[X][Y];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView topText = (TextView) findViewById(R.id.topText);
		// topText.setText("Minesweeper Android top text");

		Display display = getWindowManager().getDefaultDisplay();

		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		topText.setText("screenWidth =" + screenWidth + " screenHeight =" + screenHeight);
		
		
//		buttons=new Button[X][Y];
		
		TableLayout tl = (TableLayout) findViewById(R.id.tableLayout);
		
		for (int y = 0; y < Y; y++) {
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			for (int x = 0; x < X; x++) {
				
				buttons[x][y]=new Button(this);
				
//				final Button button = new Button(this);
				
				buttons[x][y].setWidth(screenWidth / X - 4);
				buttons[x][y].setHeight(buttons[x][y].getHeight());
				final int xInner = x;
				final int yInner = y;
				buttons[x][y].setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!flagButtonPressed) {
							topText.setText("buttons[" + xInner + "][" + yInner + "] stepped");
							buttons[xInner][yInner].setText("#");
						} else {
							topText.setText("buttons[" + xInner + "][" + yInner + "] flagged");
							flagButtonPressed = false;
							buttons[xInner][yInner].setText("F");
						}
					}
				});
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
