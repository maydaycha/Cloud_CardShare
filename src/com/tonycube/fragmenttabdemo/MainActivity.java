package com.tonycube.fragmenttabdemo;

import com.tonycube.demo.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	
	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_Quit = Menu.FIRST+1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
	
		FragmentTabHost tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		//1
		tabHost.addTab(tabHost.newTabSpec("Apple")
			   				  .setIndicator("Search Card"), 
   					  AppleFragment.class, 
   					  null);
	    //2
		tabHost.addTab(tabHost.newTabSpec("Google")
				   			  .setIndicator("Card Share"), 
					  GoogleFragment.class, 
					  null);
	    //3
		tabHost.addTab(tabHost.newTabSpec("Facebook")
				   			  .setIndicator("Profiles"), 
					  FacebookFragment.class, 
				      null);
	}

	/**************************
	 * 
	 * 		µ¹¤l­¶ÅÒ©I¥s¥Î
	 * 
	 **************************/
	
	public String getAppleData(){
		return "Apple 123";
	}

	public String getGoogleData(){
		return "Google 456";
	}
	
	public String getFacebookData(){
		return "Facebook 789";
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		  menu.add(0, MENU_ABOUT, 0, "About");
		  menu.add(0, MENU_Quit, 0, "Quit");
		  return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
	     super.onOptionsItemSelected(item);
	     switch(item.getItemId()){
	          case MENU_ABOUT:
	              openOptionsDialog();
	              break;
	         case MENU_Quit:
	              finish();
	              break;
	       }
	       return true;
	}
	
	public void openOptionsDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("About Card Sharing Platform");
        dialog.setMessage("Author: 0253401 ±i­»³Ç¡B0253433 ªôÄ£¼y¡B0153406 ¾¤¦±®p");
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener(){
                    public void onClick(
                            DialogInterface dialoginterface, int i){
                            }
                    });
        dialog.show();
    }
}
