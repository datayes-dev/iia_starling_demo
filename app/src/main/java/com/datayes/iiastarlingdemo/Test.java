package com.datayes.iiastarlingdemo;

import android.content.Context;
import android.text.TextUtils;

import com.datayes.common_storage.SPUtils;
import com.datayes.iia.starling.Starling;

/**
 * 测试
 * Created by shenen.gao on 2018/5/8.
 *
 * @author shenen.gao
 */

public enum Test {
    // 单例
    INSTANCE;

    private String baseUrl;
    private String webSocketUrl;

    /**
     * 测试用
     */
    private String testUserId;

    public String getWebSocketUrl() {
        return webSocketUrl;
    }

    public String getTestUserId() {
        return testUserId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void init(Context context) {

        // 这里只是方便测试，随机一个用户id
//        testUserId = (String) SPUtils.getInstance()
//                .get(context, "testUserId", "", Starling.INSTANCE);
//
//        if (TextUtils.isEmpty(testUserId)) {
//
//            testUserId = String.valueOf(Math.abs(new Random(System.currentTimeMillis()).nextInt()));
//            SPUtils.getInstance().put(context, "testUserId", testUserId, Starling.INSTANCE);
//        }

        testUserId = "testtestessdklfjaslkdjf";


        // 测试环境切换
        String environment = (String) SPUtils.getInstance().get(context, "test_environment", "", Starling.INSTANCE);

        if (TextUtils.isEmpty(environment)) {

            environment = "qa";
            SPUtils.getInstance().put(context, "test_environment", environment, Starling.INSTANCE);
        }

        baseUrl = "https://gw.wmcloud-stg.com";
        webSocketUrl = "ws://101.226.198.90:8722/ws?user=" + testUserId + "&token=" + testUserId;

        if ("qa".equals(environment)) {

            baseUrl = "http://staringwizard-service.respool.wmcloud-qa.com";
            webSocketUrl = "ws://fcsc-staring.respool.wmcloud-qa.com:8722/ws?user=" + testUserId + "&token=" + testUserId;
        }

    }

}
