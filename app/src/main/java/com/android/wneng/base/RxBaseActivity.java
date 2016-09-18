package com.android.wneng.base;

import android.content.Intent;
import android.os.Bundle;

import com.android.wneng.R;
import com.android.wneng.app.AppManager;
import com.android.wneng.utils.Helper;
import com.android.wneng.utils.jump.BaseConstants;
import com.android.wneng.utils.statusbar.StatusBarCompat;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by wneng on 16/9/8.
 */

public abstract class RxBaseActivity extends RxAppCompatActivity {


    private int backEnterAnim = 0;
    private int backExitAnim = 0;
    private String activityAnimType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        getAnimParams();
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();
        //适配4.4系统的StatusBar
        StatusBarCompat.compat(this);

    }


    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void initToolBar();


    /**
     * function: 获取动画参数
     */
    private void getAnimParams() {
        Intent intent = getIntent();
        if (Helper.isNotNull(intent)) {
            if (intent.hasExtra(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE)) {
                activityAnimType  = intent.getStringExtra(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE);
            }
            if (intent.hasExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM)) {
                backEnterAnim = intent.getIntExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, 0);
            }
            if (intent.hasExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKEXITANIM)) {
                backExitAnim = intent.getIntExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKEXITANIM, 0);
            }
        }
    }

    /**
     * function: 设置后退动画(finish时调用)
     */
    public void setBackAnim() {
        if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_SLIDE.equals(activityAnimType)) {
            overridePendingTransition(R.anim.pull_right_in, R.anim.pull_right_out);
        } else if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP.equals(activityAnimType)) {
            overridePendingTransition(R.anim.pull_bottom_in, R.anim.pull_bottom_out);
        } else if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_CENTER.equals(activityAnimType)) {
            overridePendingTransition(R.anim.pull_center_in, R.anim.pull_center_out);
        } else if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA.equals(activityAnimType)) {
            overridePendingTransition(R.anim.pull_alpha_in, R.anim.pull_alpha_out);
        } else {
            overridePendingTransition(backEnterAnim, backExitAnim);
        }
    }
}
