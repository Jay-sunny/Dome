package com.example.administrator.viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //定义全局属性
    ViewPager viewPager = null;
    PagerTabStrip tabStrip = null;

    //原因是我们的ViewPager需要传入一个是View视图
    ArrayList<View> viewContainter = new ArrayList<View>();
    //每项的标题
    ArrayList<String> titleContainer = new ArrayList<String>();

    public String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //全局属性的初始化
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) findViewById(R.id.tabstrip);

        //取消tab下面的长横线
        tabStrip.setDrawFullUnderline(false);

        //设置tab的背景色
//        tabStrip.setBackgroundColor(getResources().getColor(R.color.bg));
        tabStrip.setBackgroundResource(R.color.bg);
        //设置当前tab页签的下划线颜色
//        tabStrip.setTabIndicatorColor(getResources().getColor(R.color.red));
        tabStrip.setTabIndicatorColorResource(R.color.red);

        //设置文本的间距
        tabStrip.setTextSpacing(500);

        //创建一个View视图：从我们的布局文件中得到
        View view1 = LayoutInflater.from(this).inflate(R.layout.table1,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.table2,null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.table3,null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.table4,null);

        //viewpager开始添加view  添加到集合中去   ArrayList<String> titleContainer = new ArrayList<String>();
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);

        //页签项
        titleContainer.add("第1个页面");
        titleContainer.add("第2个页面");
        titleContainer.add("第3个页面");
        titleContainer.add("第4个页面");

        //设置适配器
        viewPager.setAdapter(new PagerAdapter() {
            //viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(viewContainter.get(position));
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            //滑动切换的时候销毁当前的组件
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
        });
        //添加事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "--------changed:" + arg0);
            }
        });
    }
}
