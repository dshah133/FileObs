package com.example.deep;



import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class CustomDialogClass1 extends Dialog implements
android.view.View.OnClickListener {
// public boolean flag=false;
public Activity c;
public Dialog d;
public Button ok;


public CustomDialogClass1(Activity a){
super(a);
// TODO Auto-generated constructor stub
this.c = a;
}



@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.custom_row_1);
ok = (Button) findViewById(R.id.btn_yes);

ok.setOnClickListener(this);


}

@Override
public void onClick(View v) {


  

dismiss();
}
}

