package com.example.anew.weather.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.anew.weather.Uutil.MyClassicsFooter;
import com.example.anew.weather.Uutil.MyClassicsHeader;
import com.example.anew.weather.Uutil.MyOkhttp;
import com.example.anew.weather.Uutil.NewsResult;
import com.example.anew.weather.Uutil.Title_bar;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;


public class BlankFragment1 extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private RcAdapter_fragment1 rcAdapter_fragment1;
    private List<Data> dataList;
    public ACache aCache;
    public SmartRefreshLayout smartRefreshLayout;
    static final int Normal=0; //加载数据
    static final int Refresh=1;//更新数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  view=inflater.inflate(R.layout.fragment_blank, container, false);
       // return view;
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_blank, null);
           //获取 okhttp请求的数据 toutiao
            initTitle(rootView);

            aCache=ACache.get(getActivity());
            String a=aCache.getAsString("top");
                if(a!=null){
                    Log.d("aaaass",a);
                    parseJsonWithGson(a,Normal);
                }
            setSrefreshLayout(rootView);
            //initView();
            // requesImages();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;


    }

    //设置标题栏
    public void initTitle(View rootview){
        View titlebarview=rootview.findViewById(R.id.title_bar);
        Title_bar title_bar=new Title_bar(titlebarview);
        title_bar.setTitleText("首页");
    }


    //使用Gson来解析json数据
    private void parseJsonWithGson(String jsonData,int i) {
        //Type type = new TypeToken<List<Data>>() {
        //}.getType();
        Gson gson = new Gson();
        NewsResult newsResult=gson.fromJson(jsonData,NewsResult.class);
        Log.i("aaaa",newsResult.getReason());
        Log.i("aaaa",newsResult.getError_code());
        Log.i("aaaa",newsResult.getNews().getList().size()+"");
//        dataList=new ArrayList<Data>();
        dataList=newsResult.getNews().getList();
//        for (Data data : newsResult.getNews().getList()) {
//            Log.i("aaaa", data.getThumbnail_pic_s());
//            Log.i("aaaa", data.getTitle());
//        }
        SetAdapter(dataList,i);
    }

    private void SetAdapter(List<Data> list,int i) {
        switch (i){
            case 0:
                recyclerView=rootView.findViewById(R.id.recyclerview1);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false);
                //Animator
                DefaultItemAnimator animator = new DefaultItemAnimator();
                    /*设置动画时间
                    //animator.setAddDuration(2000);
                    //animator.setRemoveDuration(2000);*/
                recyclerView.setItemAnimator(animator);
                recyclerView.setLayoutManager(layoutManager);
                rcAdapter_fragment1=new RcAdapter_fragment1(list);
                recyclerView.setAdapter(rcAdapter_fragment1);
                break;
            case 1:
                //
                if(aCache.getAsString("top")!=null) {

                    rcAdapter_fragment1.addData(list);
                }
                break;
        }


    }

    private void setSrefreshLayout(View rootView){
        smartRefreshLayout=rootView.findViewById(R.id.srefreshLayout);
        smartRefreshLayout.setRefreshHeader(new MyClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new MyClassicsFooter(getActivity()));


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                //Toast.makeText(getActivity(),"ssssss",Toast.LENGTH_LONG).show();

                MyOkhttp okhttp=new MyOkhttp(0,getActivity());
                okhttp.requesImages();
                String a=aCache.getAsString("top");
                if(a!=null){
                    parseJsonWithGson(a,Refresh);
                }
                //myAdapter.refresh(newList);
                smartRefreshLayout.finishRefresh(3000/*,false*/);//设置刷新完成的时间
                //smartRefreshLayout.finishRefresh();
            }
        });
    }


//    private void initView(){
//        String urlStr ="http://08imgmini.eastday.com/mobile" +
//                "/20191125/2019112423_4811fddb51d74d0887cf508c1ba159b3_5784_mwpm_03200403.jpg";
//            dataList=new ArrayList<>();// essential
//                for (int i = 0; i < 10; i++) {
//            Data ss = new Data();
//            ss.setTitle("食品"+(i+1));
//            ss.setThumbnail_pic_s(urlStr);
//            ss.setDate("这是对食品"+(i+1)+"的描述...");
//            dataList.add(ss);
//
//            RecyclerView recyclerView=rootView.findViewById(R.id.recyclerview1);
//            Log.d("aa", String.valueOf(dataList.size()));
//            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
//                            LinearLayoutManager.VERTICAL, false);
//            recyclerView.setLayoutManager(layoutManager);
//            RcAdapter_fragment1 rcAdapter_fragment1=new RcAdapter_fragment1(dataList);
//            recyclerView.setAdapter(rcAdapter_fragment1);
//        }
//
//    }


}
