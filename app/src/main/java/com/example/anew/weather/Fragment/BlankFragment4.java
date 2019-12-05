package com.example.anew.weather.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anew.weather.R;


public class BlankFragment4 extends Fragment {
    View rootView;
    TextView text,content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_blank4, container, false);
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_blank4, null);
            initView();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

    public void initView(){
        text=rootView.findViewById(R.id.text);
        content=rootView.findViewById(R.id.content);
        text.setText(getResources().getText(R.string.text));
        content.setText(getResources().getText(R.string.content));
    }

}
