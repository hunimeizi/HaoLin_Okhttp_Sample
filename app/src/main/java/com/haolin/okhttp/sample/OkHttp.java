package com.haolin.okhttp.sample;

import com.haolin.okhttp.sample.okhttp.HttpTask;
import com.haolin.okhttp.sample.okhttp.JsonCallBackListener;
import com.haolin.okhttp.sample.okhttp.JsonHttpRequest;
import com.haolin.okhttp.sample.okhttp.ThreadPoolManager;
import com.haolin.okhttp.sample.okhttp.listener.CallbackLitener;
import com.haolin.okhttp.sample.okhttp.listener.IHttpRequest;
import com.haolin.okhttp.sample.okhttp.listener.IJsonDataListener;

/**
 * 作者：haoLin_Lee on 2019/04/30 21:30
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class OkHttp {

    public static<T,M> void sendJsonRequest(T requestData, String url, Class<M> response, IJsonDataListener listener) {
        IHttpRequest iHttpRequest = new JsonHttpRequest();
        CallbackLitener callbackLitener = new JsonCallBackListener<>(response, listener);
        HttpTask httpTask = new HttpTask(url, requestData, iHttpRequest, callbackLitener);
        ThreadPoolManager.getThreadPoolManager().addTask(httpTask);
    }
}
