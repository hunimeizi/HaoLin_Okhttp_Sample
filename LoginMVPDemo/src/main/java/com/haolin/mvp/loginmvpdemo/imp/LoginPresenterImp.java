package com.haolin.mvp.loginmvpdemo.imp;

import android.view.View;

import com.haolin.mvp.loginmvpdemo.bean.LoginBean;
import com.haolin.mvp.loginmvpdemo.listener.LoginModelListener;
import com.haolin.mvp.loginmvpdemo.listener.LoginPresenterViewListener;

/**
 * 作者：haoLin_Lee on 2019/05/22 11:22
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class LoginPresenterImp implements LoginPresenterViewListener.LoginPresenterListener {

    private LoginPresenterViewListener.LoginViewListener loginViewListener;

    public LoginPresenterImp(LoginPresenterViewListener.LoginViewListener loginViewListener) {
        this.loginViewListener = loginViewListener;
    }

    @Override
    public void getLoginInfo(String userName, String pass) {
        loginViewListener.setProgressBarVis(View.VISIBLE);
        new LoginModelImp(userName, pass).loginInfoListener(new LoginModelListener.LoginModelCallBack() {
            @Override
            public void loginInfo(LoginBean loginBean) {
                loginViewListener.setProgressBarVis(View.GONE);
                loginViewListener.isLoginSuccess(loginBean != null, loginBean);
            }
        });
    }

    @Override
    public void clearLoginInfo() {
        loginViewListener.clearInfo();
    }

}
