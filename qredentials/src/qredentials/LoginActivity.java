package qredentials;

import net.sourceforge.zbar.android.CameraTest.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {
	TextView usernameTV;
	TextView passwordTV;
	Button submit,back;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		setup();
		
		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(LoginActivity.this, WelcomeActivity.class);
				startActivity(myIntent);
				
			}
		});
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(LoginActivity.this, ProfileActivity.class);
				startActivity(myIntent);
				
			}
		});
	}

	private void setup() {
		usernameTV=(TextView)findViewById(R.id.email_login);
		passwordTV=(TextView)findViewById(R.id.password_login);
		submit=(Button)findViewById(R.id.login_login);
		back=(Button)findViewById(R.id.back_button_login);
		
		
		
	}

	public LoginActivity() {
		// TODO Auto-generated constructor stub
	}

}
