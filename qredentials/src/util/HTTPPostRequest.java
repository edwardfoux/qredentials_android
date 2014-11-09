package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;



import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * RegistrationServerRequest class makes HTTPOST request to the server (the url and the data parameters are passed to the method registerUser
 * @author Edward Fuks
 *
 */

public class HTTPPostRequest {
	
	 public static final String TAG = ": HTTPOSTrequest";
	 private String errorMessage=null;
	 private String className;


	 /**
	  * executeHTTPPost executes the HTTPOST request to the server
	  * @param nameValuePairs data parameters 
	  * @param serverURL url of the server
	  * @return the response from the server
	  */
	 //List<NameValuePair>
	public String executeHTTPPost(Object nameValuePairs,String serverURL,Activity activity) {
		if (!Utility.haveNetworkConnection(Utility.getApplicationContext())){
			Log.e(Utility.TAG+TAG,"No Internet connection. Cancelled the http request");
			
				
			return "";
		}

		// Add your data

		
		
		HttpPost request = new HttpPost(serverURL);

		if (nameValuePairs instanceof JSONObject){
			JSONObject obkect =(JSONObject)nameValuePairs;
			try {
				request.setEntity(new StringEntity(obkect.toString()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
		try {
			request.setEntity(new UrlEncodedFormEntity((List<NameValuePair>)nameValuePairs));
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
			errorMessage="Failed to set the parameters for the HTTPOST request";
			Log.e(Utility.TAG+HTTPPostRequest.TAG,errorMessage);
			return errorMessage;
		}
		
		}
		// receiving the response

		HttpResponse response = null;

		HttpParams httpParams = new BasicHttpParams();

		HttpClient client = new DefaultHttpClient(httpParams);

		try {
			
			response = client.execute(request);
			

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage="The execution of the POST request failed";
			Log.e(Utility.TAG+HTTPPostRequest.TAG,errorMessage);
			return errorMessage;

		}
		catch (OutOfMemoryError e2) {
		      e2.printStackTrace();
		      return null;
		    }

		String output = null;

		try {

			output = EntityUtils.toString(response.getEntity());

			Log.i(Utility.TAG+this, "server response: "+output);

		} catch (IllegalStateException e) {


			e.printStackTrace();
			errorMessage="Error occured while converting the server output into the String: Illegal statement";
			Log.e(Utility.TAG+HTTPPostRequest.TAG,errorMessage);
			return errorMessage;

		} catch (IOException e) {


			e.printStackTrace();
			errorMessage="Error occured while converting the server output into the String: IOException";
			Log.e(Utility.TAG+HTTPPostRequest.TAG,errorMessage);
			return errorMessage;

		}

		return output;
	}
	

	public String executeHTTPPut(JSONObject nameValuePairs,Activity activity, String serverURL) {
		if (!Utility.haveNetworkConnection(Utility.getApplicationContext())){
			Log.e(Utility.TAG+TAG,"No Internet connection. Cancelled the http request");
			
				
			return "";
		}

		// Add your data

		
		
		HttpPut request = new HttpPut(serverURL);
		
			
			
			try {
				request.setEntity(new StringEntity(nameValuePairs.toString()));
				String a=Utility.getToken(activity.getApplicationContext());
				request.setHeader("Authorization", "Bearer " + a);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
		
		// receiving the response

		HttpResponse response = null;

		HttpParams httpParams = new BasicHttpParams();

		HttpClient client = new DefaultHttpClient(httpParams);
		
		

		try {
			
			response = client.execute(request);
			

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage="The execution of the POST request failed";
			Log.e(Utility.TAG+HTTPPostRequest.TAG,errorMessage);
			return errorMessage;

		}
		catch (OutOfMemoryError e2) {
		      e2.printStackTrace();
		      return null;
		    }

		String output = null;

		try {

			output = EntityUtils.toString(response.getEntity());

			Log.i(Utility.TAG+this, "server response: "+output);

		} catch (IllegalStateException e) {


			e.printStackTrace();
			errorMessage="Error occured while converting the server output into the String: Illegal statement";
			Log.e(Utility.TAG+HTTPPostRequest.TAG,errorMessage);
			return errorMessage;

		} catch (IOException e) {


			e.printStackTrace();
			errorMessage="Error occured while converting the server output into the String: IOException";
			Log.e(Utility.TAG+HTTPPostRequest.TAG,errorMessage);
			return errorMessage;

		}

		return output;
	}



	

}
