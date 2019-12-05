package com.example.anew.weather.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.anew.weather.R;

public class SecondComingActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_coming);
        ActivityCollector.addActivity(this);

        TextView t=findViewById(R.id.text);
        t.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putBoolean("isOpen",false);
        editor.apply();
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ActivityCollector.finishAll();
        return super.onKeyDown(keyCode, event);
    }
}
