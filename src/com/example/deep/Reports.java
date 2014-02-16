package com.example.deep;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class Reports extends Activity {
	
	private ListView FileListView;
	private TextView textv;
	
	Button home;
    private CustomListAdapter arrayAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports);
	    textv=(TextView)findViewById(R.id.text1);
	    home=(Button)findViewById(R.id.button1);
	    
	      home.setOnClickListener(new OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		//String packageName = context.getPackageName();
	    		//Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
	    		//String className = launchIntent.getComponent().getClassName();
	    		Intent intent = new Intent(Reports.this, MainActivity.class);
	    	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
	    	    startActivity(intent);
	    		
	    	}
	    	
	    	
	    });
	//	Log.d("here","here-1");
		File f =getFilesDir();
	//	Log.d("here","here-2");
		File[] flist=f.listFiles();
	//	Log.d("here","here-3");
		if(flist.length>0)
		{
		String[] theNamesOfFiles = new String[flist.length];
		
		for (int i = 0; i < theNamesOfFiles.length; i++) {
		   theNamesOfFiles[i] = flist[i].getName();
		   theNamesOfFiles[i]=theNamesOfFiles[i].replace(".txt","");
		   theNamesOfFiles[i]=theNamesOfFiles[i].replace("_"," ");
		   
		}
		Log.d("Files", "Size: "+ flist.length);
		for (int i=0; i < flist.length; i++)
		{
		    Log.d("Files", "FileName:" + theNamesOfFiles[i]);
		   
			
           
		}
	
	  
	    FileListView = (ListView) findViewById(R.id.files);
	    List<String>file_list=new ArrayList<String>(Arrays.asList(theNamesOfFiles));
		try{
		//arrayAdapter = new ArrayAdapter(this, R.layout.custom_row, theNamesOfFiles);
		arrayAdapter = new CustomListAdapter(this, R.layout.custom_list_item, file_list);
		}
		catch(Exception e)
		{
			Log.d("ex","ex");
		}
      try{
		FileListView.setAdapter(arrayAdapter);
		}
		catch(Exception e){
			Log.d("ex1","ex1");
		}
	 }
		else {
           textv.setVisibility(View.VISIBLE);
		}
	   
	   
	    
        // listening to single list item on click
   /*     FileListView.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
               
              // selected item
              String file_name = ((TextView) view).getText().toString();
               
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(),Report_detail.class);
              // sending data to new activity
              i.putExtra("file", file_name);
              startActivity(i);
             
          }
        });*/
	
	}
    public void viewFileHandler(View v){
    	String file_name = ((TextView) v).getText().toString();
        
        // Launching new Activity on selecting single List Item
        Intent i = new Intent(getApplicationContext(),Report_detail.class);
        // sending data to new activity
        Log.d("filename",file_name);
        i.putExtra("file", file_name);
        startActivity(i);
    	
    }
	
	public void removeClickHandler(final View v) {
	//	showDialog(DIALOG_REMOVE);
		// boolean flg=false;
	/*	LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(R.layout.custom_row, null);
			new AlertDialog.Builder(this,R.style.FullHeightDialog)
		    .setTitle("Delete entry")
		    
		    .setMessage("Are you sure you want to delete this entry?")
		    .setView(textEntryView)
		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        	 
		        	
		        	
		        }
		     })
		    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            
		        }
		     })
		  //  .setIcon(R.drawable.index)
		     .show();*/
		
		CustomDialogClass cdd=new CustomDialogClass(Reports.this,v,arrayAdapter);
		cdd.show();
		arrayAdapter.notifyDataSetChanged();
		
			
			
		
		
	}
	
	
}
