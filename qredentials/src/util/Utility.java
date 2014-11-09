package util;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Utility {
	public static SharedPreferences sp = null;
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String USERNAME = "usernmae";
	public static final String NAME = "name";
	public static final String ISLOGGEDIN = "isloggedin";
	public static final String TOKEN = "token";
	public static final String TIMESTAMP = "timestamp";
	public static final String TAG = "qredential: ";
	private static Context applicationContext;

	public static void setUsername(String username){
		
	}
	public static void setIsLoggedIn(boolean bool,Context c){
		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean(ISLOGGEDIN, bool);

		// Commit the edits!
		editor.commit();
	}
	public static void setName(String name, Context c){
		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(NAME, name);

		// Commit the edits!
		editor.commit();
	}
	public static void setUsername(String username,Context c){

		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(USERNAME, username);

		// Commit the edits!
		editor.commit();
	
	}
	public static void setToken(String token,Context c){

		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(TOKEN, token);

		// Commit the edits!
		editor.commit();
	
	}
	public static void setTimestamp(int time,Context c){

		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt(TIMESTAMP, time);		// Commit the edits!
		editor.commit();
	
	}
	
	public static final String getToken(Context c){
		
		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		return sp.getString(TOKEN,"nothng");
	}
	public static final int getTimeStamp(Context c){
		
		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		return sp.getInt(TIMESTAMP,0);
	}
	
	public static final boolean getIsloggedIn(Context c){
		
		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		return sp.getBoolean(ISLOGGEDIN, false);
	}
	
	public static String getUsername(Context c){
		sp = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		return sp.getString(USERNAME, null);
	}
	public static Context  getApplicationContext(){
		return applicationContext;
	}
	
	public static void setApplicationContext(Context c){
		applicationContext=c;
	}
	 public static boolean haveNetworkConnection(Context c) {
	        boolean haveConnectedWifi = false;
	        boolean haveConnectedMobile = false;
	        

	        try {
				ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo[] netInfo = cm.getAllNetworkInfo();
				for (NetworkInfo ni : netInfo) {
				    if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				        if (ni.isConnected())
				            haveConnectedWifi = true;
				    if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				        if (ni.isConnected())
				            haveConnectedMobile = true;
				}
			} catch(NullPointerException e){
				Log.e(Utility.TAG, "null pointer exception in the internet connection check. The context is "+c);
				return true;
			}
	        return haveConnectedWifi || haveConnectedMobile;
	        
	    }
	


}
