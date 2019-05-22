package com.haolin.okhttp.sample.listener;

import com.haolin.okhttp.sample.Bean;

/**
 * 作者：haoLin_Lee on 2019/05/21 21:55
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public interface BjModelListener {

    interface SetBjModelCallBack {
        void bjModelCallBack(Bean bean);
    }
    void setBjModelListener(SetBjModelCallBack setBjModelCallBack);
}
