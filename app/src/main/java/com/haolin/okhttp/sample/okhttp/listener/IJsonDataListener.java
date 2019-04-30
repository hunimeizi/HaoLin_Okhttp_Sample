package com.haolin.okhttp.sample.okhttp.listener;

/**
 * 作者：haoLin_Lee on 2019/04/30 21:25
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public interface IJsonDataListener<T> {

    void onSuccess(T object);
    void onFailure();
}
