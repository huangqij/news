package com.example.anew.weather.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.anew.weather.Activity.MainActivity;

import java.util.List;

/**
 * Created by ENZ on 2016/9/29.
 */
public class ViewpageAdapter extends PagerAdapter {
    private Context context;
    private List<View> viewdata;

    public ViewpageAdapter(Activity activity, List<View> mview) {
        context = activity;
        viewdata = mview;
    }
    //这个方法是获取一共有多少个item
    @Override
    public int getCount() {
        return viewdata.size();
    }

    //这个就这样写就OK ，无需管
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //这个方法用来实例化页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewdata.get(position),0);
        return viewdata.get(position);
    }


    //删除实例化页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewdata.get(position));
    }
}
