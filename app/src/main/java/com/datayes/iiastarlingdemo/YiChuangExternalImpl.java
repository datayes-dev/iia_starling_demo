package com.datayes.iiastarlingdemo;

import android.content.Context;

import com.datayes.common_utils.toast.ToastUtils;
import com.datayes.iia.servicestock_api.bean.StockBean;
import com.datayes.iia.starling.IExternalProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 一创外部实现
 * Created by shenen.gao on 2018/4/27.
 *
 * @author shenen.gao
 */

public class YiChuangExternalImpl implements IExternalProvider {

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

    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    // mock

    public List<StockBean> getStockBeans() {

        List<StockBean> list = new ArrayList<>();

        list.add(new StockBean(
                "000002.XSHE", // 证券ID
                "000002",  // 股票代码 (必须)
                "万科A", // 名称 (必须)
                "WKA", // 证券简称拼音
                "XSHE", // 交易市场  XSHE, XSHG (必须)
                "E", // 证券类型(股票，债券)
                "L",// L-上市，S-暂停，DE-终止上市，UN-未上市
                "",
                "")
        );

        list.add(new StockBean(
                "",
                "000001",
                "平安银行",
                "",
                "XSHE",
                "",
                "",
                "",
                ""
        ));

        list.add(new StockBean(
                "",
                "000007",
                "全新好",
                "",
                "XSHE",
                "",
                "",
                "",
                ""
        ));

        list.add(new StockBean(
                "",
                "000006",
                "深振业A",
                "",
                "XSHE",
                "",
                "",
                "",
                ""
        ));

        list.add(new StockBean(
                "",
                "000005",
                "世纪星源",
                "",
                "XSHE",
                "",
                "",
                "",
                ""
        ));

        list.add(new StockBean(
                "",
                "000004",
                "国农科技",
                "",
                "XSHE",
                "",
                "",
                "",
                ""
        ));

        list.add(new StockBean(
                "",
                "000009",
                "中国宝安",
                "",
                "XSHE",
                "",
                "",
                "",
                ""
        ));

        return list;
    }


}
