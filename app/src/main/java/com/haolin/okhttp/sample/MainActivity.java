package com.haolin.okhttp.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.haolin.okhttp.sample.imp.PresenterImp;
import com.haolin.okhttp.sample.listener.PresenterViewListener;
import com.haolin.okhttp.sample.okhttp.listener.IJsonDataListener;

public class MainActivity extends AppCompatActivity implements PresenterViewListener.BjView {

    private TextView tvBean;
    private ProgressBar progressBean;
    private PresenterImp presenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBean = findViewById(R.id.tvBean);
        progressBean = findViewById(R.id.progressBean);
        presenterImp = new PresenterImp(this);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void getDataOnClick(View view) {
        presenterImp.getRequest();
    }

    @Override
    public void backResponse(boolean isSuccess, Bean bean) {
        if (isSuccess) {
            tvBean.setText(bean.toString());
        } else {
            Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void progressVis(int vis) {
        progressBean.setVisibility(vis);
    }
}
