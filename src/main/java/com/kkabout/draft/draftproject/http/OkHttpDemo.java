package com.kkabout.draft.draftproject.http;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author kangjian03@58.com
 * @date 2020/4/20
 **/
@Component
public class OkHttpDemo {
    private OkHttpClient client;

    private static int connectTimeout = 50;

    private static int readTimeout = 200;

    @PostConstruct
    public void init() {
        client = new OkHttpClient().newBuilder()
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS).readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    public void doGet() throws IOException {
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        Call call = client.newCall(request);

        /**
         * 同步请求
         */
        Response responseSync = call.execute();
        System.out.println(responseSync);
    }

    public CompletableFuture doGetAsync() {
        /**
         * 异步请求
         */
        Request request = new Request.Builder()
                .url("www.baidu.com")
                .build();
        Call call = client.newCall(request);
        CompletableFuture cf = new CompletableFuture();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                cf.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                cf.complete(response.body().toString());
            }
        });
        return cf;
    }
}
