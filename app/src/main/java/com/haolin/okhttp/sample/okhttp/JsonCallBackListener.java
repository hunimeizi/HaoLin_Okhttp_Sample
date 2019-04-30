package com.haolin.okhttp.sample.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.haolin.okhttp.sample.okhttp.listener.CallbackLitener;
import com.haolin.okhttp.sample.okhttp.listener.IJsonDataListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 作者：haoLin_Lee on 2019/04/30 21:12
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class JsonCallBackListener<T> implements CallbackLitener {

    private Class<T> responseClass;
    private IJsonDataListener listener;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallBackListener(Class<T> responseClass, IJsonDataListener listener) {
        this.responseClass = responseClass;
        this.listener = listener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        //将inputStream对象转船成responseClass
        String response = getContent(inputStream);
        final T clazz = new Gson().fromJson(response, responseClass);
        //将子线程的数据传递到主线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(clazz);
            }
        });
    }


    @Override
    public void onFailure() {
        listener.onFailure();
    }

    private String getContent(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }
}
