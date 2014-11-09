package net.sourceforge.zbar.android.CameraTest;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





import util.HTTPPostRequest;
import util.Utility;
import net.sourceforge.zbar.android.CameraTest.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
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
	String  mEmail, mPassword;
	private UserLoginTask mAuthTask = null;
	Activity activity;
	

	@Override
	protected void onPause() {
		finish();
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		activity=this;
		
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
				attemptLogin();
				
			}
		});
	}

	private void setup() {
		usernameTV=(TextView)findViewById(R.id.email_login);
		passwordTV=(TextView)findViewById(R.id.password_login);
		submit=(Button)findViewById(R.id.login_login);
		back=(Button)findViewById(R.id.back_button_login);
		
		
		
	}

	public void attemptLogin() {
		
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		usernameTV.setError(null);
		passwordTV.setError(null);

		// Store values at the time of the login attempt.
		
		
		mEmail = usernameTV.getText().toString();
		mPassword = passwordTV.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			//mPasswordView.setError(getString(R.string.error_field_must));
			passwordTV.setError("This field is required ");
			focusView = passwordTV;
			cancel = true;
		} else if (mPassword.length() < Utility.PASSWORD_MIN_LEN) {
			passwordTV.setError("Password is too short");
			focusView = passwordTV;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			//mEmailView.setError(getString(R.string.error_field_must));
			usernameTV.setError("This field is required ");
			focusView = usernameTV;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			usernameTV.setError("email is incorrect");
			focusView = usernameTV;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
		
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
		
	}
	
	public class UserLoginTask extends AsyncTask<Void, Void, String> {
		public static final String TAG = ": LoginActivity: UserLoginTask";
		@Override
		protected String doInBackground(Void... params) {
			String url="http://qred.cloudapp.net/api/v1/token";
			String serverURL=url;

			HTTPPostRequest loginRequest = new HTTPPostRequest();
			
		
			
			
			//forming the parameters list for the httppost request
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);


			nameValuePairs.add(new BasicNameValuePair(Utility.USERNAME, mEmail));
			nameValuePairs.add(new BasicNameValuePair(Utility.PASSWORD, mPassword));
			nameValuePairs.add(new BasicNameValuePair(Utility.GRANTTYPE, Utility.PASSWORD));
			


			String registerResult = loginRequest.executeHTTPPost(nameValuePairs,serverURL,activity);

			return registerResult;
		}
		
		

	    

		@Override
		protected void onPostExecute(final String success) {
			
			

			JSONObject response=null;
			try {
				 response=new JSONObject(success);
			} catch (JSONException e) {
				
				e.printStackTrace();
				Log.e(Utility.TAG+UserLoginTask.TAG, "Failed to convert the strign into the JSON");
				return;
			}
			/**
			 * { "access_token": "TOKEN", "expires_in": 604800, "user": { "uuid": "USER_UUID", 
			 * "type": "user", "created": TIMESTAMP,
			 *  "modified": TIMESTAMP, "email": "EMAIL",
			 *   "activated": true }
			 *   {"access_token":"YWMtQE-4OmgMEeS1EGEJkVcECwAAAUm4laZeMOWqXytM17kAQR71mG1gC-yM-Lc",
			 *   "expires_in":604800,"user":{"uuid":"96345f10-6804-11e4-9ce9-c7f448b015ce","type":"user",
			 *   "name":"Mountain Dew","created":1415532963712,"modified":1415532963712,"username":"a@a.com","activated":true}}
			 */
			
			String accessToken,uuid,email,name;
			boolean activated;
			int modified,created;
			
			try {
				JSONObject userObject=response.getJSONObject("user");
				email=userObject.getString(Utility.USERNAME);
				accessToken=response.getString(Utility.TOKEN);
				name=userObject.getString(Utility.NAME);
				activated=userObject.getBoolean("activated");
				
				uuid=userObject.getString(Utility.UUID);
			} catch (JSONException e) {
				Log.e("Error", "error");
				e.printStackTrace();
				return;
			}
			
			if (activated){
				
				Utility.setUsername(email,getApplicationContext());
				Utility.setUuid(uuid,getApplicationContext());
				Utility.setToken(accessToken, getApplicationContext());
				
				Intent myIntent = new Intent(LoginActivity.this, ProfileActivity.class);
				startActivity(myIntent);
				Utility.setIsLoggedIn(activated, getApplicationContext());
				finish();
				
			}
		}
		
		
		
		

		
	}

}
