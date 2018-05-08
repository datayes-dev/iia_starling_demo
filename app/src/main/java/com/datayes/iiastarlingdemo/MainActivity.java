package com.datayes.iiastarlingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.datayes.iia.starling.main.area.IiaStarlingAreaChangeView;
import com.datayes.iia.starling.main.stock.IiaStarlingStockChgView;

public class MainActivity extends AppCompatActivity {

    /**
     * 个股异动入口view
     */
    private IiaStarlingStockChgView mIiaStarlingStockChgView;

    /**
     * 板块异动入口view
     */
    private IiaStarlingAreaChangeView mIiaStarlingAreaChangeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIiaStarlingStockChgView = (IiaStarlingStockChgView) findViewById(R.id.stock_change_view);
        mIiaStarlingAreaChangeView = (IiaStarlingAreaChangeView) findViewById(R.id.area_change_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO 必须调用start开始才会启动长链接
        mIiaStarlingStockChgView.start();
        mIiaStarlingAreaChangeView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // TODO 必须调用stop才会停止长链接
        // TODO 未及时断开会产生不必要的性能消耗
        mIiaStarlingStockChgView.stop();
        mIiaStarlingAreaChangeView.stop();
    }


}
