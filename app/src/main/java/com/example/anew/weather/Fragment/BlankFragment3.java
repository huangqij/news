package com.example.anew.weather.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anew.weather.R;
import com.example.anew.weather.Uutil.Title_bar;


public class BlankFragment3 extends Fragment {
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_blank3, container, false);

        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_blank3, null);
            initTitle(rootView);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

    public void initTitle(View v){
        View titlebarview=v.findViewById(R.id.title_bar);
        Title_bar title_bar=new Title_bar(titlebarview);
        title_bar.setTitleText("消息");
    }

}
