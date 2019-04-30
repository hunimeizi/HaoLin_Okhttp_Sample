package com.haolin.okhttp.sample.okhttp.listener;

import java.io.InputStream;

/**
 * 作者：haoLin_Lee on 2019/04/30 20:47
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public interface CallbackLitener {

    void onSuccess(InputStream inputStream);

    void onFailure();
}
