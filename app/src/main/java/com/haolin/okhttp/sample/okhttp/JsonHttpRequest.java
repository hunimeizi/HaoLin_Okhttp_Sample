package com.haolin.okhttp.sample.okhttp;

import com.haolin.okhttp.sample.okhttp.listener.CallbackLitener;
import com.haolin.okhttp.sample.okhttp.listener.IHttpRequest;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：haoLin_Lee on 2019/04/30 20:56
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class JsonHttpRequest implements IHttpRequest {

    private String url;
    private byte[] data;
    private CallbackLitener callbackLitener;
    private HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallbackLitener listener) {
        this.callbackLitener = listener;
    }

    @Override
    public void execute() {

        URL url;

        try {
            url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection(); //打开http连接
            urlConnection.setConnectTimeout(5000); //连接超时时间
            urlConnection.setUseCaches(false);//不使用缓存
            urlConnection.setInstanceFollowRedirects(true); //成员函数 只作为当前函数 设置这个连接是否可以被重定向
            urlConnection.setReadTimeout(3000);//相应超时时间
            urlConnection.setDoInput(true); // 设置这个连接是否可以被写入
            urlConnection.setDoOutput(true);// 设置这个连接是否可以被输出
            urlConnection.setRequestMethod("POST");//设置请求方式
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息类型
            urlConnection.connect();//连接
            //---------使用字节流发送数据---------
            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            bos.write(data);
            bos.flush();
            out.close();
            // ----------字符流写入数据----------
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                callbackLitener.onSuccess(in);
            } else {
                callbackLitener.onFailure();
                throw new RuntimeException("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackLitener.onFailure();
            throw new RuntimeException("请求失败");
        } finally {
            urlConnection.disconnect();
        }

    }
}
