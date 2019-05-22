package com.haolin.okhttp.library.okhttp;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.haolin.okhttp.library.okhttp.listener.CallbackLitener;
import com.haolin.okhttp.library.okhttp.listener.IHttpRequest;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 作者：haoLin_Lee on 2019/04/30 20:50
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest httpRequest;

    public HttpTask(String url, T requestData, IHttpRequest httpRequest, CallbackLitener litener) {
        this.httpRequest = httpRequest;
        httpRequest.setUrl(url);
        String content = new Gson().toJson(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpRequest.setListener(litener);
    }

    @Override
    public void run() {
        try {
            httpRequest.execute();
        } catch (Exception e) {
            ThreadPoolManager.getThreadPoolManager().addDelayTask(this);
            e.printStackTrace();
        }
    }

    private long delayTime;
    private int retryCount;

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = System.currentTimeMillis() + delayTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public long getDelay(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed delayed) {
        return 0;
    }
}
