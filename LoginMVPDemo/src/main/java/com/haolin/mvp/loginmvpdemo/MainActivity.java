package com.haolin.mvp.loginmvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.haolin.mvp.loginmvpdemo.bean.LoginBean;
import com.haolin.mvp.loginmvpdemo.imp.LoginPresenterImp;
import com.haolin.mvp.loginmvpdemo.listener.LoginPresenterViewListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginPresenterViewListener.LoginViewListener {

    private EditText edit_name, edit_pass;
    private ProgressBar progress_vis;
    private TextView tv_loginInfo;
    private LoginPresenterImp loginPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_loginInfo = findViewById(R.id.tv_loginInfo);
        edit_name = findViewById(R.id.edit_name);
        edit_name.setText("13594347817");
        edit_pass = findViewById(R.id.edit_pass);
        edit_pass.setText("123456");
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        Button btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        progress_vis = findViewById(R.id.progress_vis);
        loginPresenterImp = new LoginPresenterImp(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(edit_name.getText().toString()) || TextUtils.isEmpty(edit_pass.getText().toString())) {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginPresenterImp.getLoginInfo(edit_name.getText().toString(), edit_pass.getText().toString());
                break;
            case R.id.btn_clear:
                loginPresenterImp.clearLoginInfo();
                break;
        }
    }

    @Override
    public void setProgressBarVis(int vis) {
        progress_vis.setVisibility(vis);
    }

    @Override
    public void isLoginSuccess(boolean isLogin, LoginBean loginBean) {
        tv_loginInfo.setText(isLogin ? "登陆成功返回结果为\n" + loginBean.toString() : "登陆失败");
    }

    @Override
    public void clearInfo() {
        edit_name.setText("");
        edit_pass.setText("");
    }
}
