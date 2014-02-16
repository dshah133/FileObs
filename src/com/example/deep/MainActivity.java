package com.example.deep;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends Activity {

	 private Button start;
	 private Button reports;
	 private EditText getpath; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getpath = (EditText) findViewById(R.id.editText1);
        
        start= (Button) findViewById(R.id.button1);
        reports=(Button) findViewById(R.id.button2);
        reports.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent i=new Intent("com.example.deep.REPORTS");
        		startActivity(i);
        		
        	}
        	
        	
        	
        	
        });
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
                handlePath();
            }
        });
		
		
	}
	private void handlePath(){
		if ((getpath.getText()==null )|| getpath.getText().toString().equals("")){
			CustomDialogClass1 cdd=new CustomDialogClass1(MainActivity.this);
			cdd.show();
			
			
        return;
			
		}
		try{
		Intent i = new Intent("com.example.deep.MONITOR");
		i.putExtra("path_to_monitor",getpath.getText().toString());
		startActivity(i);
		}
		catch(Exception e){
			
			
		}
      
}
     
	protected void onStart(Bundle savedInstanceState){
		
		
    }
	
}