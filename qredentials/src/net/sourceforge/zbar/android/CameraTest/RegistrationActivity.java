package net.sourceforge.zbar.android.CameraTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationActivity extends Activity {
	TextView emailTV,passwordTV,confirmPasswordTV,userNameTV;
	Button registerBTN,backBTN;

	public RegistrationActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		setup();
		backBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(RegistrationActivity.this, WelcomeActivity.class);
				startActivity(myIntent);
				
			}
		});
	}
	private void setup() {
		userNameTV=(TextView)findViewById(R.id.username);
		emailTV=(TextView)findViewById(R.id.email_register);
		confirmPasswordTV=(TextView)findViewById(R.id.confirm_password_register);
		passwordTV=(TextView)findViewById(R.id.password_register);
		registerBTN=(Button)findViewById(R.id.sign_up_register);
		backBTN=(Button)findViewById(R.id.back_register);
		
	}

}
