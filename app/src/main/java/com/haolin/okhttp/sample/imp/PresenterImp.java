package com.haolin.okhttp.sample.imp;

import android.view.View;

import com.haolin.okhttp.sample.Bean;
import com.haolin.okhttp.sample.listener.BjModelListener;
import com.haolin.okhttp.sample.listener.PresenterViewListener;

/**
 * 作者：haoLin_Lee on 2019/05/21 22:04
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class PresenterImp implements PresenterViewListener.BjPresenter {
    private PresenterViewListener.BjView bjView;

    public PresenterImp(PresenterViewListener.BjView bjView) {
        this.bjView = bjView;
    }

    @Override
    public void getRequest() {
        bjView.progressVis(View.VISIBLE);
        new BjModelImp().setBjModelListener(new BjModelListener.SetBjModelCallBack() {
            @Override
            public void bjModelCallBack(Bean bean) {
                bjView.backResponse(bean != null, bean);
                bjView.progressVis(View.GONE);
            }
        });
    }
}
