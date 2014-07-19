package com.nickromito.brovalon;

import android.app.ActionBar.LayoutParams;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

/**
 * @author nromito
 *
 */
public class MainActivity extends Activity {
	
	public final static String EXTRA_PLAYER_NAME = "com.nickromito.brovalon.PLAYER_NAME";
	public static String mPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        mPlayerName = intent.getStringExtra(SignInActivity.EXTRA_PLAYER_NAME);

        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        
        Tab tab = actionBar.newTab()
        				   .setText(R.string.join_game)
        				   .setTabListener(new TabListener<JoinFragment>(this, "join", JoinFragment.class));
        
        actionBar.addTab(tab);
        
        tab = actionBar.newTab()
        		 	   .setText(R.string.create_game)
        		 	   .setTabListener(new TabListener<CreateFragment>(this, "create", CreateFragment.class));
        
        actionBar.addTab(tab);  
        
    }
    
    public void joinGame(View view) {
    	
    }
    
    public void createGame(View view) {
    	/*Intent intent = new Intent(this, CreateGameActivity.class);
        EditText editText = (EditText) findViewById(R.id.text_edit_enter_name);
        String playerName = editText.getText().toString();
        intent.putExtra(EXTRA_PLAYER_NAME, playerName);
        startActivity(intent);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    //FRAGMENT: Join Game Fragment
    public static class JoinFragment extends Fragment {

        public JoinFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_join, container, false);
            TextView welcome = (TextView) rootView.findViewById(R.id.text_view_welcome);
            welcome.setText("Welcome " + mPlayerName);
            return rootView;
        }
    }
    
    //FRAGMENT: Create Game Fragment
    public static class CreateFragment extends Fragment {

        public CreateFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_create, container, false);
            TextView welcome = (TextView) rootView.findViewById(R.id.text_view_welcome);
            welcome.setText("Welcome " + mPlayerName);
            return rootView;
        }
    }
    
    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
    	private Fragment mFragment;
    	private final Activity mActivity;
    	private final String mTag;
    	private final Class<T> mClass;
    	
    	public TabListener(Activity activity, String tag, Class<T> clz) {
    		mActivity = activity;
    		mTag = tag;
    		mClass = clz;
    	}
    	
    	@Override
    	public void onTabSelected(Tab tab, FragmentTransaction ft) {
    		//Check if fragment is already initialized
    		if(mFragment == null) {
    			//If not init, instantiate and add it to activity
    			mFragment = Fragment.instantiate(mActivity, mClass.getName());
    			ft.add(android.R.id.content, mFragment, mTag);
    		} else {
    			//If it exists, simply attach it in order to show it
    			ft.attach(mFragment);
    		}
    	}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {	
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if(mFragment != null) {
				//Detach the fragment because another is being attached
				ft.detach(mFragment);
			}
		}
    }

}
