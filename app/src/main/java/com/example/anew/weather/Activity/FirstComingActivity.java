package com.example.anew.weather.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anew.weather.Adapter.ViewpageAdapter;
import com.example.anew.weather.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FirstComingActivity extends AppCompatActivity implements View.OnClickListener{
    Button textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ActivityCollector.addActivity(this);

        initView();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void initView(){

        textView=findViewById(R.id.text);
        textView.setOnClickListener(this);

        ViewPager viewPager=findViewById(R.id.viewpager);
        List<View> mview=new ArrayList<View>();
        LayoutInflater inflater=getLayoutInflater();
        View view1=inflater.inflate(R.layout.activity_first1,null);
        View view2=inflater.inflate(R.layout.activity_first1,null);
        View view3=inflater.inflate(R.layout.activity_first1,null);
        mview.add(view1);
        mview.add(view2);
        mview.add(view3);

        final List<View> dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));


        ViewpageAdapter viewpageAdapter=new ViewpageAdapter(this,mview);
        viewPager.setAdapter(viewpageAdapter);
        viewPager.setCurrentItem(0);
        dots.get(0).setBackgroundResource(R.drawable.dot_select);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        dots.get(0).setBackgroundResource(R.drawable.dot_select);
                        dots.get(1).setBackgroundResource(R.drawable.dot_normal);
                        dots.get(2).setBackgroundResource(R.drawable.dot_normal);
                        textView.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        dots.get(0).setBackgroundResource(R.drawable.dot_normal);
                        dots.get(1).setBackgroundResource(R.drawable.dot_select);
                        dots.get(2).setBackgroundResource(R.drawable.dot_normal);
                        textView.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        dots.get(0).setBackgroundResource(R.drawable.dot_normal);
                        dots.get(1).setBackgroundResource(R.drawable.dot_normal);
                        dots.get(2).setBackgroundResource(R.drawable.dot_select);
                        textView.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
   }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text:
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putBoolean("isfirst",false);
                editor.apply();
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ActivityCollector.finishAll();
        return super.onKeyDown(keyCode, event);
    }
}
