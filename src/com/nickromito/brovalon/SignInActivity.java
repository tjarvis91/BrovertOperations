package com.nickromito.brovalon;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class SignInActivity extends Activity {

	public static final String EXTRA_PLAYER_NAME = "com.nickromito.brovalon.PLAYER_NAME";
	private static final String url_sign_in = "http://localhost:3000/sessions";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
	}
	
	public void signIn(View view) {
        EditText editText = (EditText) findViewById(R.id.text_edit_email);
        String email = editText.getText().toString();
        editText = (EditText) findViewById(R.id.text_edit_password);
        String password = editText.getText().toString();
        String playerName = "User";
        //HttpResponse response = signInRequest(email, password);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_PLAYER_NAME, playerName);
        startActivity(intent);
	}
	
	private HttpResponse signInRequest(String email, String password) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url_sign_in);
		HttpResponse response = null;
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		List<NameValuePair> session = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("email", email));
		pairs.add(new BasicNameValuePair("password", password));
		
		session.add(new BasicNameValuePair("session", pairs.toString()));
		try {
			post.setEntity(new UrlEncodedFormEntity(session));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		try {
			response = client.execute(post);
			Log.d("Http Response: ", response.toString());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_sign_in,
					container, false);
			return rootView;
		}
	}

}
