package com.haolin.okhttp.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.haolin.okhttp.sample.okhttp.listener.IJsonDataListener;

public class MainActivity extends AppCompatActivity {

    private String url = "http://v.juhe.cn/xianxing/index?key=bd5079e4b776ba687a118c5cf3c234ce&city=beijing&type=1";
    private String error_url = "http://xxxxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendJsonRequest();
    }

    private void sendJsonRequest() {
        OkHttp.sendJsonRequest(null, error_url, Bean.class, new IJsonDataListener<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                Log.e("lyb=========", bean.toString());
            }

            @Override
            public void onFailure() {
                Log.e("lyb=========", "请求失败");
            }
        });

    }
}
