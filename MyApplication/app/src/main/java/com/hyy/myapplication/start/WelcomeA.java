package com.hyy.myapplication.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hyy.myapplication.R;

public class WelcomeA extends Activity
{
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_welcome);
        //延迟两秒后执行run方法中的页面跳转
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(WelcomeA.this,LoginActivity.class);
                startActivity(intent);
                WelcomeA.this.finish();
            }
        },2000);
    }
}
