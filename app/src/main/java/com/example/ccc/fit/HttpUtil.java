package com.example.ccc.fit;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil
{
    public static void sendOKHttpRequest(String address,okhttp3.Callback callback)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    public static String postOKHttpRequest(String address,String name,String code)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("username", name).add("password", code).build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        String responsedata=" ";
        try {
            Response response = client.newCall(request).execute();
            responsedata=response.body().string();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return responsedata;
    }

}
