package com.dream.myqiyi.tabsample;

import android.content.Intent;
import android.os.Bundle;

import com.dream.myqiyi.account.AccountActivity;

public class TabGroup3Activity extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startChildActivity("OptionsActivity", new Intent(this,AccountActivity.class));
    }
}
