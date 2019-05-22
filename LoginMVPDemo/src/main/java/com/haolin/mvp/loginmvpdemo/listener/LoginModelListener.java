package com.haolin.mvp.loginmvpdemo.listener;

import com.haolin.mvp.loginmvpdemo.bean.LoginBean;

/**
 * 作者：haoLin_Lee on 2019/05/22 11:11
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public interface LoginModelListener {

    interface LoginModelCallBack {
        void loginInfo(LoginBean loginBean);
    }

    void loginInfoListener(LoginModelCallBack callBack);
}
