package com.cyq7on.crud.common.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    public static Response postFile(final String url, String fileKey, String fileName, byte[] file) throws IOException {
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(file, MediaType.parse("image/*"));
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart(fileKey, fileName, body);
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        return new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(new MyHttpLoggingInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .build().newCall(request).execute();

    }
}
