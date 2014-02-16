package com.example.deep;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;



public class CustomDialogClass extends Dialog implements
android.view.View.OnClickListener {
// public boolean flag=false;
public Activity c;
public Dialog d;
public Button yes, no;
public View vv;
public CustomListAdapter adp;
public CustomDialogClass(Activity a,View vv,CustomListAdapter adp) {
super(a);
this.vv=vv;
this.adp=adp;
// TODO Auto-generated constructor stub
this.c = a;
}
public CustomDialogClass(Activity a) {
super(a);

// TODO Auto-generated constructor stub
this.c = a;
}
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.custom_row);
yes = (Button) findViewById(R.id.btn_yes);
no = (Button) findViewById(R.id.btn_no);
yes.setOnClickListener(this);
no.setOnClickListener(this);

}

@Override
public void onClick(View v) {
switch (v.getId()) {
case R.id.btn_yes:
  String itemToRemove = (String)vv.getTag();
  String itemToremove1=itemToRemove=itemToRemove.replace(" ", "_");
  itemToremove1=itemToremove1+".txt";
  Log.d("File",itemToremove1);
  String selectedFilePath="/data/data/com.example.deep/files/"+itemToremove1;
  File file = new File(selectedFilePath);
  boolean deleted = file.delete();
  Log.d("value","value :"+deleted);
  adp.remove(itemToRemove);
  adp.notifyDataSetChanged();
  break;
case R.id.btn_no:
  dismiss();
  break;
default:
  break;
}
dismiss();
}
}

