package com.example.ccc.fit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String result;
    private static final String APP_ID = "101538268";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private static final String TAG = "Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTencent = Tencent.createInstance(APP_ID,MainActivity.this.getApplicationContext());
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.edit_1);
        passwordEdit = (EditText) findViewById(R.id.edit_2);
        login = (Button) findViewById(R.id.button2);
        android.widget.Button mBtnTextView = findViewById(R.id.button1);  // get the button, it is in activity_main.xml
        mBtnTextView.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View view)
            {

                android.content.Intent intent = new android.content.Intent(MainActivity.this, register.class);
                startActivity(intent);
                /*
                HttpUtil.sendOKHttpRequest("http://127.0.0.1/a.txt",new okhttp3.Callback()
                {

                    public void onResponse(Call call, Response response) throws IOException
                    {
                        String responseData=response.body().string();
                        EditText view = findViewById(R.id.edit_1);
                        view.setText(responseData);
                    }
                    public void onFailure(Call call,IOException e)
                    {

                    }
                });
                */
                //HttpUtil.postOKHttpRequest("http://127.0.0.1/a.txt");

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (validate())
                 {
                    // 检查是否登陆成功
                    try {
                        if (login()) {
                            Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "用户名称或者密码错误，请重新输入！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }


                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                SharedPreferences pre = getSharedPreferences("data", MODE_PRIVATE);
                if(account.equals(pre.getString("name", ""))&&password.equals(pre.getString("password", "")))
                {
                                    editor = pref.edit();
                                    int a=0;
                                    if (a==1) {
                                        editor.putBoolean("remember_password", true);
                                    }
                                    else{
                                        editor.clear();
                                    }
                                    editor.commit();
                                    android.content.Intent intent = new android.content.Intent(MainActivity.this,Main2Activity.class);
                                    startActivity(intent);
                                    finish();
                }

                                    else {
                                                 Toast.makeText(MainActivity.this, "用户名或密码不对", Toast.LENGTH_LONG).show();
                                          }

            }		});
        /*
        android.widget.Button mBtnTextView1 = findViewById(R.id.button2);  // get the button, it is in activity_main.xml
        mBtnTextView1.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(android.view.View view)
            {
                android.content.Intent intent = new android.content.Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
*/
    }
    public void buttonLogin(View v){
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(MainActivity.this,"all", mIUiListener);
    }
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private boolean validate() {
        String username = accountEdit.getText().toString();
        if (username.equals("")) {
            Toast.makeText(MainActivity.this, "用户名称是必填项！", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        String pwd = passwordEdit.getText().toString();
        if (pwd.equals("")) {
            Toast.makeText(MainActivity.this, "用户密码是必填项！", Toast.LENGTH_SHORT)
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
