package com.hyy.myapplication.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hyy.myapplication.R;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btRegist=(Button)this.findViewById(R.id.regist_btn);
        OnClickListener ocl=new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Login(arg0);
                //
            }

        };
        btRegist.setOnClickListener(ocl);
    }

    public void Login(View arg0)
    {
        Button bt=(Button)findViewById(arg0.getId());
        String text=bt.getText().toString();
        if (text.equals("注册"))
        {
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            RegisterActivity.this.finish();
        }
    }
}