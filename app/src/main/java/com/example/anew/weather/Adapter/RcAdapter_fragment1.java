package com.example.anew.weather.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anew.weather.Activity.Main2Activity;
import com.example.anew.weather.R;
import com.example.anew.weather.Uutil.Data;

import java.util.List;




/**
 * Created by new on 2018/3/18.
 */

public class RcAdapter_fragment1 extends RecyclerView.Adapter<RcAdapter_fragment1.ViewHolder> {
    private List<Data> mDataList;
    //final ViewGroup parent;
    private static final int TYPE_ONE=1;
    private static final int TYPE_TWO=2;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image,image2,image3;
        TextView title;
        TextView content;
        View nview;
        Context context;
        int type;



        public ViewHolder(View view,Context c,int t)
        {
            super(view);
            nview=view;
            context=c;
            type=t;
            if(type==TYPE_ONE){
                image=(ImageView) view.findViewById(R.id.avatar);   //缓存布局
                title=(TextView) view.findViewById(R.id.item_name);
                content=(TextView) view.findViewById(R.id.content);
            }
            else{
                image=(ImageView) view.findViewById(R.id.avatar);   //缓存布局
                image2=(ImageView) view.findViewById(R.id.avatar2);
                image3=(ImageView) view.findViewById(R.id.avatar3);
                title=(TextView) view.findViewById(R.id.item_name);
                content=(TextView) view.findViewById(R.id.content);
            }

        }
    }

    public RcAdapter_fragment1(List<Data> dataList)
    {
        mDataList=dataList;
    }   //  FruitAdapter的构造方法

    public void addData(List list){
        mDataList.addAll(0,list);
       notifyItemRangeChanged(0,list.size());
    }
    @Override
    public int getItemViewType(int position) {
        //判断第三张图片是否存在
        String p03=mDataList.get(position).getThumbnail_pic_s03();
        if(p03==null){
        return TYPE_ONE;
        }
        else {
        return TYPE_TWO;
        }
        //return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)    //子项布局
    {
        View view;
        if(viewType==TYPE_ONE){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one,parent,false);
        }else{
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two,parent,false);
        }

        final ViewHolder holder=new ViewHolder(view,parent.getContext(),viewType);

        holder.nview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int position=holder.getAdapterPosition();
            Data data=mDataList.get(position);

            Main2Activity.actionStart(parent.getContext(),data.getUrl());
        }
    });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position)    //  连接数据   position位置
    {
        Data data=mDataList.get(position);
        if(holder.type==TYPE_ONE){
            holder.title.setText(data.getTitle());
            //holder.image.setImageResource(data.getThumbnail_pic_s());
            String urlStr=data.getThumbnail_pic_s();
            Glide.with(holder.context).load(urlStr).into(holder.image);
            holder.content.setText(data.getDate());
        }
        else {
            holder.title.setText(data.getTitle());
            //holder.image.setImageResource(data.getThumbnail_pic_s());
            String urlPic=data.getThumbnail_pic_s();
            String urlPic2=data.getThumbnail_pic_s02();
            String urlPic3=data.getThumbnail_pic_s03();
            Glide.with(holder.context).load(urlPic).into(holder.image);
            Glide.with(holder.context).load(urlPic2).into(holder.image2);
            Glide.with(holder.context).load(urlPic3).into(holder.image3);
            holder.content.setText(data.getDate());
        }

    }
    @Override
    public int getItemCount()
    {
        return mDataList.size();
    }

}
