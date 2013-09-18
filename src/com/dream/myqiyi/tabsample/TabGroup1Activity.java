package com.dream.myqiyi.tabsample;

import com.dream.myqiyi.home.HomeActivity;

import android.content.Intent;
import android.os.Bundle;

public class TabGroup1Activity extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startChildActivity("OptionsActivity", new Intent(this,HomeActivity.class));
    }
}
