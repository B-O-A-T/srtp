package com.hyy.myapplication.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyy.myapplication.MainActivity;
import com.hyy.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class LoginActivity extends Activity {

    private EditText name,pwd;
    private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取事件源对象
        Button btLogin=(Button)this.findViewById(R.id.login_btn);
        TextView btRegist=(TextView)this.findViewById(R.id.register_btn);
        //事件处理方法
        OnClickListener ocl=new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Login(arg0);
                //
            }

        };
        btLogin.setOnClickListener(ocl);

        btRegist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
        //btRegist.setOnClickListener(ocl);
    }
    public void Login(View arg0)
    {
        name=(EditText)findViewById(R.id.login_account);
        pwd=(EditText)findViewById(R.id.login_password);
        //tv_result = (TextView) findViewById(R.id.tv_result);
        Button bt=(Button)findViewById(arg0.getId());
        TextView tv=(TextView)findViewById(arg0.getId());
        String text1=tv.getText().toString();
        String text=bt.getText().toString();
        if(text.equals("登录")){
            System.out.println("------>>>>"+name);
            Log.d("MY QQ","------->>>"+name);
            //获取输入的账号和密码
            final String userName=name.getText().toString();
            final String userPass=pwd.getText().toString();
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPass)) {
                Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
            } else {
                // 开启子线程
                new Thread() {
                    public void run() {
                        loginByPost(userName, userPass); // 调用loginByPost方法

                    };
                }.start();
            }
            if(userName.equals(userPass)){
                // 判断
                // 显示获取到的用户信息

                //new AlertDialog.Builder(this).setTitle("登录").setMessage("账号："+userName+"\r\n密码："+"******").setNegativeButton("确定",null).show();
                //提示框
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
            else{
                Toast toast=Toast.makeText(this,"您输入的账号和密码不一致，请重新输入",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else{
            name.setText("");
            pwd.setText("");
        }
        if (text1.equals("未注册？"))
        {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }



    private Toast setNegativeButton(String string, Object object) {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * POST请求操作
     *
     * @param userName
     * @param userPass
     */
    public void loginByPost(String userName, String userPass) {

        try {

            // 请求的地址
            String spec = "http://172.22.163.69:5000/login?next=%2Findex";
            // 根据地址创建URL对象
            URL url = new URL(spec);
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 传递的数据
            String data = "username=" + URLEncoder.encode(userName, "UTF-8")
                    + "&userpass=" + URLEncoder.encode(userPass, "UTF-8");
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(data.getBytes().length));
            // 设置请求的头
            urlConnection
                    .setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");

            urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
            urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
            //setDoInput的默认值就是true
            //获取输出流
            OutputStream os = urlConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            if (urlConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                final String result = new String(baos.toByteArray());

                // 通过runOnUiThread方法进行修改主线程的控件内容
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里把返回的数据写在控件上 会出现什么情况尼
                        tv_result.setText(result);
                    }
                });

            } else {
                System.out.println("链接失败.........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}