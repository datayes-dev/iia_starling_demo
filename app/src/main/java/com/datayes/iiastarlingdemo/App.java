package com.datayes.iiastarlingdemo;

import com.datayes.common_view.base.BaseApp;
import com.datayes.iia.starling.Starling;

/**
 * 功能：
 *
 * @Author wentong.chen
 * on 2018/4/20.
 */

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化智能盯盘
        Starling.INSTANCE
                // baseurl
                .setBaseUrl("http://fcsc-staring.respool.wmcloud-qa.com")
                // 长链接url
                .setWebSocketUrl("ws://fcsc-staring.respool.wmcloud-qa.com:8722/ws?user=xxx&token=xxx")
                // okhttp用户拦截器
                .setUserInterceptor(new AuthorizationInterceptor())
                // 设置回调
                .setExternalProvider(new YiChuangExternalImpl())
                // 初始化
                .init(this, null, true);

        // 开始同步模块数据，建议使用模块之前就同步好数据（如：app启动时，或者app同步数据的时候）
        Starling.INSTANCE.startSyncData(getApplicationContext());
    }
}
