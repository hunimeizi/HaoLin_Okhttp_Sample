package com.haolin.okhttp.sample.okhttp.listener;

/**
 * 作者：haoLin_Lee on 2019/04/30 20:42
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public interface IHttpRequest {

     void setUrl(String url);

     void setData(byte[] data);

     void setListener(CallbackLitener listener);

     void execute();
}
