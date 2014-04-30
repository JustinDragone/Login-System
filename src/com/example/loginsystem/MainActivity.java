package com.example.loginsystem;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;
import android.os.AsyncTask;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends Activity  {
	TextView username;
	TextView password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		String text = intent.getStringExtra("Screen Text");
		Log.i("Main Activity", "Intent Text = " +text);

		if(text != null) {
			username = (TextView) findViewById (R.id.editText1);
			username.setText(text);
		}

		if(text != null) {
			password = (TextView) findViewById (R.id.editText1);
			password.setText(text);
		}

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment())
			.commit();
		}
	}

	public void onStart(){
		super.onStart();
		new HTTPRequestTask().execute();
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
			new HttpRequestTask().execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		private class HttpRequestTask extends AsyncTask<Void, Void, Info> {
			@Override
			protected Info doInBackground(Void... params) {
				try {
					final String url = "http://rest-service.guides.spring.io/Info";
					RestTemplate restTemplate = new RestTemplate();
					restTemplate.getMessageConverters().add(new HTTPRequestTask());
					Info greeting = restTemplate.getForObject(url, Info.class);
					return greeting;
				} 
				catch (Exception e) {
					Log.e("MainActivity", e.getMessage(), e);
				}
				return null;
			}
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}


	protected void Information(Info info) {
		TextView username = (TextView) findViewById(R.id.editText1);
		TextView password = (TextView) findViewById(R.id.editText2);
		username.setText(Info.getUsername());
		password.setText(Info.getPassword());
	}

	public void onClick(View arg0) {
		Intent login = new Intent(getApplicationContext(), Login.class);
		startActivity(login);
		finish();
	}

	final TextView login = (TextView) findViewById(R.id.editText1);
	login.setText("Welcome user:  "+ username.get("username"));
	final TextView pass = (TextView) findViewById(R.id.editText2);
	password.setText(password.get("password"));
}





