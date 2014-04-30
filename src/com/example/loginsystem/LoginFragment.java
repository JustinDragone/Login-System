package com.example.loginsystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment{
	
	View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	view = inflater.inflate(R.layout.fragment_main, null);
        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				String username, password;
				
				 EditText editText1 = (EditText) view.findViewById (R.id.editText1);
				 EditText editText2 = (EditText) view.findViewById(R.id.editText2);
				 
				 username = editText1.getText().toString();
				 password = editText2.getText().toString();
				 
				 TextView newuser = (TextView) view.findViewById (R.id.editText1);
				 TextView newpass = (TextView) view.findViewById (R.id.editText2);
				 
				 newuser.setText(username);
				 newpass.setText(password);					
			}
        });
        return view;
        
    }
}