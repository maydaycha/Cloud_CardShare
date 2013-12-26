package com.tonycube.fragmenttabdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FacebookFragment extends ListFragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("=====>", "FacebookFragment onActivityCreated");
		String s1[] = {"Name : 張小傑", "Account: nctuIIM", "Password: nctu123", "sexual: 男", "Birthday: 1991-02-10", "Height: 178", "Weight: 88", "MobileNum: 0988123456", "eMail: NCTUIIM@nctu.edu.tw", "Address: 新竹市學府路"};
	    ArrayList<String> a1 = new ArrayList<String>();
	    
	    for(int i=0; i<s1.length; i++){
	    	a1.add(s1[i]);
	    }
	    
	    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, a1);
	    setListAdapter(adapter1);
	}
	
	 public void onListItemClick(ListView l, View v, int position, long id) {
		    // do something with the data

		  }
	
}
