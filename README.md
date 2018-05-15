# 通联数据-灵机股票-智能盯盘SDK
## 一.接入指南

### (一).SDK依赖（4.0+ Android 14）

#### 1.依赖镜像：

    repositories {
        jcenter()
        maven { url "https://raw.github.com/datayes-dev/android_maven/master" }
        maven { url "https://raw.github.com/datayes-dev/iia_android/master" }
        maven { url "https://jitpack.io" }
    }
    
#### 2.依赖库

以下依赖是必须的，但是版本可以按照自己的项目情况修改

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    // sdk依赖原生

    compile 'com.android.support:appcompat-v7:26.1.0'
    compile 'com.android.support:design:26.1.0'
    compile 'com.android.support:cardview-v7:26.1.0'

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    // sdk依赖通联

    // 通联工具包
    compile 'com.datayes:common-utils:0.2.0'
    // 通联网络
    compile 'com.datayes:common-net:0.2.3'
    // 通联存储
    compile 'com.datayes:common-storage:0.2.0'
    // 通联事件
    compile 'com.datayes:common-bus:0.2.0'
    // 通联通用view
    compile 'com.datayes:common-view:0.2.1'
    // 通联通用绘图
    compile 'com.datayes:common-chart:0.3.5'
    // 灵机智投基础
    compile('com.datayes.iia:module-common:0.3.5@aar')
    // 智能盯盘自选股接口
    compile('com.datayes.iia:selfstock_api:0.1.2@aar')
    // 智能盯盘股票服务接口
    compile('com.datayes.iia:servicestock_api:0.1.2@aar')
    // 智能盯盘sdk
    compile('com.datayes.iia:starling:0.2.5@aar')

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    // sdk外部依赖

    // 路由
    compile 'com.alibaba:arouter-api:1.3.1'
    // rx显示层库
    compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
    // 下拉刷新组件
    compile 'in.srain.cube:ultra-ptr:1.0.11'
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
            // okhttp用户拦截器
            .setUserInterceptor(new AuthorizationInterceptor())
            // 设置回调
            .setExternalProvider(new YiChuangExternalImpl())
            // 初始化
            .init(this, null, true);

    // 开始同步模块数据，建议使用模块之前就同步好数据（如：app启动时，或者app同步数据的时候，调用之前保证自选股数据完整）
    Starling.INSTANCE.startSyncData(getApplicationContext());
    
    
### (三).接口用户权限

用户权限是基于okhttp拦截器，可以参考Demo（AuthorizationInterceptor）


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

接入者需要实现IExternalProvider接口，可以参考Demo（YiChuangExternalImpl）
        
    // 初始化智能盯盘
    Starling.INSTANCE
            // 设置回调
            .setExternalProvider(new YiChuangExternalImpl())
            
##### 需要实现的接口：             

    @Override
    public List<StockBean> getSelfStockList() {

        // TODO 获取自选股列表，这里调用比较频繁，最好是取缓存
        return getStockBeans();
    }

    @Override
    public void openStockDetail(Context context, String ticker, String market) {

        // TODO  打开股票详情页面
        ToastUtils.showShortToast(context, "打开股票详情页ticker: " + ticker);
    }

    @Override
    public String getWebSocketUrl() {
        // TODO 获取长链接url, 需要保证url token的正确性
        return Test.INSTANCE.getWebSocketUrl();
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
        "");
    
### (五).智能盯盘入口    

个股异动入口view (IiaStarlingStockChgView)

    // 必须调用start开始才会启动长链接
    IiaStarlingStockChgView.start();
    
    // 必须调用stop才会停止长链接
    // 未及时断开会产生不必要的性能消耗
    IiaStarlingStockChgView.stop();


板块异动入口view (IiaStarlingAreaChangeView)

    // 必须调用start开始才会启动长链接
    IiaStarlingAreaChangeView.start();
    
    // 必须调用stop才会停止长链接
    // 未及时断开会产生不必要的性能消耗
    IiaStarlingAreaChangeView.stop();