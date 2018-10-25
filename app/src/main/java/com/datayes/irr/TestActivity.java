package com.datayes.irr;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.datayes.iia.module_common.base.BaseActivity;

public class TestActivity extends BaseActivity {
    @Override
    protected int getContentLayoutRes() {
        return 0;
    }

    @Override
    public void onApplicationToBackground() {

    }

    @Override
    public void onApplicationBackFromBackGround() {

    }

    @Override
    protected void onPause() {
        super.onPause();

        View rootView = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
        pauseLifeView(rootView);
    }

    /**
     * 递归找view
     * @param parent
     */
    public void pauseLifeView(View parent) {

        if (parent instanceof ViewGroup) {

            pauseLifeView(parent);

        } else if (parent != null) {

            if (parent instanceof ILifeView) {

                ((ILifeView)parent).onPause();
            }
        }
    }

    /**
     * 集成周期接口的view
     */
    public class TestView extends View implements ILifeView {

        public TestView(Context context) {
            super(context);
        }

        @Override
        public void onResume() {

        }

        @Override
        public void onPause() {

        }
    }

    /**
     * 生命周期接口
     */
    public interface ILifeView {

        void onResume();

        void onPause();
    }

}
