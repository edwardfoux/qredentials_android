package net.sourceforge.zbar.android.CameraTest;







import util.Utility;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	
	Button signUpBTN,loginBTN,qrScanBTN;
	
	ProgressBar spinner;
	RelativeLayout layout;

	public WelcomeActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		setup();
		
		if (Utility.getIsloggedIn(getApplicationContext())){
			Intent myIntent = new Intent(WelcomeActivity.this, ProfileActivity.class);
		startActivity(myIntent);
			finish();
			return;
		}
			
		
		signUpBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
				startActivity(myIntent);
				
			}
		});
		loginBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
				startActivity(myIntent);
				
			}
		});
		qrScanBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(WelcomeActivity.this, CameraTestActivity.class);
				startActivityForResult(myIntent,1);
				
			}
		});
	}

	private void setup() {
		signUpBTN=(Button)findViewById(R.id.sign_up_button_welcome);
		loginBTN=(Button)findViewById(R.id.login_welcome);
		qrScanBTN=(Button)findViewById(R.id.qr_scan_button_welcome);
		//cardInforTV;
		spinner=(ProgressBar)findViewById(R.id.card_progress);
		layout=(RelativeLayout)findViewById(R.id.layout_card);
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            String result=data.getStringExtra("result");
	            
	            
	            spinner.setVisibility(View.VISIBLE);
	            
	            View child = getLayoutInflater().inflate(R.layout.card_element, null);
	            ((View)child.findViewById(R.id.card_divider)).setVisibility(View.GONE);
	            ((View)findViewById(R.id.intro_welcome)).setVisibility(View.GONE);
				TextView addressTV = (TextView) child.findViewById(R.id.card_address);
				TextView emailTV = (TextView) child.findViewById(R.id.card_email);
				TextView nameTV = (TextView) child.findViewById(R.id.card_name);
				TextView phoneTV = (TextView) child.findViewById(R.id.card_phone);
				TextView phone2TV = (TextView) child.findViewById(R.id.card_phone2);
				TextView websiteTV = (TextView) child.findViewById(R.id.card_website);
				layout.addView(child);
				
				addressTV.setText(result);
	            
	            
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}

}
