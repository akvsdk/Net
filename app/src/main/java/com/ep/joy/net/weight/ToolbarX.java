package com.ep.joy.net.weight;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ep.joy.net.R;


/**

 */
public class ToolbarX {
    private Toolbar mToolbar;
    private AppCompatActivity mActivity;
    private ActionBar mActionBar;
    private RelativeLayout rlCustom;
    private TextView titleTv;

    public ToolbarX(Toolbar toolbar, AppCompatActivity activity) {
        mToolbar = toolbar;
        rlCustom = (RelativeLayout) mToolbar.findViewById(R.id.rlCustom);
        mActivity = activity;
        mActivity.setSupportActionBar(mToolbar);
        mActionBar = mActivity.getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        View customLayoutInToolbar = View.inflate(mActivity, R.layout.custom_layout_in_toolbar, null);
        titleTv = (TextView) customLayoutInToolbar.findViewById(R.id.tv);
        setCustomView(customLayoutInToolbar);
    }

    public ToolbarX setTitle(String text) {
        mActionBar.setTitle(text);
        return this;
    }

    public ToolbarX setSubTitle(String text) {
        titleTv.setText(text);
        return this;


    }

    public ToolbarX setTitle(int resId) {
        mActionBar.setTitle(resId);
        return this;

    }

    public ToolbarX setSubTitle(int resId) {
        mActionBar.setSubtitle(resId);
        return this;

    }

    public ToolbarX setNavigationOnClickListener(View.OnClickListener listener) {
        mToolbar.setNavigationOnClickListener(listener);
        return this;
    }

    public ToolbarX setNavigationIcon(int resId) {
        mToolbar.setNavigationIcon(resId);
        return this;
    }

    public ToolbarX setDisplayHomeAsUpEnabled(boolean show) {
        mActionBar.setDisplayHomeAsUpEnabled(show);
        return this;
    }

    public ToolbarX setCustomView(View view) {
        rlCustom.removeAllViews();
        rlCustom.addView(view);
        return this;
    }

    public ToolbarX hide() {
        mActionBar.hide();
        return this;
    }

}
