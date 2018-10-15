package com.jeffrey.architecture.ribs;

import android.view.ViewGroup;

import com.jeffrey.architecture.R;
import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;

public class RibsMainActivity extends RibActivity {

    @Override
    protected ViewRouter<?, ?, ?> createRouter(ViewGroup parentViewGroup) {
        setTitle(R.string.title_activity_ribs_main);
        RibsMainBuilder rootBuilder = new RibsMainBuilder(new RibsMainBuilder.ParentComponent() {});
        return rootBuilder.build(parentViewGroup);
    }
}
