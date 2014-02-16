package com.example.deep;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


public class CustomListAdapter extends ArrayAdapter<String> {

	protected static final String LOG_TAG = CustomListAdapter.class.getSimpleName();
	
	private List<String> items;
	private int layoutResourceId;
	private Context context;

	public CustomListAdapter(Context context, int layoutResourceId, List<String> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
	    CustomHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new CustomHolder();
		holder.str = items.get(position);
		holder.removeButton = (ImageButton)row.findViewById(R.id.atomPay_removePay);
		holder.removeButton.setTag(holder.str);

		holder.name = (TextView)row.findViewById(R.id.atomPay_name);
		
		//holder.value = (TextView)row.findViewById(R.id.atomPay_value);
		

		row.setTag(holder);

		setupItem(holder);
		return row;
	}

	private void setupItem(CustomHolder holder) {
		holder.name.setText(holder.str);
	
	}

	public static class CustomHolder {
		String str;
		TextView name;
		
		ImageButton removeButton;
	}
	
	
	}

	
	
