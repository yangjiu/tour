package com.joysus.okhttp;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * OKHTTP 通用工具类
 *
 * @author qiuheng
 */
public class OkHttpUtil {
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
            .parse("text/x-markdown; charset=utf-8");
    private static final OkHttpClient mOkHttpClient;
    public static final int TIMEOUT = 20;
    public static final int WRITE_TIMEOUT = 20;
    public static final int READ_TIMEOUT = 20;

    static {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();
    }

    //自定义回调接口
    public interface MyCallBack {
        void onFailure(Call call, IOException e);

        void onResponse(String json);
    }

    public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    public static void get(String url, Callback callback) {
        try {
            Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            Request request = new Request.Builder().url(url).get().build();
            enqueue(request, callback);
        } catch (Exception e) {

        }
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param f
     * @param params
     * @param filekey
     * @param callback
     */
    public static void post(String url, File f, Map<String, String> params,
                            String filekey, Callback callback) {
        try {
            Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            if (params != null) {
                Set<String> tset = params.keySet();
                for (String key : tset) {
                    builder.addFormDataPart(key, params.get(key));
                }
            }
            if (f != null) {
                builder.addFormDataPart(filekey, f.getName(),
                        RequestBody.create(MEDIA_TYPE_MARKDOWN, f));
            }
            RequestBody requestBody = builder.build();
            Request request = new Request.Builder().url(url).post(requestBody)
                    .build();
            enqueue(request, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送post请求
     *
     * @param url
     * @param f
     * @param params
     * @param filekey
     * @param callback
     */
    public static void post(String url, Callback callback) {
        try {
            RequestBody body = RequestBody.create(JSON, "");
            Request request = new Request.Builder().url(url).post(body).build();
            enqueue(request, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
