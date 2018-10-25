package com.datayes.irr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.datayes.common_storage.SPUtils;
import com.datayes.common_utils.toast.ToastUtils;
import com.datayes.iia.starling.Starling;
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
        setContentView(com.datayes.irr.R.layout.activity_main);
        mIiaStarlingStockChgView = (IiaStarlingStockChgView) findViewById(com.datayes.irr.R.id.stock_change_view);
        mIiaStarlingAreaChangeView = (IiaStarlingAreaChangeView) findViewById(com.datayes.irr.R.id.area_change_view);

        initTest();
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


    private void initTest() {

        TextView textView = (TextView) findViewById(com.datayes.irr.R.id.tv_btn);

        String environment = (String) SPUtils.getInstance()
                .get(getApplicationContext(), "test_environment", "", Starling.INSTANCE);

        if ("qa".equals(environment)) {

            textView.setText("qa");

        } else {

            textView.setText("stg");
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String environment = (String) SPUtils.getInstance()
                        .get(getApplicationContext(), "test_environment", "", Starling.INSTANCE);

                if ("qa".equals(environment)) {

                    environment = "stg";

                } else {

                    environment = "qa";
                }

                SPUtils.getInstance().put(getApplicationContext(), "test_environment", environment, Starling.INSTANCE);

                ToastUtils.showLongToast(getApplicationContext(), "切换环境请重启");

                initTest();
            }
        });
    }

}
