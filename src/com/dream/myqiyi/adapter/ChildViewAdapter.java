package com.dream.myqiyi.adapter;

import java.util.List;
import java.util.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public  class ChildViewAdapter extends BaseListAdapter{

	public ChildViewAdapter(LayoutInflater inflater, int view, int[] viewId,
			List<Map<String, Object>> data, String[] dataName) {
		super(inflater, view, viewId, data, dataName);
	}
	class ViewHolder{
		TextView textView1;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = getInflater().inflate(getView(), null);
			final int[] viewId = getViewId();
			holder.textView1 = (TextView) convertView.findViewById(viewId[0]);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Map<String, Object> map = getData().get(position);
		String[] dataName = getDataName();
		holder.textView1.setText((String)map.get(dataName[0]));
		return convertView;
	}

}
