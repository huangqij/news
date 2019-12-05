package com.example.anew.weather.Uutil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by new on 2019/11/26.
 */

public class MyOkhttp {

        public String type[]={"头条","社会","国内","国际","娱乐",
            "体育","军事","科技","财经","时尚"};
        public String type_english[]={"top",
                "shehui","junshi","tiyu","yule","guoji", "shishang"};
        public Context context;
        public int type_int;
    public MyOkhttp(int t, Context c) {
        type_int=t;
        context=c;
    }


    public void requesImages() {
        //设置url
        //String url="http://112.124.22.238:8081/course_api/banner/query?type=1";
        //String url= "http://guolin.tech/api/china";
        //String url="http://v.juhe.cn/weather/index";
        final String url = "http://v.juhe.cn/toutiao/index";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("type", type_english[type_int])
                .add("key", "f414a78f104e951340303bdf3d14c7f7")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("aaaaa", "fail");
                Toast.makeText(context,"网络请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Log.i("aaaaa", json);
                        ACache acache=ACache.get(context);
                       // aCache=ACache.get(MainActivity.this);
                        acache.put(type_english[type_int],json,acache.TIME_halfMonth);
                        //String a=acache.getAsString("top");
                        //Log.d("aaaaww",a);
                        //parseJsonWithGson(json);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    //使用Gson来解析json数据
//    private void parseJsonWithGson(String jsonData) {
//        //Type type = new TypeToken<List<Data>>() {
//        //}.getType();
//        Gson gson = new Gson();
//        NewsResult newsResult=gson.fromJson(jsonData,NewsResult.class);
//        Log.i("aaaa",newsResult.getReason());
//        Log.i("aaaa",newsResult.getError_code());
//        Log.i("aaaa",newsResult.getNews().getList().size()+"");
//        listdata=newsResult.getNews().getList();
//        for (Data data : newsResult.getNews().getList()) {
//            Log.i("aaaa", data.getThumbnail_pic_s());
//            Log.i("aaaa", data.getTitle());
//        }
//    }

}
