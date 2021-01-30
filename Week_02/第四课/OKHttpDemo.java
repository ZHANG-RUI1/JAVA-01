package io.httpserver;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
/**
 * @author zhangrui
 * @version 1.0
 * @date 2021/1/22 17:32
 */

public class OKHttpDemo {
    public static final String URL = "http://localhost:8808";

    public static void main(String[] args){
        OkHttpClient okHttpClient;
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(URL).get().build();
        final Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
