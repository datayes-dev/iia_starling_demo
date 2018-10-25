/*
 * *
 *  * 通联数据机密
 *  * --------------------------------------------------------------------
 *  * 通联数据股份公司版权所有 © 2013-2016
 *  *
 *  * 注意：本文所载所有信息均属于通联数据股份公司资产。本文所包含的知识和技术概念均属于
 *  * 通联数据产权，并可能由中国、美国和其他国家专利或申请中的专利所覆盖，并受商业秘密或
 *  * 版权法保护。
 *  * 除非事先获得通联数据股份公司书面许可，严禁传播文中信息或复制本材料。
 *  *
 *  * DataYes CONFIDENTIAL
 *  * --------------------------------------------------------------------
 *  * Copyright © 2013-2016 DataYes, All Rights Reserved.
 *  *
 *  * NOTICE: All information contained herein is the property of DataYes
 *  * Incorporated. The intellectual and technical concepts contained herein are
 *  * proprietary to DataYes Incorporated, and may be covered by China, U.S. and
 *  * Other Countries Patents, patents in process, and are protected by trade
 *  * secret or copyright law.
 *  * Dissemination of this information or reproduction of this material is
 *  * strictly forbidden unless prior written permission is obtained from DataYes.
 *
 */
package com.datayes.irr.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.datayes.common_utils.log.LogUtils;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 * 微信分享/登录回调activity
 * Created by ntop on 15/9/4.
 */
public class WXEntryActivity extends WXCallbackActivity /*implements IWXAPIEventHandler*/ {

    // 登录Code
    private static final int RETURN_MSG_TYPE_LOGIN = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("dsdf");
    }

    // 微信发送请求到第三方应用时，会回调到该方法
//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }
//
//    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
//    @Override
//    public void onResp(BaseResp resp) {
//
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                switch (resp.getType()) {
//                    case RETURN_MSG_TYPE_LOGIN:
//                        DYToast.makeText(this, "登录失败：授权未成功", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                switch (resp.getType()) {
//                    case RETURN_MSG_TYPE_LOGIN:
//                        DYToast.makeText(this, "登录失败：用户取消登录", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                break;
//            case BaseResp.ErrCode.ERR_OK:
//                switch (resp.getType()) {
//                    case RETURN_MSG_TYPE_LOGIN:
//                        //拿到了微信返回的code,立马再去请求access_token
//                        String code = ((SendAuth.Resp) resp).code;
//
//                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求，注意是get请求
//                        Activity lastActivity = BaseApp.getInstance().getLastActivity();
//                        if (lastActivity instanceof LoginActivity) {
//                            ((LoginActivity) lastActivity).onWeChatAuthBack(code);
//                            finish();
//                            return;
//                        }
//
//                        break;
//                }
//                break;
//        }
//        this.finish();
//    }

//    private void requestToken(final String code) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    String url = Config.INSTANCE.getWeChatBaseUrl() +
//                            "/sns/oauth2/access_token" +
//                            "?appid=wx51141d33827a8f56" +
//                            "&secret=1473542b7f61cd96a75b8b37a5971276" +
//                            "&code=" + code +
//                            "&grant_type=authorization_code";
//
//                    URL mURL = new URL(url);
//
//                    HttpURLConnection connection = (HttpURLConnection) mURL.openConnection();
//                    connection.setConnectTimeout(15 * 1000);
//                    connection.setReadTimeout(5 * 1000);
//                    connection.connect();
//
//                    if (connection.getResponseCode() == 200) {
//                        InputStream inputStream = connection.getInputStream();
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                        String line;
//                        StringBuilder sb = new StringBuilder();
//                        while ((line = reader.readLine()) != null) {
//                            sb.append(line.trim());
//                        }
//                        String result = sb.toString();
//                        Log.d("WXResponse", result);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.d("Exception", e.getMessage());
//                }
//            }
//        }).start();
//    }
}
