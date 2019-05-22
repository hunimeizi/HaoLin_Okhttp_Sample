package com.haolin.mvp.loginmvpdemo.listener;

import com.haolin.mvp.loginmvpdemo.bean.LoginBean;

/**
 * 作者：haoLin_Lee on 2019/05/22 11:18
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public interface LoginPresenterViewListener {
    interface LoginViewListener{
        void setProgressBarVis(int vis);
        void isLoginSuccess(boolean isLogin, LoginBean loginBean);
        void clearInfo();
    }
    interface LoginPresenterListener{
        void getLoginInfo(String userName,String pass);
        void clearLoginInfo();
    }
}
