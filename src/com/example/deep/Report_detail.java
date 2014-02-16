package com.example.deep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.deep.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Report_detail extends Activity
{
  String a;
  Button back;
  Button home;

  protected void onCreate(Bundle savedInstanceState) {
	  
	  
			super.onCreate(savedInstanceState);
			setContentView(R.layout.report_detail);
    home=(Button)findViewById(R.id.button3);
    back= (Button)findViewById(R.id.button2);
    back.setOnClickListener(new OnClickListener(){
    	@Override
    	public void onClick(View v){
    		Intent i= new Intent("com.example.deep.REPORTS");
    		startActivity(i);
    		
    	}
    	
    	
    	
    });
    home.setOnClickListener(new OnClickListener(){
    	@Override
    	public void onClick(View v){
    		Intent intent = new Intent(Report_detail.this, MainActivity.class);
    	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
    	    startActivity(intent);
    		
    	}
    	
    	
    });
   
    TextView localTextView = (TextView)findViewById(R.id.Text1);
   
    this.a = getIntent().getExtras().getString("file");
    this.a = this.a.replace(" ", "_");
    this.a += ".txt";
    try
    {
      FileInputStream localFileInputStream = openFileInput(this.a);
      String str1 = "";
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localFileInputStream));
      if (localFileInputStream != null);
      while (true)
      {
        String str2 = localBufferedReader.readLine();
        if (str2 == null)
        {
          localFileInputStream.close();
          localTextView.setMovementMethod(new ScrollingMovementMethod());
          localTextView.setText(str1);
          
          return;
        }
        str1 = str1 + str2 + "\n";
        System.out.println(str2);
      }
    }
    catch (Exception e)
    {
      
    }
  }
}