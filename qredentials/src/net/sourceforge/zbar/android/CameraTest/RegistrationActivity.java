package net.sourceforge.zbar.android.CameraTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import util.HTTPPostRequest;
import util.Utility;
import net.sourceforge.zbar.android.CameraTest.R;
import net.sourceforge.zbar.android.CameraTest.LoginActivity.UserLoginTask;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationActivity extends Activity {
	TextView emailTV,passwordTV,confirmPasswordTV,nameTV;
	String mEmail,mPassword,mConfPassword,mName;
	Button registerBTN,backBTN;
	private UserLoginTask mAuthTask = null;
	Activity activity;

	public RegistrationActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		activity=this;
		setup();
		backBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(RegistrationActivity.this, WelcomeActivity.class);
				startActivity(myIntent);
				
			}
		});
		
		registerBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				attemptLogin();
				
			}

			
		});
	}
	
	private void attemptLogin() {
		
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		
		emailTV.setError(null);
		passwordTV.setError(null);
		confirmPasswordTV.setError(null);
		nameTV.setError(null);

		// Store values at the time of the login attempt.
		
		
		mEmail = emailTV.getText().toString();
		mPassword = passwordTV.getText().toString();
		mConfPassword = confirmPasswordTV.getText().toString();
		mName = nameTV.getText().toString();

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
			nameTV.setError("This field is required ");
			focusView = nameTV;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			emailTV.setError("email is incorrect");
			focusView = emailTV;
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
	
	private void setup() {
		nameTV=(TextView)findViewById(R.id.username_register);
		emailTV=(TextView)findViewById(R.id.email_register);
		confirmPasswordTV=(TextView)findViewById(R.id.confirm_password_register);
		passwordTV=(TextView)findViewById(R.id.password_register);
		registerBTN=(Button)findViewById(R.id.sign_up_register);
		backBTN=(Button)findViewById(R.id.back_register);
		
	}
	
	public class UserLoginTask extends AsyncTask<Void, Void, String> {
		public static final String TAG = ": LoginActivity: UserLoginTask";
		@Override
		protected String doInBackground(Void... params) {
			String url="http://qred.cloudapp.net/api/v1/users";
			String serverURL=url;

			HTTPPostRequest loginRequest = new HTTPPostRequest();
			
		
			
			
			//forming the parameters list for the httppost request
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);


			nameValuePairs.add(new BasicNameValuePair(Utility.USERNAME, mEmail));
			nameValuePairs.add(new BasicNameValuePair(Utility.PASSWORD, mPassword));
			nameValuePairs.add(new BasicNameValuePair(Utility.NAME, mName));
			
			JSONObject data =new JSONObject();
			
			try {
				data.put(Utility.USERNAME, mEmail);
				data.put(Utility.PASSWORD, mPassword);
				data.put(Utility.NAME, mName);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			String registerResult = loginRequest.executeHTTPPost(data,serverURL,activity);

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
			 */
			
			String action,application,params;
			boolean activated;
			int modified,created;
			
			try {
				
				action=response.getString("action");
				application=response.getString("application");
				
				
			} catch (JSONException e) {
				Log.e("Error", "error");
				e.printStackTrace();
				return;
			}
			
			
				Intent myIntent = new Intent(RegistrationActivity.this, ProfileActivity.class);
				startActivity(myIntent);
				Utility.setIsLoggedIn(true, getApplicationContext());
				Utility.setUsername(mEmail);
				finish();
			
		}
		
		
		
		

		
	}

}
