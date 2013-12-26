package com.tonycube.fragmenttabdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tonycube.demo.R;
import com.tonycube.demo.R.id;
import com.tonycube.demo.R.layout;
import com.tonycube.demo.R.menu;

public class LoginActivity extends Activity {
	private Button button_login;
	private EditText account, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		findViews();
		setListener();
		
	}
	
	private void findViews(){
		button_login = (Button)findViewById(R.id.button_login);
		account = (EditText)findViewById(R.id.editText_account);
		password = (EditText)findViewById(R.id.editText_password);
	}
	
	private void setListener(){
		button_login.setOnClickListener(l);
	}
	
	OnClickListener l = new OnClickListener(){
		public void onClick(View v){
			String acc = account.getText().toString();
			String pass = password.getText().toString();
			
			if(acc.equals("may") && pass.equals("123")){
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			}
			else{
				Toast.makeText(LoginActivity.this, "Account or Password error", Toast.LENGTH_SHORT).show();
			}
				
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
