package com.haolin.okhttp.sample.listener;

import com.haolin.okhttp.sample.Bean;

/**
 * 作者：haoLin_Lee on 2019/05/21 22:00
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class PresenterViewListener {
   public interface BjView{
        void backResponse(boolean isSuccess, Bean bean);
        void progressVis(int vis);
    }
    public interface BjPresenter{
        void getRequest();
    }
}
