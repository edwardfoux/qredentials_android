package net.sourceforge.zbar.android.CameraTest;


import net.sourceforge.zbar.android.CameraTest.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AllCardActivity extends Activity{
	LinearLayout holder;

	public AllCardActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_all_cards);
		
		setup();
		
		for (int i=0;i<10;i++){
		 View child = getLayoutInflater().inflate(R.layout.card_element, null);
			TextView addressTV = (TextView) child.findViewById(R.id.card_address);
			TextView emailTV = (TextView) child.findViewById(R.id.card_email);
			TextView nameTV = (TextView) child.findViewById(R.id.card_name);
			TextView phoneTV = (TextView) child.findViewById(R.id.card_phone);
			TextView phone2TV = (TextView) child.findViewById(R.id.card_phone2);
			TextView websiteTV = (TextView) child.findViewById(R.id.card_website);
			holder.addView(child);
		}
	}

	private void setup() {
		holder=(LinearLayout)findViewById(R.id.linear_holder);
		
	}
	
	

}
