package com.dream.myqiyi.home;

import android.R;
import android.app.Activity;
import android.os.Bundle;

public class ChildActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
	}
}
