package com.haradaatsushi.parsesample;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Parse.initialize(this, "xxxxxxxxxxxxxxxxxxxxxxxxxxxx", "oooooooooooooooooooooooooooo");

		// ParseObject testObject = new ParseObject("TestObject");
		// testObject.put("foo", "bar");
		// testObject.saveInBackground();

		ParseObject itemObject = new ParseObject("Item");
		itemObject.put("name", "eraser");
		itemObject.put("price", "200");
		itemObject.put("sales_enabled", true);
		itemObject.saveInBackground();

		Button getButton = (Button) findViewById(R.id.get_button);
		getButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> itemList, ParseException e) {
				Log.d("MainActivity", "done");
				if (e == null) {
					for (ParseObject object : itemList) {
						String name = object.getString("name");
						int price = object.getInt("price");
						boolean salesEnabled = object.getBoolean("sales_enabled");
						Log.d("MainActivity", "name => " + name);
						Log.d("MainActivity", "price => " + price);
						Log.d("MainActivity", "sales_enabled => " + salesEnabled);
					}
				} else {
					// something went wrong
				}
			}
		});
	}
}
