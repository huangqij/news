package com.example.anew.weather.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anew.weather.Adapter.FragmentAdapter;
import com.example.anew.weather.R;
import com.example.anew.weather.Uutil.Title_bar;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;




public class BlankFragment2 extends Fragment implements View.OnClickListener {
    View rootView;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_blank2, container, false);

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_blank2, null);
           initTitle(rootView);
            initView();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;


    }
    public void initTitle(View v){
        View titlebarview=v.findViewById(R.id.title_bar);
        Title_bar title_bar=new Title_bar(titlebarview);
        title_bar.setTitleText("频道");
    }

    public void initView() {
        viewPager = rootView.findViewById(R.id.viewpager);

        textView1 = rootView.findViewById(R.id.textview_shehui);
        textView1.setOnClickListener(this);
        textView2 = rootView.findViewById(R.id.textview_junshi);
        textView2.setOnClickListener(this);
        textView3 = rootView.findViewById(R.id.textview_tiyu);
        textView3.setOnClickListener(this);
         textView4 = rootView.findViewById(R.id.textview_yule);
        textView4.setOnClickListener(this);
        textView5 = rootView.findViewById(R.id.textview_guoji);
        textView5.setOnClickListener(this);
        textView6 = rootView.findViewById(R.id.textview_shishang);
        textView6.setOnClickListener(this);

        List<Fragment> fragmentList=new ArrayList<Fragment>();
        Fragment fragment1=new Fragment2_1();
        Fragment fragment2=new Fragment2_2();
        Fragment fragment3=new Fragment2_3();
        Fragment fragment4=new Fragment2_4();
        Fragment fragment5=new Fragment2_5();
        Fragment fragment6 =new Fragment2_6();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        fragmentList.add(fragment6);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentAdapter fragmentAdapter=new FragmentAdapter(fragmentManager,fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
            switch (position){
                case 0:
                    setBgcolor(textView1,true);
                    setBgcolor(textView2,false);
                    setBgcolor(textView3,false);
                    setBgcolor(textView4,false);
                    setBgcolor(textView5,false);
                    setBgcolor(textView6,false);
                    break;
                case 1:
                    setBgcolor(textView1,false);
                    setBgcolor(textView2,true);
                    setBgcolor(textView3,false);
                    setBgcolor(textView4,false);
                    setBgcolor(textView5,false);
                    setBgcolor(textView6,false);
                    break;
                case 2:
                    setBgcolor(textView1,false);
                    setBgcolor(textView2,false);
                    setBgcolor(textView3,true);
                    setBgcolor(textView4,false);
                    setBgcolor(textView5,false);
                    setBgcolor(textView6,false);
                    break;
                case 3:
                    setBgcolor(textView1,false);
                    setBgcolor(textView2,false);
                    setBgcolor(textView3,false);
                    setBgcolor(textView4,true);
                    setBgcolor(textView5,false);
                    setBgcolor(textView6,false);
                    break;
                case 4:
                    setBgcolor(textView1,false);
                    setBgcolor(textView2,false);
                    setBgcolor(textView3,false);
                    setBgcolor(textView4,false);
                    setBgcolor(textView5,true);
                    setBgcolor(textView6,false);
                    break;
                case 5:
                    setBgcolor(textView1,false);
                    setBgcolor(textView2,false);
                    setBgcolor(textView3,false);
                    setBgcolor(textView4,false);
                    setBgcolor(textView5,false);
                    setBgcolor(textView6,true);
                    break;
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void setBgcolor(TextView view,Boolean b){
        if(b){
            //view.setTextColor(android.graphics.Color.parseColor("#00BFFF"));
            view.setTextColor(getResources().getColor(R.color.deepskyblue));
            //view.setFakeBoldText(true);
            view.setTypeface(Typeface.DEFAULT_BOLD);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); //单位最好使用SP
           // view.setBackgroundColor(c);

        }
        else {
           // int c=getResources().getColor(R.color.deepskyblue);
           // view.setTextColor(android.graphics.Color.parseColor("#808080"));
            view.setTextColor(getResources().getColor(R.color.gray));
            //view.setFakeBoldText(false);
            view.setTypeface(Typeface.DEFAULT);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); //单位最好使用SP
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_shehui:
                viewPager.setCurrentItem(0);
                break;
            case R.id.textview_junshi:
                viewPager.setCurrentItem(1);
                break;
            case R.id.textview_tiyu:
                viewPager.setCurrentItem(2);
                break;
            case R.id.textview_yule:
                viewPager.setCurrentItem(3);
                break;
            case R.id.textview_guoji:
                viewPager.setCurrentItem(4);
                break;
            case R.id.textview_shishang:
                viewPager.setCurrentItem(5);
                break;

        }
    }
}
