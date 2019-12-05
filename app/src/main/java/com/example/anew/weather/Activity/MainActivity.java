package com.example.anew.weather.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;


import com.example.anew.weather.Fragment.BlankFragment1;
import com.example.anew.weather.Fragment.BlankFragment2;
import com.example.anew.weather.Fragment.BlankFragment3;
import com.example.anew.weather.Fragment.BlankFragment4;

import com.example.anew.weather.R;
import com.example.anew.weather.Uutil.ACache;
import com.example.anew.weather.Uutil.Data;
import com.example.anew.weather.Uutil.MyOkhttp;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mfragmentTabHost;
    //ArrayList<Class> classdata=new ArrayList<Class>();//装有加载碎片内容的类
    private Class[] classedata={BlankFragment1.class,BlankFragment2.class
    ,BlankFragment3.class,BlankFragment4.class};
    String tabname[]={"首页","频道","通知","我的"};

    int image[]={
            R.drawable.select_home,R.drawable.select_channel,
            R.drawable.select_msg, R.drawable.select_my
    };
    public ACache aCache;
    private List<Data> dataList;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);
        gotoSecondComingActivity();
        gotoFirstComingActivity();

        getHttpData();
        view=getLayoutInflater().inflate(R.layout.activity_main,null);
        setContentView(view);
        setTabhost();

    }

    @Override
    protected void onStop() {
        Log.i("aaaaa","onStop");
        super.onStop();
        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putBoolean("isOpen",true);
        editor.apply();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("aaaaa","onDestroy");
        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putBoolean("isOpen",true);
        editor.apply();
        ActivityCollector.removeActivity(this);
    }

    private void gotoFirstComingActivity(){
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        //如果找不到，就用后面的 true
        Boolean isfirst=sharedPreferences.getBoolean("isfirst",true);
        if(isfirst){
            Intent intent=new Intent(MainActivity.this,FirstComingActivity.class);
            startActivity(intent);
        }
    }
    private void gotoSecondComingActivity(){
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        //如果找不到，就用后面的 true
        Boolean isfirst=sharedPreferences.getBoolean("isfirst",true);
        Boolean isOpen=sharedPreferences.getBoolean("isOpen",true);
        if((!isfirst)&&isOpen){
            Intent intent=new Intent(MainActivity.this,SecondComingActivity.class);
            startActivity(intent);
        }
    }


    private void getHttpData(){
        for (int i=0;i<7;i++){
           // MyOkhttp myOkhttp0=new MyOkhttp(i,MainActivity.this);
            //myOkhttp0.requesImages();

        }
    }
    //Tabhost
    private void setTabhost(){
        //      1,找到FragmentTabHost的控件
        mfragmentTabHost=findViewById(android.R.id.tabhost);
        //      2,配置该控件(找到要替换的布局FrameLayout)
        mfragmentTabHost.setup(MainActivity.this, getSupportFragmentManager(), R.id.realtabcontent);
        for(int i=0;i<tabname.length;i++){
            //      3,添加table new出来了付了一个id
            TabHost.TabSpec tabSpec = mfragmentTabHost.newTabSpec(i+"");
            View view=getTabView(i,MainActivity.this);
            //      5,把自定义布局添加到TabSpec中
            tabSpec.setIndicator(view);
            //      6.把tabSpec添加到tabhost里面
            mfragmentTabHost.addTab(tabSpec,classedata[i], null);
        }
        //设置没有竖白线
        mfragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mfragmentTabHost.setCurrentTab(0);

    }

    private View getTabView(int index, Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        ImageView tabImage = inflate.findViewById(R.id.item_imge);
        TextView tabTitle = inflate.findViewById(R.id.item_text);
        tabImage.setImageResource(image[index]); // 通过selector来控制图片的改变
        tabTitle.setText(tabname[index]);// 通过selector来控制文字颜色的改变
        return inflate;
    }

    private void setLunbo(){

    }


}




