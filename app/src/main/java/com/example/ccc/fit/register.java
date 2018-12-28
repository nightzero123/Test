package com.example.ccc.fit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class register extends AppCompatActivity
{
    private Button button;

    //private Button readButton;

    private EditText accountEdit;
    private EditText passwordEdit;
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.button);
        accountEdit = (EditText) findViewById(R.id.editText2);
        passwordEdit = (EditText) findViewById(R.id.editText3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //

                if (validate())
                {
                    // 检查是否登陆成功
                    try {
                        if (login()) {
                            Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register.this, Main2Activity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this, "用户名称或者密码错误，请重新输入！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                /*
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", accountEdit.getText().toString());
                editor.putString("password", passwordEdit.getText().toString());
                editor.commit();
                finish();
                Toast.makeText(register.this, "注册成功", Toast.LENGTH_LONG).show();
                */
                 }
        });

    }
    private boolean validate() {
        String username = accountEdit.getText().toString();
        if (username.equals("")) {
            Toast.makeText(register.this, "用户名称是必填项！", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        String pwd = passwordEdit.getText().toString();
        if (pwd.equals("")) {
            Toast.makeText(register.this, "用户密码是必填项！", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }
    private boolean login() throws JSONException {
        // 获得用户名称
        String username = accountEdit.getText().toString().trim();
        // 获得密码
        String pwd = passwordEdit.getText().toString().trim();
        // 获得登录结果
        query(username, pwd);
        boolean flag = false;
        JSONObject object = null;
        while(true) {
            if (result != null) {
                object = new JSONObject(result);
                long code = object.getLong("code");
                if (code != 0) {
                    flag = false;
                } else {
                    //System.out.println("LoginActivity result: " + result);
                    // 将此服务器返回的此用户信息保存起来
                    //saveUserMsg(result);
                    object = new JSONObject(object.getString("data"));
                    object = new JSONObject(object.getString("info"));
                    long id = object.getLong("id");
                    String account = object.getString("account");
                    String email = object.getString("email");
                    String phone = object.getString("phone");
                    //可以继续...

                    flag = true;
                }
                return flag;
            }
        }
    }
    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            //setResult(e.getMessage(), false);
            result = e.getMessage();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            //setResult(response.body().string(), true);
            result = response.body().string();
        }
    };
    public void query(String account, String password) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("account", account)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://47.94.252.84:80/site/index")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}

