package com.daniloff.mibesweeper.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private final int X=10;
	private final int Y=10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView topText = (TextView) findViewById(R.id.topText);
		topText.setText("Minesweeper Android top text");
		
	TableLayout tl = (TableLayout) findViewById(R.id.tableLayout);

	for (int y=0;y<Y;y++){
		TableRow tr = new TableRow(this);
		tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,Y));
		for(int x=0;x<X;x++){
			Button button = new Button(this);
			button.setText(x+" "+y);
			tr.addView(button);
		}
		tl.addView(tr);
	}
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
