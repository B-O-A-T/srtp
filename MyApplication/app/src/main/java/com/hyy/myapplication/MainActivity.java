package com.hyy.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener,OnPageChangeListener{

                 // 底部菜单4个Linearlayout
                 private LinearLayout ll_home;
                 private LinearLayout ll_quanzi;
                 private LinearLayout ll_discover;
                 private LinearLayout ll_me;

                 // 底部菜单4个ImageView
                 private ImageView iv_home;
                 private ImageView iv_quanzi;
                 private ImageView iv_discover;
                 private ImageView iv_me;

                 // 底部菜单4个菜单标题
                 private TextView tv_home;
                 private TextView tv_quanzi;
                 private TextView tv_discover;
                 private TextView tv_me;

                 // 中间内容区域
                 private ViewPager viewPager;

                 // ViewPager适配器ContentAdapter
                 private ContentAdapter adapter;

                 private List<View> views;

                 @Override
         protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_main);

                 // 初始化控件
                 initView();
                 // 初始化底部按钮事件
                 initEvent();

             }

             private void initEvent() {
                 // 设置按钮监听
                 ll_home.setOnClickListener(this);
                 ll_quanzi.setOnClickListener(this);
                 ll_discover.setOnClickListener(this);
                 ll_me.setOnClickListener(this);
                         //设置ViewPager滑动监听
                 viewPager.setOnPageChangeListener(this);
             }

                 private void initView() {

                 // 底部菜单4个Linearlayout
                 this.ll_home = (LinearLayout) findViewById(R.id.ll_home);
                this.ll_quanzi = (LinearLayout) findViewById(R.id.ll_quanzi);
                 this.ll_discover = (LinearLayout) findViewById(R.id.ll_discover);
                 this.ll_me = (LinearLayout) findViewById(R.id.ll_me);

                 // 底部菜单4个ImageView
                this.iv_home = (ImageView) findViewById(R.id.iv_home);
                this.iv_quanzi = (ImageView) findViewById(R.id.iv_quanzi);
                 this.iv_discover = (ImageView) findViewById(R.id.iv_discover);
                 this.iv_me = (ImageView) findViewById(R.id.iv_me);

                 // 底部菜单4个菜单标题
                 this.tv_home = (TextView) findViewById(R.id.tv_home);
                 this.tv_quanzi = (TextView) findViewById(R.id.tv_quanzi);
                 this.tv_discover = (TextView) findViewById(R.id.tv_discover);
                 this.tv_me = (TextView) findViewById(R.id.tv_me);

                 // 中间内容区域ViewPager
                 this.viewPager = (ViewPager) findViewById(R.id.vp_content);

                 // 适配器
                 View page_01 = View.inflate(MainActivity.this, R.layout.page_01, null);
                 View page_02 = View.inflate(MainActivity.this, R.layout.page_02, null);
                 View page_03 = View.inflate(MainActivity.this, R.layout.page_03, null);
                 View page_04 = View.inflate(MainActivity.this, R.layout.page_04, null);

                 views = new ArrayList<View>();
                 views.add(page_01);
                 views.add(page_02);
                     views.add(page_03);
                views.add(page_04);
                        this.adapter = new ContentAdapter(views);
                 viewPager.setAdapter(adapter);
                    }

                 @Override
         public void onClick(View v) {
                 // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
                restartBotton();
                // ImageView和TetxView置为绿色，页面随之跳转
                switch (v.getId()) {
                     case R.id.ll_home:
                             iv_home.setImageResource(R.drawable.homepage_pressed);
                             tv_home.setTextColor(0xff232325);
                             viewPager.setCurrentItem(0);
                             break;
                     case R.id.ll_quanzi:
                             iv_quanzi.setImageResource(R.drawable.quanzi_pressed);
                             tv_quanzi.setTextColor(0xff232325);
                             viewPager.setCurrentItem(1);
                             break;
                     case R.id.ll_discover:
                             iv_discover.setImageResource(R.drawable.discover_pressed);
                             tv_discover.setTextColor(0xff232325);
                             viewPager.setCurrentItem(2);
                             break;
                     case R.id.ll_me:
                             iv_me.setImageResource(R.drawable.me_pressed);
                             tv_me.setTextColor(0xff232325);
                             viewPager.setCurrentItem(3);
                            break;

                     default:
                             break;
                    }

             }

                 private void restartBotton() {
                 // ImageView置为灰色
                 iv_home.setImageResource(R.drawable.homepage_normal);
                 iv_quanzi.setImageResource(R.drawable.quanzi_normal);
                 iv_discover.setImageResource(R.drawable.discover_normal);
                 iv_me.setImageResource(R.drawable.me_normal);
                 // TextView置为白色
                 tv_home.setTextColor(0xff232325);
                 tv_quanzi.setTextColor(0xff232325);
                 tv_discover.setTextColor(0xff232325);
                tv_me.setTextColor(0xff232325);
            }

        public void onPageScrollStateChanged(int arg0) {
                }


         public void onPageScrolled(int arg0, float arg1, int arg2) {

             }


         public void onPageSelected(int arg0) {
                 restartBotton();
                 //当前view被选择的时候,改变底部菜单图片，文字颜色
                switch (arg0) {
                    case 0:
                            iv_home.setImageResource(R.drawable.homepage_pressed);
                            tv_home.setTextColor(0xff1B940A);
                           break;
                    case 1:
                            iv_quanzi.setImageResource(R.drawable.quanzi_pressed);
                           tv_quanzi.setTextColor(0xff1B940A);
                            break;
                    case 2:
                            iv_discover.setImageResource(R.drawable.discover_pressed);
                            tv_discover.setTextColor(0xff1B940A);
                             break;
                    case 3:
                             iv_me.setImageResource(R.drawable.me_pressed);
                           tv_me.setTextColor(0xff1B940A);
                             break;

         default:
             break;
         }

     }
 }