package com.example.anew.weather.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anew.weather.Adapter.RcAdapter_fragment1;
import com.example.anew.weather.R;
import com.example.anew.weather.Uutil.ACache;
import com.example.anew.weather.Uutil.Data;
import com.example.anew.weather.Uutil.NewsResult;
import com.google.gson.Gson;

import java.util.List;


public class Fragment2_6 extends Fragment {
    View rootView;
    private List<Data> dataList;
    public ACache aCache;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_fragment2_6, container, false);

        aCache=ACache.get(getActivity());
        String a=aCache.getAsString("shishang");
        if(a!=null){
            Log.d("aaaass",a);
            parseJsonWithGson(a);
        }
        return rootView;
    }

    private void parseJsonWithGson(String jsonData) {
        //Type type = new TypeToken<List<Data>>() {
        //}.getType();
        Gson gson = new Gson();
        NewsResult newsResult=gson.fromJson(jsonData,NewsResult.class);
        Log.i("aaaa",newsResult.getReason());
        Log.i("aaaa",newsResult.getError_code());
        Log.i("aaaa",newsResult.getNews().getList().size()+"");
//        dataList=new ArrayList<Data>();
        dataList=newsResult.getNews().getList();
        for (Data data : newsResult.getNews().getList()) {
            Log.i("aaaa", data.getThumbnail_pic_s());
            Log.i("aaaa", data.getTitle());
        }
        SetAdapter(dataList);
    }

    private void SetAdapter(List<Data> list) {
        RecyclerView recyclerView=rootView.findViewById(R.id.recyclerview_frg2_6);
        Log.d("aa", String.valueOf(list.size()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        //Animator
        DefaultItemAnimator animator = new DefaultItemAnimator();
        //设置动画时间
        //animator.setAddDuration(2000);
        //animator.setRemoveDuration(2000);
        recyclerView.setItemAnimator(animator);

        recyclerView.setLayoutManager(layoutManager);
        RcAdapter_fragment1 rcAdapter_fragment1=new RcAdapter_fragment1(list);
        recyclerView.setAdapter(rcAdapter_fragment1);
    }
}

