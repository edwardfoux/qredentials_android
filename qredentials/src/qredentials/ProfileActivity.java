package qredentials;

import net.sourceforge.zbar.android.CameraTest.R;
import util.DownloadImageTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ProfileActivity  extends Activity{
	 Button allCardsBTN,editProfileBTN,scanQRBTN;
	 ImageView qrCode;
	 
	 ProgressBar spinner;
	 RelativeLayout layout;
	 
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_profile);
		setup();
		scanQRBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ProfileActivity.this, CameraTestActivity.class);
				startActivityForResult(myIntent,1);
				
			}
		});
		allCardsBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ProfileActivity.this, AllCardActivity.class);
				startActivityForResult(myIntent,1);
				
			}
		});
		
		new DownloadImageTask((ImageView) findViewById(R.id.qrcode_image_profile))
        .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

	}
	private void setup() {
		editProfileBTN=(Button)findViewById(R.id.edit_profile);
		qrCode=(ImageView)findViewById(R.id.qrcode_image_profile);
		scanQRBTN=(Button)findViewById(R.id.scan_qr_card);
		allCardsBTN=(Button)findViewById(R.id.all_cards);
		spinner=(ProgressBar)findViewById(R.id.profile_progress);
		layout=(RelativeLayout)findViewById(R.id.layout_profile);
		
		
	} 
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            String result=data.getStringExtra("result");
	            spinner.setVisibility(View.VISIBLE);
	            qrCode.setVisibility(View.GONE);
	            
	            View child = getLayoutInflater().inflate(R.layout.card_element, null);
	            ((View)child.findViewById(R.id.card_divider)).setVisibility(View.GONE);
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
