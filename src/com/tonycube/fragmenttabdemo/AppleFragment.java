package com.tonycube.fragmenttabdemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tonycube.demo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Spinner;
import android.widget.Toast;


public class AppleFragment extends Fragment {
	
	Bundle data = new Bundle();
	String storeName = "";
	Bundle CardInfo = new Bundle();
	String[] list_storeName, list_cardNo, list_owner, list_cardPhone, list_expireTime, list_ownType, list_showType, list_url;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("=====>", "AppleFragment onAttach");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("=====>", "AppleFragment onCreateView");
		return inflater.inflate(R.layout.frg_apple, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("=====>", "AppleFragment onActivityCreated");
		
		Spinner spinner = (Spinner) this.getView().findViewById(R.id.spinner1);
		ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,new String[]{"��[","EDWIN","�۫~","���۰�","�d�O��","�}�ڤ�","���p"});
		spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(spinAdapter);
		
		spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
            	storeName = adapterView.getSelectedItem().toString();
            	new Thread(SearchStore).start();
            	//Toast.makeText(AppleFragment.this.getActivity(), "�z���"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView arg0) {
            	new Thread(SearchStore).start();
            }
        });
		
		Button btnSearch = (Button) this.getView().findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread(SearchStore).start();
				String storeName = "", cardNo = "", owner = "", cardPhone = "", expireTime = "", ownType = "", showType = "", url = "";;
				
				JSONArray jsonArray;
				try {
					jsonArray = new JSONArray(CardInfo.getString("key"));
					for(int i = 0; i < jsonArray.length(); i++) {
	                    JSONObject jsonData = jsonArray.getJSONObject(i);
	                    storeName += jsonData.getString("storeName").toString() + " ,";
	                    cardNo += jsonData.getString("cardNo").toString() + " ,";
	                    owner += jsonData.getString("owner").toString() + " ,";
	                    cardPhone += jsonData.getString("cardPhone").toString() + " ,";
	                    expireTime += jsonData.getString("expireTime").toString() + " ,";
	                    ownType += jsonData.getString("ownType").toString() + " ,";
	                    showType += jsonData.getString("showType").toString() + " ,";
	                    url += jsonData.getString("url").toString() + " ,";
	                    //Toast.makeText(AppleFragment.this.getActivity(), jsonData.getString("storeName"), Toast.LENGTH_SHORT).show();
	                }
					Log.e("may", "storname: "+storeName);
					Log.e("may", "storname: "+cardNo);
					
					list_storeName = storeName.split(",");
					list_cardNo = cardNo.split(",");
					list_owner = owner.split(",");
					list_cardPhone = cardPhone.split(",");
					list_expireTime = expireTime.split(",");
					list_ownType = ownType.split(",");
					list_showType = showType.split(",");
					list_url = url.split(",");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(AppleFragment.this.getActivity(), "Error", Toast.LENGTH_SHORT).show();
				}
				
				showlistview();
			}
			
		});
		
	}
	
	public void showlistview(){
		final ListView listView;
		ArrayAdapter<String> listAdapter;
		
		listView = (ListView)this.getView().findViewById(R.id.listView);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_single_choice,list_cardNo);
		listView.setAdapter(listAdapter);
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String detailinfo;
				detailinfo = "";
				
				detailinfo = "Store Name: " + list_storeName[arg2] + "\n" + 
							 "Card No: " + list_cardNo[arg2] + "\n" + 
							 "Owner: " + list_owner[arg2] + "\n" + 
							 "Card Phone: " + list_cardPhone[arg2] + "\n" + 
							 "Expire Time: " + list_expireTime[arg2] + "\n" +
							 "URL: " + list_url[arg2];
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(AppleFragment.this.getActivity());
				dialog.setTitle("Card Detail Information");
				dialog.setMessage(detailinfo);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
					
				});
			    dialog.show();
				return false;
			}
			
		});
	}
	
	Runnable SearchStore = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String uriAPI = "http://120.126.18.173/query.php";  
			
		      try
		      { 
		    	HttpClient httpclient = new DefaultHttpClient();
		        HttpResponse response;
		        HttpPost httpost = new HttpPost(uriAPI);
		        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		        nvps.add(new BasicNameValuePair("storeName", storeName));
		        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		        
		        response = httpclient.execute(httpost);
		        HttpEntity entity = response.getEntity();
		        if(entity != null){
		        	CardInfo.clear();
		        	CardInfo.putString("key",EntityUtils.toString(entity, HTTP.UTF_8));
		        }
		      }
		      catch(Exception e)
		      {
		        e.printStackTrace();
		      }
		}
		
	};
	
}
