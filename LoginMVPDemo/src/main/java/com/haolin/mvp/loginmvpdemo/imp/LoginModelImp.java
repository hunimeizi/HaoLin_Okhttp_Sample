package com.haolin.mvp.loginmvpdemo.imp;

import com.haolin.mvp.loginmvpdemo.bean.LoginBean;
import com.haolin.mvp.loginmvpdemo.listener.LoginModelListener;
import com.haolin.okhttp.library.okhttp.OkHttp;
import com.haolin.okhttp.library.okhttp.listener.IJsonDataListener;

/**
 * 作者：haoLin_Lee on 2019/05/22 11:15
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class LoginModelImp implements LoginModelListener {

    private String url;
    private String userName,pass;

    public LoginModelImp(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        url = "https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5&phone="+userName+"&passwd="+pass;
    }

    @Override
    public void loginInfoListener(final LoginModelCallBack callBack) {
        OkHttp.sendJsonRequest(null, url, LoginBean.class, new IJsonDataListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                callBack.loginInfo(bean);
            }

            @Override
            public void onFailure() {
                callBack.loginInfo(null);
            }
        });
    }
}
