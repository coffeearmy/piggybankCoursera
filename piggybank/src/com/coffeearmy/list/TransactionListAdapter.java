package com.coffeearmy.list;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TransactionListAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private int layoutResourceId;
	private ArrayList<String> objects;

	protected static class ViewHolder {
		TextView transaction;
	}

	public TransactionListAdapter(Context context, int layoutid,
			ArrayList<String> obj) {
		super(context, layoutid, obj);

		mContext = context;
		layoutResourceId = layoutid;
		objects = obj;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();

			holder.transaction = (TextView) convertView
					.findViewById(android.R.id.text1);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (!objects.isEmpty() && objects.get(position)!=null) {
			holder.transaction.setText(objects.get(position));
			if (objects.get(position).contains("+")) {
				holder.transaction.setTextColor(Color.GREEN);
			} else {
				holder.transaction.setTextColor(Color.RED);
			}
		}
		
		return convertView;
	}

	public void notifyListChanged(ArrayList<String> newTransactionList) {

		this.objects.clear();
		this.objects.addAll(newTransactionList);
		this.notifyDataSetChanged();

	}
}
