# 通联数据-灵机股票-智能盯盘SDK
## 一.接入指南

### (一).SDK依赖（4.0+ Android 14）

#### 1.依赖镜像：

    repositories {

        maven { url "https://raw.github.com/datayes-dev/android_maven/master" }
        maven { url "https://raw.github.com/datayes-dev/iia_android/master" }
        maven { url "https://jitpack.io" }
    }
    
#### 2.依赖库

以下依赖是必须的，但是版本可以按照自己的项目情况修改

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    // sdk依赖原生

    compile 'com.android.support:support-v4:25.3.1'
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support:design:25.3.1'
    compile 'com.android.support:multidex:1.0.1'
    compile 'com.android.support:cardview-v7:25.3.1'

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    // sdk依赖通联

    compile 'com.datayes:common-view:0.1.26'
    compile 'com.datayes:common-chart:0.3.0'
    compile('com.datayes.iia:module-common:0.1.15@aar')
    compile('com.datayes.iia:starling:0.1.18@aar')

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    // sdk外部依赖

    // 路由
    compile 'com.alibaba:arouter-api:1.3.1'

    // RxJava
    compile 'io.reactivex.rxjava2:rxjava:2.1.6'
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'

    // rx生命周期库
    compile 'com.trello.rxlifecycle2:rxlifecycle-android:2.1.0'
    compile 'com.trello.rxlifecycle2:rxlifecycle-components:2.1.0'

    // rx显示层库
    compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'

    // 下拉刷新组件
    compile 'in.srain.cube:ultra-ptr:1.0.11'

    // 网络
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    compile 'com.squareup.retrofit2:converter-scalars:2.3.0'

    // butterknife
    compile 'com.jakewharton:butterknife:8.6.0'

    // greendao
    compile 'org.greenrobot:greendao:3.2.2'

    // WebSocket
    compile "org.java-websocket:Java-WebSocket:1.3.8"
    


### (二).SDK初始化

    // 初始化智能盯盘
    Starling.INSTANCE
            // baseurl
            .setBaseUrl("http://fcsc-staring.respool.wmcloud-qa.com")
            // 长链接url
            .setWebSocketUrl("ws://fcsc-staring.respool.wmcloud-qa.com:8722/ws?user=10061&token=123")
            // okhttp用户拦截器
            .setUserInterceptor(new AuthorizationInterceptor())
            // 设置回调
            .setExternalProvider(new YiChuangExternalImpl())
            // 初始化
            .init(this, null, true);

    // 开始同步模块数据，建议使用模块之前就同步好数据（如：app启动时，或者app同步数据的时候）
    Starling.INSTANCE.startSyncData(getApplicationContext());
    
    
### (三).接口用户权限

用户权限是基于okhttp拦截器，可以参考（AuthorizationInterceptor）


    // 初始化智能盯盘
    Starling.INSTANCE
            // okhttp用户拦截器
            .setUserInterceptor(new AuthorizationInterceptor())
            
##### 例子：   
            
    /**
     * okhttp 用户权限拦截器
     * Created by shenen.gao on 2018/4/27.
     *
     * @author shenen.gao
     */
    
    public class AuthorizationInterceptor implements Interceptor {
    
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
    
            // TODO 用户id
            builder.addHeader("DatayesPrincipalName", "testestest11");
    
            // TODO deviceId
            builder.addHeader("deviceId", "deviceId");
    
            return chain.proceed(builder.build());
        }
    }


### (四).SDK需要实现的接口

接入者需要实现IExternalProvider接口，可以参考（YiChuangExternalImpl）
        
    // 初始化智能盯盘
    Starling.INSTANCE
            // 设置回调
            .setExternalProvider(new YiChuangExternalImpl())
            
##### 需要实现的接口：             

    @Override
    public List<StockBean> getSelfStockList() {
    
        // TODO 获取自选股列表
        return getStockBeans();
    }

    @Override
    public void openStockDetail(Context context, String ticker, String market) {

        // TODO  打开股票详情页面
        ToastUtils.showShortToast(context, "打开股票详情页ticker: " + ticker);
    }
    
##### 股票数据结构：    
    
    new StockBean(
        "000002.XSHE", // 证券ID
        "000002",  // 股票代码 (必须)
        "万科A", // 名称 (必须)
        "WKA", // 证券简称拼音
        "XSHE", // 交易市场  XSHE, XSHG (必须)
        "E", // 证券类型(股票，债券)
        "L",// L-上市，S-暂停，DE-终止上市，UN-未上市
        "",
        ""
    



