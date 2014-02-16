package com.example.deep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import android.os.Bundle;
import android.os.FileObserver;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//Changes are notified via Toast.


public class Monitor extends Activity {
	
	
    private OutputStreamWriter osw;
    private static int idx=0;
	private Button stop;
    private static String path;
    private static String ev;
    private Button rep_gen;
    private Button home_but;
    
    private static Context context;
 
    ArrayList<RecursiveFileObserver> obv = new ArrayList<RecursiveFileObserver>();
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		idx=0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitor);

		
		String filename;
		Calendar c3 = Calendar.getInstance();
		SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MMM-yy_H:m:s");
		String strdate3 = sdf3.format(c3.getTime());
		filename=strdate3+".txt";
		try{
			Log.d("Monitor file",filename);
			@SuppressWarnings("deprecation")
			FileOutputStream fOut = openFileOutput(filename,MODE_WORLD_READABLE);
			
		    osw = new OutputStreamWriter(fOut); 
		    
			}
			catch(FileNotFoundException ex)
			{
			 Log.e("Filestream error","Can not open the file");
			}
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		     path = extras.getString("path_to_monitor");
		}
		else
		{
			Log.d("ERROR","path is null");
		}
		 stop= (Button) findViewById(R.id.button1);
		 rep_gen=(Button) findViewById(R.id.button2);
		 rep_gen.setVisibility(View.INVISIBLE);
		 home_but=(Button)findViewById(R.id.button3);
		 home_but.setVisibility(View.INVISIBLE);
		stop.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v)
			{
			 stop_observer();
			 obv.clear();
			 try {
				osw.close();
			} catch (IOException e) {
				Log.e("Exception","Exception Occured !");
				e.printStackTrace();
			}
			 rep_gen.setVisibility(View.VISIBLE);
			 rep_gen.setOnClickListener(new OnClickListener(){
				 @Override
				 public void onClick(View v)
				 {
					 try{
					 Intent i = new Intent("com.example.deep.REPORTS");
                     startActivity(i);
					 }
					 catch(Exception e){
						 
					 } 
					 
					 
				 }
				 
				 
			 });
			 
			 home_but.setVisibility(View.VISIBLE);
			 home_but.setOnClickListener(new OnClickListener(){
				   @Override
				   public void onClick(View v)
				   {
					 try{
					finish();
					 }
					 catch(Exception e){
						 Log.e("ERROR","Exception Occured");
					 }
					   
				   }
                 });
			 
			}
			
			
		});
	 
		
		try{
		Log.d("index",""+idx);
		obv.add(new RecursiveFileObserver(path));
		
		obv.get(idx).startWatching();
		
		idx++;
		}
		catch(Exception e){
			Log.d("exception","exception occured");
		}
	}
    private void stop_observer(){
    	for(int i=0;i<obv.size();i++)
    	{
         obv.get(i).stopWatching();
    		
    	}
    	
    	View v=findViewById(R.id.button1);
    	v.setVisibility(View.GONE);
    	
    	
    	
    }
	/*class MyDirObserver extends FileObserver {
	    String superPath;
	    public MyDirObserver(String path) {
	        super(path, ALL_EVENTS);
	        this.superPath = path;
	    }


	    public void onEvent(int event, String path) {
	        Log.e("onEvent of Directory", "=== onEvent ===");
	        try {
	            Dump("dir", event, path, superPath);
	        } catch (NullPointerException ex) {
	            Log.e("ERROR", "I am getting error");
	        }
	    }
	}*/


	private void Dump(final String tag, int event, String path, String superPath) {
		
		ev="";
	    Log.d(tag, "=== dump begin ===");
	    Log.d(tag, "path=" + path);
	    Log.d(tag, "super path=" + superPath);
	    Log.d(tag, "event list:");
	    Log.d(tag, "Event id :"+event);
	   context = getApplicationContext();
	    
	   //here i have added only some of the events ,we can easily extend it to each event documented.
	    if(event==32768 || event==4)
	    {
	    	return;
	    	
	    }
	    if(superPath.indexOf("/null")!=-1)
	    {
	    	return;
	    	
	    }
	    	
	    
	    if ((event & FileObserver.OPEN) != 0) {
	    	ev=superPath+" is Opened."; 
	    	
	        Log.d(tag, "  OPEN");
	    }
	    if ((event & FileObserver.CLOSE_NOWRITE) != 0) {
	    	ev=superPath+" is Closed without any write";
	        Log.d(tag, "  CLOSE_NOWRITE");
	    }
	    if ((event & FileObserver.CLOSE_WRITE) != 0) {

	    	ev=superPath+" is Modified.";
	        Log.d(tag, "  CLOSE_WRITE");
	        Log.i("NEWFILEOBSERVER", "File is Modified");
	        if (path != null) {
	            Log.d("---------FilePath", superPath + path);
	        }


	    }
	    if ((event & FileObserver.CREATE) != 0) {
	    //    isCreate = true;
	    	ev=superPath+" is Created.";
	        Log.i("NEWFILEOBSERVER", "File is Created ");
	        if (path != null) {
	            Log.d("---------FilePath", superPath + path);
	        }
	        Log.d(tag, "  CREATE");
	        
	        //along with new file/directory creation I add another recursiveFileObserver for that file/dir
	        
	        obv.add(new RecursiveFileObserver(superPath));
	        obv.get(idx).startWatching();
	        idx++;

	    }
	    if ((event & FileObserver.DELETE) != 0) {
	    	ev=superPath+" is Deleted.";
	        Log.i("NEWFILEOBSERVER", "File is deleted");
	        if (path != null) {
	            Log.d("---------FilePath", superPath + path);
	        }
	        //  startMyActivity("A new file is deleted thats="+superPath); 


	        Log.d(tag, "  DELETE");


	    }

	    if ((event & FileObserver.DELETE_SELF) != 0) {
	    	ev=superPath+" is Deleted.";
	        Log.d(tag, "  DELETE_SELF");
	    }

	    if ((event & FileObserver.ACCESS) != 0) {
	    	ev=superPath+" is Accessed.";
	        Log.d(tag, "  ACCESS");
	    }
       
	      Log.d(tag, "=== dump end ===");
	//    Toast.makeText(context, ev, duration).show();
	      
	      if(ev!="")
	      {  ev=ev.replace("/null", "");
	    	  try {
				osw.write(ev+"\n");
				Log.d("FileStream","Written");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      runOnUiThread(new Runnable() {
	            public void run()
	            {
	            	
	                Toast.makeText(context, ev, Toast.LENGTH_SHORT).show();
	               
	            }
	        });
	    
	}
	
	
	
	
	
	public class RecursiveFileObserver extends FileObserver {

		public static final int CHANGES_ONLY = CLOSE_WRITE | MOVE_SELF | MOVED_FROM;

		List<SingleFileObserver> mObservers;
		String mPath;
		int mMask;

		public RecursiveFileObserver(String path) {
		    this(path, ALL_EVENTS);
		}

		public RecursiveFileObserver(String path, int mask) {
		    super(path, mask);
		    mPath = path;
		    mMask = mask;
		}

		@Override
		public void startWatching() {
		    if (mObservers != null) return;
		    mObservers = new ArrayList<SingleFileObserver>();
		    Stack<String> stack = new Stack<String>();
		    stack.push(mPath);

		    while (!stack.empty()) {
		        String parent = stack.pop();
		        mObservers.add(new SingleFileObserver(parent, mMask));
		        File path = new File(parent);
		        File[] files = path.listFiles();
		        if (files == null) continue;
		        for (int i = 0; i < files.length; ++i) {
		            if (files[i].isDirectory() && !files[i].getName().equals(".")
		                && !files[i].getName().equals("..")) {
		                stack.push(files[i].getPath());
		            }
		        }
		    }
		    for (int i = 0; i < mObservers.size(); i++)
		        mObservers.get(i).startWatching();
		}

		@Override
		public void stopWatching() {
		    if (mObservers == null) return;

		    for (int i = 0; i < mObservers.size(); ++i)
		        mObservers.get(i).stopWatching();

		    mObservers.clear();
		    mObservers = null;
		}

		@Override
		public void onEvent(int event, String path) {
			Log.e("onEvent of Directory", "=== onEvent ===");
			Log.d("dir",path);
	      try {
	           Dump("dir", event,null,path);
	       } catch (NullPointerException ex) {
	            Log.e("ERROR", "I am getting error");
	        }
			
		}

		private class SingleFileObserver extends FileObserver {
		    private String mPath;

		    public SingleFileObserver(String path, int mask) {
		        super(path, mask);
		        mPath = path;
		    }

		    @Override
		    public void onEvent(int event, String path) {
		        String newPath = mPath + "/" + path;
		        RecursiveFileObserver.this.onEvent(event, newPath);
		    } 

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}

	
	


