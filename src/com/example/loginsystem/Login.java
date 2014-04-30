package com.example.loginsystem;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Login extends Activity {
	EditText username;
	EditText password;
	Button login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		login = (Button) findViewById(R.id.button1);

		protected Boolean Connection(String... args){
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo Info = cm.getActiveNetworkInfo();
			if (Info != null && Info.isConnected()) {
				try {
					URL url = new URL("http://localhost:8080");
					HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
					urlc.setConnectTimeout(2000);
					urlc.connect();
					if (urlc.getResponseCode() == 300) {
						return true;
					}
				} 
				catch (MalformedURLException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			return false;
		}

		login.setOnClickListener (new View.OnClickListener() {

			public void onClick(View view){
				if ((!username.getText().toString().equals("")) && 
						( !password.getText().toString().equals("")) ){
					Toast.makeText(getApplicationContext(),
							"Please Enter Password", Toast.LENGTH_SHORT).show();
				}
				else if ((!password.getText().toString().equals(""))){
					Toast.makeText(getApplicationContext(),
							"Please Enter Username", Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(getApplicationContext(),
							"Please Enter Username and Password", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}



	    
		
	

	
	
