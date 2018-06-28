package com.datayes.iiastarlingdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp 用户权限拦截器
 * Created by shenen.gao on 2018/4/27.
 *
 * @author shenen.gao
 */

public class AuthorizationInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder builder = request.newBuilder();

        builder.addHeader("DatayesPrincipalName", "sdfasdfsadfsdfsadf");
        builder.addHeader("userCert", "sdfsdfsdfsd");

        return chain.proceed(builder.build());
    }
}
