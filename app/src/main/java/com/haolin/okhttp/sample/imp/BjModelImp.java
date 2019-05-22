package com.haolin.okhttp.sample.imp;

import com.haolin.okhttp.library.okhttp.OkHttp;
import com.haolin.okhttp.library.okhttp.listener.IJsonDataListener;
import com.haolin.okhttp.sample.Bean;
import com.haolin.okhttp.sample.listener.BjModelListener;

/**
 * 作者：haoLin_Lee on 2019/05/21 21:57
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class BjModelImp implements BjModelListener {

    private String url = "http://v.juhe.cn/xianxing/index?key=bd5079e4b776ba687a118c5cf3c234ce&city=beijing&type=1";

    @Override
    public void setBjModelListener(final SetBjModelCallBack setBjModelCallBack) {
        OkHttp.sendJsonRequest(null, url, Bean.class, new IJsonDataListener<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                setBjModelCallBack.bjModelCallBack(bean);
            }

            @Override
            public void onFailure() {
                setBjModelCallBack.bjModelCallBack(null);
            }
        });
    }
}
